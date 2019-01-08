package project.application;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static project.application.ConsoleUtils.clearConsole;

public class ListsCRUD {

    List list = new List();
    Database_app db = new Database_app();
    Files file = new Files();
    private static DecimalFormat df2 = new DecimalFormat(".##");
    
    public static boolean isValidDate(String inDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(inDate.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }
    
    public int saveList(List list) {
            String sql = "INSERT INTO `application`.`lists`(question, date_expires)" 
                                    + "VALUES(?,?)";
            int id = 0;

            try {
                    PreparedStatement pstmt =db.connectToDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, list.getQuestion());
                    pstmt.setDate(2, list.getDate_expires());

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

    public void updateList(List list) {
            String sql = "UPDATE `application`.`lists` SET question=?, date_expires=? " 
                                    + "WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setString(1, list.getQuestion());
                    pstmt.setDate(2, list.getDate_expires());
                    pstmt.setInt(3, list.getId());

                    // Update User Account
                    pstmt.executeUpdate();

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }
    }

    public void deleteList(int id) {
            String sql = "DELETE FROM `application`.`lists` WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, id);

                    // Delete User Account
                    pstmt.executeUpdate();

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }
    }

    public List findListById(int id) {
            String sql = "SELECT * FROM `application`.`lists` WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, id);

                    // Getting User Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                            List list = new List();
                            list.setId(resultSet.getInt(1));
                            list.setQuestion(resultSet.getString(2));
                            list.setDate_expires(resultSet.getDate(3));

                            return list;
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return null;
    }

    public java.util.List<List> findAllLists() {
            String sql = "SELECT * FROM `application`.`lists`;";
            java.util.List<List> lists = null;
            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);

                    // Getting User's Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    while (resultSet.next()) {
                            if (lists == null)
                                    lists = new ArrayList<>();

                            List list = new List();
                            list.setId(resultSet.getInt(1));
                            list.setQuestion(resultSet.getString(2));
                            list.setDate_expires(resultSet.getDate(3));

                            lists.add(list);
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return lists;
    }
    
    public java.util.List<List> findListsByExpireDate(Date date_expires) {
            String sql = "SELECT * FROM `application`.`lists` WHERE date_expires = '"+date_expires+"'";
            java.util.List<List> lists = null;
            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);

                    // Getting User's Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    while (resultSet.next()) {
                            if (lists == null)
                                    lists = new ArrayList<>();

                            List list = new List();
                            list.setId(resultSet.getInt(1));
                            list.setQuestion(resultSet.getString(2));
                            list.setDate_expires(resultSet.getDate(3));

                            lists.add(list);
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return lists;
    }        

    public void performCreateList() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* Question created successfully *");
        System.out.println("Please type the Question: ");
        String question = new Scanner(System.in).nextLine();
        System.out.println("Please enter the expire date: ");
        String date_expires = new Scanner(System.in).nextLine();
        List n = new List(question, Date.valueOf(date_expires));
        saveList(n);
        file.logToFile("Admin created the question: \""+question+"\" which expires at "+date_expires+".");
        menu.execute();
    } 
    
    public void performUpdateList() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* Question updated successfully *");
        int id=0;String question;String date_expires;
        System.out.println("Select the id of the question: ");
        while (id==0){
            Scanner input= new Scanner(System.in);
            while (!input.hasNextDouble()){ 
                System.out.println("This is not a number! Try again: ");
                input.next();
            }
            int i=input.nextInt();
            if (i>0){
                if (db.checkListsIdExists(i)){
                    id=i;
                }else{
                    System.out.println("This question does not exist. Try again!");
                }
            }else{
                System.out.println("The id should be greater than 0! Try again: ");
            }
        }
        findListById(id);
        System.out.println("Do you want to update the question? (y/n) ");
        String inputQ = new Scanner(System.in).nextLine();
        while (!(inputQ.toLowerCase().equals("y") || inputQ.toLowerCase().equals("n"))){
            System.out.println("Insert 'y' for yes or 'n' for no...");
            inputQ = new Scanner(System.in).nextLine();
        }
        if (inputQ.toLowerCase().equals("y")){
            System.out.println("Please enter the new Question: ");
            question = new Scanner(System.in).nextLine();
        }else{
            question = findListById(id).getQuestion();
        }
        System.out.println("Do you want to update the expire date? (y/n) ");
        String inputD = new Scanner(System.in).nextLine();
        while (!(inputD.toLowerCase().equals("y") || inputD.toLowerCase().equals("n"))){
            System.out.println("Insert 'y' for yes or 'n' for no...");
            inputD = new Scanner(System.in).nextLine();
        }
        if (inputD.toLowerCase().equals("y")){
            System.out.println("Please enter the new expire date: ");
            date_expires = new Scanner(System.in).nextLine();
            while(!isValidDate(date_expires)){
                System.out.println("Insert valid date like: YYYY-MM-DD");
                date_expires = new Scanner(System.in).nextLine();
            }
        }else{
             date_expires = findListById(id).getDate_expires().toString();
        }
        List n= new List(id, question, Date.valueOf(date_expires) );
        updateList(n);
        file.logToFile("Admin updated the question number "+id+". New question: \""+question+"\", New expire date: "+date_expires+".");
        menu.execute();
    } 
    
    public void performDeleteList() {
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* Question deleted successfully *");
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
                if (db.checkListsIdExists(i)){
                    id=i;
                }else{
                    System.out.println("This question does not exist. Try again!");
                }
            }else{
                System.out.println("The id should be greater than 0! Try again: ");
            }
        
        }
        deleteList(id);
        file.logToFile("Admin deleted the question number "+id+".");
        menu.execute();
    }
        
    public void performReadLists(){
        clearConsole();
	Menu menu = new Menu();
        menu.setTitle("* These are all the questions *");
//        UsersCRUD u = new UsersCRUD();
        java.util.List<List> lQuestion = findAllLists();
        System.out.println();
        // forEach Iterator using lamda expression, simple toString()
        lQuestion.forEach(lis -> System.out.println(lis.toString()));
        System.out.println();
        // forEach Iterator using lamda expression, toString() with JSON Object
        //lUser.forEach(us -> System.out.println(us.toString(true)));
        file.logToFile("User viewed all the questions.");
        lQuestion.forEach(lis -> file.logToFile(lis.toString(true)));
        menu.execute();
    }
    
    public void performReadListsByExpireDate(){
        clearConsole();
	Menu menu = new Menu();
        int k=0;String date_expires=null;
        System.out.println("Enter the requested date (YYYY-MM-DD): ");
        while (k==0){
            String input = new Scanner(System.in).nextLine();
            while(!isValidDate(input)){
                System.out.println("Insert valid date like: YYYY-MM-DD");
                input = new Scanner(System.in).nextLine();
            }
            String de=input;
            if (db.checkDate_expiresExists(Date.valueOf(de))){
                date_expires=de;
                k=1;
            }else{
                System.out.println("There is not question which expires on "+ de+".Ask for another date.");
            }
        }
        java.util.List<List> lQuestion = findListsByExpireDate(Date.valueOf(date_expires));
        System.out.println();
        // forEach Iterator using lamda expression, simple toString()
        lQuestion.forEach(lis -> System.out.println(lis.toString()));
        System.out.println();
        // forEach Iterator using lamda expression, toString() with JSON Object
        //lUser.forEach(us -> System.out.println(us.toString(true)));
        file.logToFile("User viewed the questions which expire at "+ date_expires +".");
        lQuestion.forEach(lis -> file.logToFile(lis.toString(true)));
        menu.setTitle("* These are the questions which expire at "+ date_expires +"!*");
        menu.execute();
    }
    
    public void findResultsByList(int lists_id){
        Statement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        double k=0;double w=0;
        
        String query1="SELECT COUNT(*) FROM application.answers_info\n "+
            "WHERE lists_id="+lists_id+";";
        String query2="SELECT COUNT(*) FROM application.answers_info\n" +
            "WHERE lists_id="+lists_id+" AND answers_id=1;";
        
        try {
            st = db.connectToDB().createStatement();
            rs1 = st.executeQuery(query1);
            while (rs1.next()) {
                k =(rs1.getDouble(1));
            }
            rs2 = st.executeQuery(query2);
            while(rs2.next()){
                w =(rs2.getDouble(1));
            }
            double d=(w/k)*100;
            double v=100-d;
            System.out.println(findListById(lists_id).getQuestion()+": "+ df2.format(d) + "% YES, "+df2.format(v)+"% NO" );
            file.logToFile("User viewed the results of question number "+lists_id+".");
            file.logToFile(findListById(lists_id).getQuestion()+": "+ df2.format(d) + "% YES, "+df2.format(v)+"% NO");
        } catch (SQLException ex) { 
            Logger.getLogger(Database_app.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void performReadResultsByList(){
        clearConsole();
	Menu menu = new Menu();
        int lists_id;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of requested question: ");
        while (!input.hasNextDouble()){ 
            System.out.println("This is not a number! Try again: ");
            input.next();
        }
        lists_id=input.nextInt();
        while (lists_id<1){
            Scanner input1 = new Scanner(System.in);
            System.out.println("The id should be greater than 0! Try again: ");
            lists_id=input1.nextInt();
        }
        findResultsByList(lists_id);
        menu.setTitle("* These are the results of question number "+lists_id+"!*");
        menu.execute();
    }
}
