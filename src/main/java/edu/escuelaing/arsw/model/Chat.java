package edu.escuelaing.arsw.model;

public class Chat {
	private String content;
	private String user;
	private UserStateChat type;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public UserStateChat getType() {
		return type;
	}
	public void setType(UserStateChat type) {
		this.type = type;
	}
	
	
	
}
