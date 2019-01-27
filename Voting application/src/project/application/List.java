package project.application;

import java.sql.Date;
import org.json.simple.JSONObject;

public class List {
    private int id;
    private String question;
    private Date date_expires;
    
    public List(){}
    
    public List(String question, Date date_expires){
        this.question=question;
        this.date_expires=date_expires;
    }
 
    public List(int id, String question, Date date_expires){
        this.id=id;
        this.question=question;
        this.date_expires=date_expires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDate_expires() {
        return date_expires;
    }

    public void setDate_expires(Date date_expires) {
        this.date_expires = date_expires;
    }
    
     @Override
    public String toString() {
        return this.toString(false);
    }
    
    public String toString(boolean toJSON) {
        JSONObject lis = new JSONObject();
        
        if(toJSON) {
            lis.put("id", this.getId());
            lis.put("question", this.getQuestion());
            lis.put("date_expires", this.getDate_expires());
            return lis.toJSONString();
        }
        else
            return "List: [id = " + this.getId() + ", " + "Question = " + this.getQuestion() + ", " + "Expire Date = " + this.getDate_expires()+ "]";
    }
    
}
