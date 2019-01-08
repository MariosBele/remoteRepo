package project.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_eu {
    
    private static final String DB_URL2 = "127.0.0.1:3306";
    private static final String DB_NAME2 = "eu";
    private static final String DB_USER2 = "root";
    private static final String DB_PASSWD2 = "root";
    private static final String FULL_DB_URL2 = "jdbc:mysql://" + DB_URL2 + "/" + DB_NAME2 + "?zeroDateTimeBehavior=convertToNull";

    
    public boolean checkEUusername(String eu_username) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        String query = "SELECT * FROM `eu`.`app_rqs` WHERE eu_username = '" + eu_username + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL2, DB_USER2, DB_PASSWD2);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            result = resultSet.first();
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean checkEUpassword(String eu_username, String eu_password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        String query = "SELECT * FROM `eu`.`app_rqs` WHERE eu_username = '" + eu_username + "'"+ " AND eu_password = '" + eu_password + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL2, DB_USER2, DB_PASSWD2);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            result = resultSet.first();
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}    
    