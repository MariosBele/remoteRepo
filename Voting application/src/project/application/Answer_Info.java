package project.application;

import java.sql.Date;
import org.json.simple.JSONObject;

public class Answer_Info {
    private int id;
    private int users_id;
    private int lists_id;
    private int answers_id;
    private Date date_of_answer;
    
    public Answer_Info(){}
    
    public Answer_Info(int users_id, int lists_id, int answers_id, Date date_of_answer){
        this.users_id=users_id;
        this.lists_id=lists_id;
        this.answers_id=answers_id;
        this.date_of_answer=date_of_answer;
    }
    
    private Answer_Info(int id, int users_id, int lists_id, int answers_id, Date date_of_answer){
        this.id=id;
        this.users_id=users_id;
        this.lists_id=lists_id;
        this.answers_id=answers_id;
        this.date_of_answer=date_of_answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getLists_id() {
        return lists_id;
    }

    public void setLists_id(int lists_id) {
        this.lists_id = lists_id;
    }

    public int getAnswers_id() {
        return answers_id;
    }

    public void setAnswers_id(int answers_id) {
        this.answers_id = answers_id;
    }

    public Date getDate_of_answer() {
        return date_of_answer;
    }

    public void setDate_of_answer(Date date_of_answer) {
        this.date_of_answer = date_of_answer;
    }
    
     @Override
    public String toString() {
        return this.toString(false);
    }
    
    public String toString(boolean toJSON) {
        JSONObject ans = new JSONObject();
        
        if(toJSON) {
            ans.put("id", this.getId());
            ans.put("users_id", this.getUsers_id());
            ans.put("lists_id", this.getLists_id());
            ans.put("answers_id", this.getAnswers_id());
            ans.put("date_of_answer", this.getDate_of_answer());
            return ans.toJSONString();
        }
        else
            return "Answer information: [id = " + this.getId() + ", " + "User id = " + this.getUsers_id() + ", " + "Question id = " + this.getLists_id()+ ", " + "Answer = " + this.getAnswers_id()+", " + "Date of answer = " + this.getDate_of_answer()+"]";
    }
}
