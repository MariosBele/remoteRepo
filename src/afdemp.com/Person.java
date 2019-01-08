package Package1;
public class Person{
    private String first_name;
    private String last_name;
    private int age;
    private String sex;
    private String fav_season;
    
    public String getFirstName(){
        return first_name;
    }
    public void setFirstName(String first_name){
        this.first_name=first_name;
    }
    public String getLastName(){
        return last_name;
    }
    public void setLastName(String last_name){
        this.last_name=last_name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getFav_Season(){
        return fav_season;
    }
    public void setFavSeason(String fav_season){
        this.fav_season=fav_season;
    }
    public String toString(){
        return "Name: " +first_name+" "+last_name+" \nAge: "+ age+", Sex: "+ sex+" \nFavorite Season: "+fav_season;
    }
    
}