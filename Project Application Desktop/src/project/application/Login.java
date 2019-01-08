package project.application;

import java.util.Scanner;
import static project.application.ProjectApplication.db;
import static project.application.ProjectApplication.db2;

public class Login {
    
    private String username;
    private String eu_username;
    private String password;

    public String getUsername() {
        return this.username;
    }
    
    public String getEUusername() {
        return this.eu_username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String login(){
        System.out.print("Welcome to the Application \nDo you want to Log in or Sign up?");
        String input= new Scanner(System.in).nextLine();
        while (!(input.toLowerCase().equals("log in") || input.toLowerCase().equals("sign up"))){
            System.out.println("Wrong input! Please write \"Log in\" or \"Sign up\" ");
            input = new Scanner(System.in).nextLine();
        }
        return input;
    }
    
    public String lCheckUsername(){
        System.out.println("Please enter your Username: ");
        username = new Scanner(System.in).nextLine();
        while (!db.checkUsername(username)){
            System.out.println("This username does not exist. Try again!");
            username = new Scanner(System.in).nextLine();
        }
        return username;
    }
    
    public String lCheckPassword(){
        System.out.println("Please enter your Password: ");
        password = new Scanner(System.in).nextLine();
        while (!db.checkPassword(username,password)){
            System.out.println("Wrong password. Try again!");
            password = new Scanner(System.in).nextLine();
        }
        return password;
    }
    
    public String lSignUpUsername(){
        System.out.println("Please enter your given username: ");
        eu_username = new Scanner(System.in).nextLine();
        while (!db2.checkEUusername(eu_username)){
            System.out.println("This username does not exist. Try again!");
            eu_username = new Scanner(System.in).nextLine();
        }
        return eu_username;
    }
    
    public String lSignUpPassword(){
        System.out.println("Please enter your given password: ");
        String eu_password = new Scanner(System.in).nextLine();
        while (!db2.checkEUpassword(eu_username,eu_password)){
            System.out.println("Wrong password. Try again!");
            eu_password = new Scanner(System.in).nextLine();
        }
        return eu_password;
    }
}
