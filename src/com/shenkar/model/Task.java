package com.shenkar.model;

public class Task {
	private String task;
	private String description;
	private int id;
	private String ConnectionID;
	private String strUserAgent;
	public Task(){}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getConnectionID() {
		return ConnectionID;
	}

	public void setConnectionID(String connectionID) {
		ConnectionID = connectionID;
	}

	public String getStrUserAgent() {
		return strUserAgent;
	}

	public void setStrUserAgent(String strUserAgent) {
		this.strUserAgent = strUserAgent;
	}
	@Override
	public String toString() {
		return "Task [task=" + task + ", description=" + description + ", id=" + id + ", ConnectionID=" + ConnectionID
				+ ", strUserAgent=" + strUserAgent + "]";
	}
}
