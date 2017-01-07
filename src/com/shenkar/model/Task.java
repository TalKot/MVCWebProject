package com.shenkar.model;

public class Task {
	private int ClientID;
	private String task;
	private String description;
	private int taskNumber;
	
	public Task(){}
	
	public Task(int clientID, String task, String description) {
		this.ClientID = clientID;
		this.task = task;
		this.description = description;
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

	public int getClientID() {
		return ClientID;
	}


	public void setClientID(int clientID) {
		ClientID = clientID;
	}


	public int getTaskNumber() {
		return taskNumber;
	}


	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}
	
	@Override
	public String toString() {
		return "Task [ClientID=" + ClientID + ", task=" + task + ", description=" + description + "]";
	}

}
