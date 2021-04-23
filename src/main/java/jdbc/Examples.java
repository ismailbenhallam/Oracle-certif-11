package jdbc;

import jdbc.utils.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Examples implements AutoCloseable {
    private static final String URL = "jdbc:mysql://localhost:3307/certif_mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE = "persons";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE;
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE + " VALUES(NULL,?,?)";

    private Connection connection;
    private PreparedStatement selectAllStatement;
    private PreparedStatement insertAllStatement;

    public Examples() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            selectAllStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            insertAllStatement = connection.prepareStatement(INSERT_QUERY);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        selectAllStatement.close();
        insertAllStatement.close();
        connection.close();
    }

    public static void main(String[] args) {
        try (var examples = new Examples()) {

            examples.databaseMetaData();

            // executeUpdate
//            examples.savePerson(new Person(0, "Hamid", "BOUCHNAK"));

            // executeUpdate (transactional)
            /*examples.savePersonList(List.of(
                    new Person(0, "Eren", "YIGAR")
                    , new Person(0, "Mikassa", "AKRAMAN")
                    , new Person(0, "Levi", "AKRAMAN")
//                    , new Person(0, "REMOOVE", "IF U WANT TO TEST TRANSACTIONAL MODE")
            ));*/

//            examples.updatePerson(new Person(18, "Levi", "AKRAMAN"));

            // executeQuery
            examples.findAllPersons().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void databaseMetaData() {
        try {
            var metaData = connection.getMetaData();
            System.out.println("________________________________________________________");
            System.out.println("DatabaseProductName :" + metaData.getDatabaseProductName());
            System.out.println("DatabaseProductVersion :" + metaData.getDatabaseProductVersion());
            System.out.println("DriverName :" + metaData.getDriverName());
            System.out.println("DriverVersion :" + metaData.getDriverVersion());
            System.out.println("supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) :" + metaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
            System.out.println("________________________________________________________");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @deprecated This is method is deprecated, cause it doesn't use a {@link PreparedStatement}
     * Please use {@link #findAllPersons} method instead
     */
    @Deprecated(forRemoval = true)
    public List<Person> getAllPersons() {
        try (var con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            return listFromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Person> findAllPersons() {
        try (ResultSet resultSet = selectAllStatement.executeQuery()) {
            return listFromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void savePersonList(List<Person> persons) {
        try {
            connection.setAutoCommit(false);
            for (Person person : persons) {
                save(person);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    private void updatePerson(Person person) {
        // NOTE; Just for illustrating this feature, there is better ways to update a row in JDBC API
        try (
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            if (!connection.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
                System.err.println("This database doesn't support 'ResultSet.TYPE_FORWARD_ONLY' & 'ResultSet.CONCUR_UPDATABLE'");
                return;
            }
            while (resultSet.next()) {
                if (resultSet.getInt(1) == person.getId()) {
                    resultSet.updateString(2, person.getFirstName());
                    resultSet.updateString(3, person.getLastName());
                    resultSet.updateRow();
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void savePerson(Person person) {
        try {
            save(person);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // Helper methods

    private List<Person> listFromResultSet(ResultSet resultSet) throws SQLException {
        var list = new ArrayList<Person>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            var firstName = resultSet.getString(2);
            var lastName = resultSet.getString(3);
            list.add(new Person(id, firstName, lastName));
        }
        return list;
    }

    private void save(Person person) throws SQLException {
        insertAllStatement.setString(1, person.getFirstName());
        insertAllStatement.setString(2, person.getLastName());
        insertAllStatement.executeUpdate();
    }
}
