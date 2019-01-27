package project.application;

public class Answer {
    private int id;
    private String answer_result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer_result() {
        return answer_result;
    }

    public void setAnswer_result(String answer_result) {
        this.answer_result = answer_result;
    }
    
    public Answer(int id, String answer_result ){
        this.answer_result=answer_result;
        this.id=id;
    }
}
