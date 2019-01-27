package project.application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Answers_InfoCRUD {
    Answer_Info ai = new Answer_Info();
    Database_app db = new Database_app();
    Files file = new Files();

    public int saveAnswer_Info(Answer_Info ai) {
            String sql = "INSERT INTO `application`.`answers_info`(users_id, lists_id, answers_id, date_of_answer)" 
                                    + "VALUES(?,?,?,?)";
            int id = 0;

            try {
                    PreparedStatement pstmt =db.connectToDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setInt(1, ai.getUsers_id());
                    pstmt.setInt(2, ai.getLists_id());
                    pstmt.setInt(3, ai.getAnswers_id());
                    pstmt.setDate(4, ai.getDate_of_answer());

                    // Creating Answer
                    if (pstmt.executeUpdate() > 0) {
                            ResultSet rs = pstmt.getGeneratedKeys(); // Returns Generated

                            if (rs.next())
                                    id = rs.getInt(1);
                    }
            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return id;
    }

    public void updateAnswer_Info(Answer_Info ai) {
            String sql = "UPDATE `application`.`answers_info` SET users_id=?, lists_id=?, answers_id=?, date_of_answer=? " 
                                    + "WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, ai.getUsers_id());
                    pstmt.setInt(2, ai.getLists_id());
                    pstmt.setInt(3, ai.getAnswers_id());
                    pstmt.setDate(4, ai.getDate_of_answer());
                    pstmt.setInt(5, ai.getId());

                    // Update Answer
                    pstmt.executeUpdate();

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }
    }

    public void deleteAnswer_Info(int id) {
            String sql = "DELETE FROM `application`.`answers_info` WHERE id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, id);

                    // Delete Answer
                    pstmt.executeUpdate();

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }
    }

    public Answer_Info findAnswer_InfoByUsers_id(int users_id) {
            String sql = "SELECT * FROM `application`.`answers_info` WHERE users_id=?";

            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);
                    pstmt.setInt(1, users_id);

                    // Getting Answers of a User
                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                            Answer_Info ai = new Answer_Info();
                            ai.setId(resultSet.getInt(1));
                            ai.setUsers_id(resultSet.getInt(2));
                            ai.setLists_id(resultSet.getInt(3));
                            ai.setAnswers_id(resultSet.getInt(4));
                            ai.setDate_of_answer(resultSet.getDate(5));

                            return ai;
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return null;
    }

    public java.util.List<Answer_Info> findAllAnswers_Info() {
            String sql = "SELECT * FROM `application`.`answers_info`;";
            java.util.List<Answer_Info> asi = null;
            try {
                    PreparedStatement pstmt = db.connectToDB().prepareStatement(sql);

                    // Getting User's Detail
                    ResultSet resultSet = pstmt.executeQuery();
                    while (resultSet.next()) {
                            if (asi == null)
                                    asi = new ArrayList<>();

                            Answer_Info ai = new Answer_Info();
                            ai.setId(resultSet.getInt(1));
                            ai.setUsers_id(resultSet.getInt(2));
                            ai.setLists_id(resultSet.getInt(3));
                            ai.setAnswers_id(resultSet.getInt(4));
                            ai.setDate_of_answer(resultSet.getDate(5));

                            asi.add(ai);
                    }

            } catch (Exception ex) {
                    System.out.println(ex.getMessage());
            }

            return asi;
    }
    
    public void findResultsByUser_id(int users_id){
        Statement st = null;
        ResultSet rs = null;
        String k=null;String w=null;
        
        String query="SELECT answer_result, question FROM application.answers_info\n" +
            "INNER JOIN application.lists\n" +
            "ON `answers_info`.`lists_id`=`lists`.`id` \n" +
            "INNER JOIN application.answers\n" +
            "ON `answers_info`.`answers_id`=`answers`.`id`\n" +
            "WHERE users_id="+users_id+";";
        try {
            st = db.connectToDB().createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                w = (rs.getString(1)); k=(rs.getString(2));
                System.out.println(k + " ANSWERED WITH " + w);
                file.logToFile(k + " ANSWERED WITH " + w+".\n");
            }
        } catch (SQLException ex) { 
            Logger.getLogger(Database_app.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

}
