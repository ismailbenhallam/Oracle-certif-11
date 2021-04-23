package security;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.SocketPermission;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Examples {

    //  TODO: https://docs.oracle.com/en/java/javase/11/security/java-se-platform-security-architecture.html
    public static void main(String[] args) {
//        checkPermissions();

        doPrivileged();

//        messageDigest("My strong password");

//        SQLInjections("value; drop table x;");

//        readFile();
//        acceptSocket();

    }

    private static void SQLInjections(String param) {
        Statement s = null;
        try {
//            System.out.println("'" + param.replace("'", "''") + "'");
            System.out.println(s.enquoteLiteral(param));
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private static void messageDigest(String message) {
        try {
            var md = MessageDigest.getInstance("SHA-256");
            var digest = md.digest(message.getBytes());
            System.out.println(new BigInteger(1, digest).toString(16)); // NOTE: Truncates leading zeros!!
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void checkPermissions() {
        Permission socketPermission = new SocketPermission("localhost:9999", "accept, connect, listen");
        Permission filePermission = new FilePermission(System.getProperty("user.home"), "read, write");
        try {
            AccessController.checkPermission(socketPermission);
            AccessController.checkPermission(filePermission);
        } catch (AccessControlException e) {
            /*
                Access denied by policies
                See: $JAVA_HOME/conf/security/java.security
                HINT: Execute "grep policy.url cat $JAVA_HOME/conf/security/java.security", and then check each security policy file
            */
            e.printStackTrace();
        }
    }

    private static void doPrivileged() {
        AccessController.doPrivileged((PrivilegedAction<String>) () -> {
                    try {
                        return Files.readString(Path.of("pom.xml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "";
                    }
                }
        );

        System.setSecurityManager(new SecurityManager());
        // Ensure that even if the caller has full permissions, it is restricted to perform only the read operation.
        Path file = Path.of("will_not_be_created.txt");
        Permission readOnlyPerm = new FilePermission(file.toString(), "read");
        PermissionCollection permissionCollection = readOnlyPerm.newPermissionCollection();
        permissionCollection.add(readOnlyPerm);
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                    try {
                        Files.writeString(file, "Kooo");
                    } catch (AccessControlException e) {
                        System.out.println("As expected ;) :" + e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                , new AccessControlContext(new ProtectionDomain[]{
                        new ProtectionDomain(null, permissionCollection)
                })
        );
    }


    /////////////////////
    private static void readFile() {
        var fileSystem = FileSystems.getDefault();
        try {
            System.out.println(Path.of(System.getProperty("user.home")).toRealPath().toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void acceptSocket() {
        try {
            var serverSocket = new ServerSocket(8888);
            var accept = serverSocket.accept();
            System.out.println("accepted");
            accept.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
