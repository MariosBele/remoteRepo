package project.application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_app {
    
    private static final String DB_URL = "localhost:3306";
    private static final String DB_NAME = "application";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "root";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/" + DB_NAME + "?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";

    public boolean checkUsername(String username) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
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
    
    public boolean checkPassword(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        String query = "SELECT * FROM `application`.`users` WHERE username = '" + username + "'"+ " AND password = '" + password + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
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
    
    public boolean checkAnsweredAlready(int users_id, int lists_id){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        String query = "SELECT * FROM `application`.`answers_info` WHERE users_id = '" + users_id + "'"+ " AND lists_id = '" + lists_id + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
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
    
    public Date checkOpenQuestions(int id){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        Date datetype= null;
        String query = "SELECT * FROM `application`.`lists` WHERE id = '" + id + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                datetype = rs.getDate(3);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return datetype;
    }
    
    public boolean checkUsersIdExists(int id){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        String query = "SELECT * FROM `application`.`users` WHERE id = '" + id + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
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
    
    public boolean checkListsIdExists(int id){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        String query = "SELECT * FROM `application`.`lists` WHERE id = '" + id + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
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
    
    public boolean checkDate_expiresExists(Date date_expires){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        String query = "SELECT * FROM `application`.`lists` WHERE date_expires = '" + date_expires + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
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
    
    public int getRoleByUsername(String username){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        int role= 0;
        String query = "SELECT * FROM `application`.`users` WHERE username = '" + username + "'";
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                role = rs.getInt(4);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    
    }
    
    public Connection connectToDB() {
        Connection connection = null; 
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
        } catch (SQLException ex) {
            Logger.getLogger(Database_app.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return connection;
    }
}
