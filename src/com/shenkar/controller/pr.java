package com.shenkar.controller;

import java.util.List;

import com.shenkar.model.HibernateToDoListDAO;
import com.shenkar.model.Task;
import com.shenkar.model.User;

public class pr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.println(HibernateToDoListDAO.Instance().getUser(1,"123").toString());
		System.out.println(HibernateToDoListDAO.Instance().getHelloWorld());
		String str = (String)HibernateToDoListDAO.Instance().getHelloWorld();
		System.out.println(str);
		*/
		/*
		User user1 = new User("Tal", "Kot", 333, 444, "ae@gmail.com", "Abc123");
		User user2 = new User("ROFL", "LOL", 444, 444, "ae@gmail.com", "Abc123");
		User user3 = new User("daddy", "Shimon", 555, 555, "ae@gmail.com", "Abc123");
		HibernateToDoListDAO.Instance().addUser(user1);
		HibernateToDoListDAO.Instance().addUser(user2);
		HibernateToDoListDAO.Instance().addUser(user3);
		*/
		
		/*
		Task task1 = new Task(333, "ah", "guy");
		Task task2 = new Task(444, "ama", "orit");
		Task task3 = new Task(555, "ahot", "noga");
		HibernateToDoListDAO.Instance().addTask(task1);
		HibernateToDoListDAO.Instance().addTask(task2);
		HibernateToDoListDAO.Instance().addTask(task3);
		HibernateToDoListDAO.Instance().addTask(new Task(333, "doda", "nitza"));
		System.out.println("All infomraiton is in the DB!");
		*/
	
		HibernateToDoListDAO.Instance().updateTask(3, "hello", "world");
		
		for (Task task : HibernateToDoListDAO.Instance().getTasksForUser(444)) 
		{
			System.out.println(task.getTaskNumber()+". "+task.getTask()+"==>"+task.getDescription());
		}
			
		HibernateToDoListDAO.Instance().deleteTask(4);

		System.out.println("dONE");
	}

}
