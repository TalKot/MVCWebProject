package com.shenkar.model;

public class Task {
	private int ClientID;
	private String task;
	private String description;
	private int taskNumber;
	private String status;
	
	public Task(){}
	
	public Task(int clientID, String task, String description,String status) {
		this.ClientID = clientID;
		this.task = task;
		this.description = description;
		this.status=status;
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task for -" + ClientID + ", task=" + task + ", description=" + description + ", - "+status;
	}



}
