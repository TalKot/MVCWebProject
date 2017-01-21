package com.shenkar.controller;

import com.shenkar.model.HibernateToDoListDAO;
import com.shenkar.model.Task;
import com.shenkar.model.User;

public class testingMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
	*/
		
		User user = new User("tal", "kot", 123, 123, "ASD", "123");
		HibernateToDoListDAO.Instance().addUser(user);
		Task a = new Task(123, "aa", "bb", "Open");
		HibernateToDoListDAO.Instance().addTask(a);
		
		System.out.println("hello");
		/*for(Task tsk:HibernateToDoListDAO.Instance().getTasksForUser(123))
		{
			System.out.println(tsk.getTask());
			//HibernateToDoListDAO.Instance().deleteTask(tsk.getTaskNumber());
		}
		//String str = HibernateToDoListDAO.Instance().deleteUser(123, "123");
*/
	}

}

