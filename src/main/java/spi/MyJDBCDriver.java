package spi;

import java.sql.*;

// See "module-info.java"
public class MyJDBCDriver /*extends MyAbstractJDBCDriver*/ {

    /**
     * If a service provider has {@code public static provider()} method,
     * then it is not required for the service provider to be a subtype of the service type.
     * Only the provider method should return a subtype of the service type
     */
    public static Driver provider() {
        return new MyAbstractJDBCDriver() {
        };
    }

}
