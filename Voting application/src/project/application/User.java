package project.application;

import org.json.simple.JSONObject;

public class User {
    private int id;
    private String username;
    private String password;
    private int roles_id;
    
    public User(){
        
    }
    
    public User(String username, String password, int roles_id) {
		this.username = username;
		this.password = password;
		this.roles_id = roles_id;
	}
    
    public User(int id, String username, String password, int roles_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles_id=roles_id;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 
    
    public int getRoles_id() {
        return this.roles_id;
    }

    public void setRoles_id(int roles_id) {
        this.roles_id = roles_id;
    }
   
     @Override
    public String toString() {
        return this.toString(false);
    }
    
    public String toString(boolean toJSON) {
        JSONObject us = new JSONObject();
        
        if(toJSON) {
            us.put("id", this.getId());
            us.put("username", this.getUsername());
            us.put("password", this.getPassword());
            us.put("roles_id", this.getRoles_id());
            return us.toJSONString();
        }
        else
            return "User: [id = " + this.getId() + ", " + "Userame = " + this.getUsername() + ", " + "Password = " + this.getPassword() + ", "+ "Role id = " + this.getRoles_id()+ "]";
    }
    
}
