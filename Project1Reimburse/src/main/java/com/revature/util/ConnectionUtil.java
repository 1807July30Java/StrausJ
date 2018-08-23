package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static Connection getConnectionFromFile(String filename) throws SQLException, IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        prop.load(loader.getResourceAsStream(filename));

        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
