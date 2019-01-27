package project.application;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
import static project.application.ConsoleUtils.clearConsole;
import static project.application.ProjectApplication.db;

public class ConsoleApp {
    private User user;
    UsersCRUD u = new UsersCRUD(); ListsCRUD l = new ListsCRUD(); Answers_InfoCRUD a = new Answers_InfoCRUD();
    Files file = new Files();

    public ConsoleApp(User user){
        this.user=user;
    }
       
    public void mainMenu() {
		Menu menu = new Menu();
		menu.setTitle("*** App Main Menu ***");
                

                if (user.getRoles_id()==1 || user.getRoles_id()==2){
                    menu.addItem(new MenuItem("Create", this, "subMenuA"));
                }
                if (user.getRoles_id()==3){
                    menu.addItem(new MenuItem("Answer", this, "performCreateAnswer_Info"));
                }
                menu.addItem(new MenuItem("Update", this, "subMenuB"));
		menu.addItem(new MenuItem("View", this, "subMenuC"));
                if(user.getRoles_id()==1 || user.getRoles_id()==3){
                    menu.addItem(new MenuItem("Delete", this, "subMenuD"));
                }
		menu.execute();
    }
	
    public void subMenuA() {
        clearConsole();
        Menu menu = new Menu();
	menu.setTitle("*** Create ***");
        if(user.getRoles_id()==1){
            menu.addItem(new MenuItem("User",this, "performCreateUser"));
        }
	menu.addItem(new MenuItem("Question", this, "performCreateQuestion"));
	menu.execute();
    }
	
    public void subMenuB() {
        clearConsole();
	Menu menu = new Menu();
	menu.setTitle("*** Update ***");
        if(user.getRoles_id()==1){
        menu.addItem(new MenuItem("User",this, "performUpdateUser")); //username, password & role!           
        }
        if(user.getRoles_id()==3){
        menu.addItem(new MenuItem("My account",this, "performSelfUpdateUser")); //Only username & password!           
        }
        if(user.getRoles_id()==1 || user.getRoles_id()==2){
        menu.addItem(new MenuItem("Question", this, "performUpdateList"));    
        }
	menu.execute();
    }
    
    public void subMenuC() {
        clearConsole();
	Menu menu = new Menu();
	menu.setTitle("*** View ***");
        if(user.getRoles_id()==1 || user.getRoles_id()==2){
            menu.addItem(new MenuItem("Users",this, "performReadUsers"));
        }
	menu.addItem(new MenuItem("Questions", this, "performReadLists"));
	menu.addItem(new MenuItem("Questions By Expire Date", this, "performReadListsByExpireDate"));
	menu.addItem(new MenuItem("Question Outcome", this, "performReadResultsByList"));
        if(user.getRoles_id()==3){
            menu.addItem(new MenuItem("My Answers", this, "performReadResultsByUser_id"));            
        }
	menu.execute();
    }
	
    public void subMenuD() {
        clearConsole();
	Menu menu = new Menu();
	menu.setTitle("*** Delete ***");
        if(user.getRoles_id()==1){
            menu.addItem(new MenuItem("User",this, "performDeleteUser"));
            menu.addItem(new MenuItem("Question", this, "performDeleteList"));
        }
	menu.execute();
    }
    
    public void performCreateUser() {
        u.performCreateUser();
    } 
    
    public void performUpdateUser() {
        u.performUpdateUser();
    } 
    
    public void performSelfUpdateUser(){
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* Your account updated successfully *");
        int id;String username;String password; int roles_id;
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
            username = u.findUserById(user.getId()).getUsername();
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
            password = u.findUserById(user.getId()).getPassword();
        }
        id = u.findUserById(user.getId()).getId();
        roles_id = u.findUserById(user.getId()).getRoles_id();
        User n= new User(id, username, password, roles_id);
        u.updateUser(n);
        file.logToFile("User number "+user.getId()+" updated its account. New username: "+username+" and new password: "+password+".\n");
        menu.execute();
    }
    
    public void performDeleteUser() {
        u.performDeleteUser();
    }

    public void performReadUsers(){
        u.performReadUsers();
    }
    
    public void performCreateList(){
        l.performCreateList();
    }
    
    public void performUpdateList(){
        l.performUpdateList();
    }
    
    public void performDeleteList(){
        l.performDeleteList();
    }
    
    public void performReadLists(){
        l.performReadLists();
    }
    
    public void performReadListsByExpireDate(){
        l.performReadListsByExpireDate();
    }
    
    public void performCreateAnswer_Info() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* Thank you for your answer *");
        int users_id; int lists_id=0; int answers_id=0; Date date_of_answer;
        LocalDate today = LocalDate.now();
        date_of_answer = Date.valueOf(today);
        users_id = user.getId();
        System.out.println("Please select the Question id: "); 
        while (lists_id==0){
            Scanner input= new Scanner(System.in);
            while (!input.hasNextDouble()){ 
                System.out.println("This is not a number! Try again: ");
                input.next();
            }
            int li=input.nextInt();
            if (li>0){
                if (db.checkListsIdExists(li)){
                    if (db.checkOpenQuestions(li).after(date_of_answer)){
                        if (!db.checkAnsweredAlready(users_id, li)){
                            lists_id=li;
                        }else{
                            System.out.println("You have already answered this question. Try another one!");
                        }
                    }else{
                        System.out.println("This question is not available anymore. Try another one!");
                    }
                }else{
                    System.out.println("This question does not exist. Try again!");
                }    
            }else{
                System.out.println("The id should be greater than 0! Try again: ");
            }     
        }
        System.out.println(l.findListById(lists_id).getQuestion());
        System.out.println("Please enter '1' for Yes or '2' for No: ");
        while(answers_id==0){
            Scanner input= new Scanner(System.in);
            while (!input.hasNextDouble()){ 
                System.out.println("This is not a number. The input should be 1 for Yes or 2 for No! Try again: ");
                input.next();
            }
            int ai=input.nextInt();
            if (ai==1 || ai==2){
                answers_id=ai;
            }else{
                System.out.println("The input should be 1 for Yes or 2 for No! Try again: ");
            }
        }
        Answer_Info n = new Answer_Info(users_id, lists_id, answers_id, date_of_answer);
        a.saveAnswer_Info(n);
        l.findResultsByList(lists_id);
        file.logToFile("User number "+users_id+" answered the question number"+lists_id+". The answer was ('1' for Yes or '2' for No): "+answers_id+" and the date of answer: "+date_of_answer+".\n");        
        menu.execute();
    } 
    
    public void performReadResultsByUser_id(){
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* These are your answers so far. *");
        int users_id = user.getId();
        file.logToFile("User number "+users_id+" looked for its answer history.\n");
        a.findResultsByUser_id(users_id);        
        menu.execute();
    }
    
    public void performReadResultsByList(){
        l.performReadResultsByList();
    }
    
}
