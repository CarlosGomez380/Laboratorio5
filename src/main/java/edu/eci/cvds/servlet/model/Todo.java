package edu.eci.cvds.servlet.model;

public class Todo{
	private int userId;
	private int id;
	private String tittle;
	private bool completed;

	public Todo{
		super();
	}

	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;	
	}

	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;	
	}



}
