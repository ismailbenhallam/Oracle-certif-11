module org.ismailbenhallam.certif_training {
    requires java.net.http;
    requires java.sql;
    provides java.sql.Driver with spi.MyJDBCDriver;
}