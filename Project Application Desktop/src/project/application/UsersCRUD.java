package project.application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static project.application.ConsoleUtils.clearConsole;

public class UsersCRUD {

    User user = new User();
    Database_app db = new Database_app();
    Files file = new Files();

    public int saveUser(User user) {
            String sql = "INSERT INTO `application`.`users`(username, password, roles_id)" 
                                    + "VALUES(?,?,?)";
            int id = 0;

            try {
                    PreparedStatement pstmt =db.connectToDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, user.getUsername());
                    pstmt.setString(2, user.getPassword());
                    pstmt.setInt(3, user.getRoles_id());

                    // Creating User Account
                    if (pstmt.executeUpdate() > 0) {
                            ResultSet rs = pstmt.getGeneratedKeys(); // Returns Generated
                                                                                                                    // Primary Key

                            if (rs.next())
                                    id = rs.getInt(1);
                    }
            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return id;
    }

    public void updateUser(User user) {
            String sql = "UPDATE `application`.`users` SET username=?, password=?, roles_id=? " 
                                    + "WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setString(1, user.getUsername());
                    pstmt.setString(2, user.getPassword());
                    pstmt.setInt(3, user.getRoles_id());
                    pstmt.setInt(4, user.getId());

                    // Update User Account
                    pstmt.executeUpdate();

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }
    }

    public void deleteUser(int id) {
            String sql = "DELETE FROM `application`.`users` WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, id);

                    // Delete User Account
                    pstmt.executeUpdate();

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }
    }

    public User findUserById(int id) {
            String sql = "SELECT * FROM `application`.`users` WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, id);

                    // Getting User Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getInt(1));
                            user.setUsername(resultSet.getString(2));
                            user.setPassword(resultSet.getString(3));
                            user.setRoles_id(resultSet.getInt(4));

                            return user;
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return null;
    }

    public User findUserByUsername(String username) {
            String sql = "SELECT * FROM `application`.`users` WHERE username=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setString(1, username);

                    // Getting User Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getInt(1));
                            user.setUsername(resultSet.getString(2));
                            user.setPassword(resultSet.getString(3));
                            user.setRoles_id(resultSet.getInt(4));

                            return user;
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return null;
    }

    public java.util.List<User> findAllUsers() {
            String sql = "SELECT * FROM `application`.`users`;";
            java.util.List<User> users = null;
            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);

                    // Getting User's Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    while (resultSet.next()) {
                            if (users == null)
                                    users = new ArrayList<>();

                            User user = new User();
                            user.setId(resultSet.getInt(1));
                            user.setUsername(resultSet.getString(2));
                            user.setPassword(resultSet.getString(3));
                            user.setRoles_id(resultSet.getInt(4));

                            users.add(user);
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return users;
    }
        
    public void performCreateUser() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* User created successfully *");
        System.out.println("Please enter a Username: ");
        String username = new Scanner(System.in).nextLine();
        while (db.checkUsername(username)){
            System.out.println("This username is not available. Try another one!");
            username = new Scanner(System.in).nextLine();
        }
        System.out.println("Please enter a Password: ");
        String password = new Scanner(System.in).nextLine();
        //Whatever our system security should check, it will be contained in a while loop right here.
        int roles_id=0;
        System.out.println("Please enter the number of Role for the new user: ");
        while (roles_id==0){
            Scanner input= new Scanner(System.in);
            while (!input.hasNextDouble()){ 
                System.out.println("This is not a number. Please insert '1' for superAdmin, '2' for Admin or '3' for simple User!");
                input.next();
            }
            int ri=input.nextInt();
            if ((ri==1 || ri==2 || ri==3)){
                roles_id=ri;
            }else{
                System.out.println("This role number is not correct. Please insert '1' for superAdmin, '2' for Admin or '3' for simple User!");
            }
        }
        User n = new User(username,password,roles_id);
        saveUser(n);
        file.logToFile("Admin created a user. Username: "+username+" , Password: "+password+", Role number: "+roles_id+".\n");
        menu.execute();
    } 
    
    public void performUpdateUser() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* User updated successfully *");
        int id=0;String username;String password; int roles_id=0;
        System.out.println("Select the id of the user: ");
        while (id==0){
            Scanner input= new Scanner(System.in);
            while (!input.hasNextDouble()){ 
                System.out.println("This is not a number! Try again: ");
                input.next();
            }
            int i=input.nextInt();
            if (i>0){
                if (db.checkUsersIdExists(i)){
                    id=i;
                }else{
                    System.out.println("This user does not exist. Try again!");
                }
            }else{
                System.out.println("The id should be greater than 0! Try again: ");
            }
        }
        findUserById(id);
        System.out.println("Do you want to update the username? (y/n) ");
        String inputU = new Scanner(System.in).nextLine();
        while (!(inputU.toLowerCase().equals("y") || inputU.toLowerCase().equals("n"))){
            System.out.println("Insert 'y' for yes or 'n' for no...");
            inputU = new Scanner(System.in).nextLine();
        }
        if (inputU.toLowerCase().equals("y")){
            System.out.println("Please enter the new Username: ");
            username = new Scanner(System.in).nextLine();
            while (db.checkUsername(username)){
                System.out.println("This username is not available. Try another one!");
                username = new Scanner(System.in).nextLine();
            }
        }else{
            username = findUserById(id).getUsername();
        }
        System.out.println("Do you want to update the password? (y/n) ");
        String inputP = new Scanner(System.in).nextLine();
        while (!(inputP.toLowerCase().equals("y") || inputP.toLowerCase().equals("n"))){
            System.out.println("Insert 'y' for yes or 'n' for no...");
            inputP = new Scanner(System.in).nextLine();
        }
        if (inputP.toLowerCase().equals("y")){
            System.out.println("Please enter the new Password: ");
            password = new Scanner(System.in).nextLine();
        }else{
            password = findUserById(id).getPassword();
        }
        System.out.println("Do you want to update the number of role? (y/n) ");
        String inputR = new Scanner(System.in).nextLine();
        while (!(inputR.toLowerCase().equals("y") || inputR.toLowerCase().equals("n"))){
            System.out.println("Insert 'y' for yes or 'n' for no...");
            inputR = new Scanner(System.in).nextLine();
        }
        if (inputR.toLowerCase().equals("y")){
            System.out.println("Please enter the new number of role: ");
            while (roles_id==0){
                Scanner input1= new Scanner(System.in);
                while (!input1.hasNextDouble()){ 
                    System.out.println("This is not a number. Please insert '1' for superAdmin, '2' for Admin or '3' for simple User!");
                    input1.next();
                }
                int ri=input1.nextInt();
                if ((ri==1 || ri==2 || ri==3)){
                    roles_id=ri;
                }else{
                    System.out.println("This role number is not correct. Please insert '1' for superAdmin, '2' for Admin or '3' for simple User!");
                }
            }
        }else{
            roles_id = findUserById(id).getRoles_id();
        }
        User n= new User(id, username, password, roles_id);
        updateUser(n);
        file.logToFile("Admin updated the user number "+id+". New username: "+username+", New password: "+password+", New role number: "+roles_id+".\n");
        menu.execute();
    } 
    
    public void performDeleteUser() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* User deleted successfully *");
        int id=0;
        System.out.println("Select the id of the user: ");
        while (id==0){
            Scanner input= new Scanner(System.in);
            while (!input.hasNextDouble()){ 
                System.out.println("This is not a number! Try again: ");
                input.next();
            }
            int i=input.nextInt();
            if (i>0){
                if (db.checkUsersIdExists(i)){
                    id=i;
                }else{
                    System.out.println("This user does not exist. Try again!");
                }
            }else{
                System.out.println("The id should be greater than 0! Try again: ");
            }
        }
        deleteUser(id);
        file.logToFile("Admin deleted the user number "+id+".");
        menu.execute();
    }
        
    public void performReadUsers(){
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* These are the current users of the App *");
//        UsersCRUD u = new UsersCRUD();
        java.util.List<User> lUser = findAllUsers();
        System.out.println();
        // forEach Iterator using lamda expression, simple toString()
        lUser.forEach(us -> System.out.println(us.toString()));
        System.out.println();
        // forEach Iterator using lamda expression, toString() with JSON Object
        //lUser.forEach(us -> System.out.println(us.toString(true)));
        file.logToFile("Admin viewed the current users of the app.");
        lUser.forEach(us -> file.logToFile(us.toString(true)));
        menu.execute();
    }

}

