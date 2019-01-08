package project.application;

import java.util.Scanner;

public class ProjectApplication {
    static Database_app db;
    static Database_eu db2;

    ProjectApplication(){
    db = new Database_app();
    db2 = new Database_eu(); 
    }

    public static void main(String[] args) {
        new ProjectApplication();
        String username;String password;
        //Sign up OR Log in?
        Login l = new Login();
        UsersCRUD user = new UsersCRUD();
        Files file = new Files();
        if (l.login().toLowerCase().equals("log in")){
            l.lCheckUsername();
            l.lCheckPassword();
            System.out.println("Welcome back");
            username=l.getUsername();
            password=l.getPassword();
        }
        //If Sign Up selected. We have to use the Database from EU.
        else{
            l.lSignUpUsername();
            l.lSignUpPassword();
            System.out.println("Welcome to Application! We need you to create your account. \nPlease enter a username: ");
            username = new Scanner(System.in).nextLine();
            while (db.checkUsername(username)){
                System.out.println("This username is not available. Try another one!");
                username = new Scanner(System.in).nextLine();
            }
            System.out.println("Please enter a Password: ");
            password = new Scanner(System.in).nextLine();
            //Whatever our system security should check, it will be contained in a while loop right here.
            User n = new User(username,password,3);
            user.saveUser(n);
        }
        file.setFileName(username);
        User user1 = new User(user.findUserByUsername(username).getId(),username,user.findUserByUsername(username).getPassword(),user.findUserByUsername(username).getRoles_id());
        ConsoleApp cons = new ConsoleApp(user1);
        cons.mainMenu();
        file.closeFile();
    }

                   
}
