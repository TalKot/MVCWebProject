package com.shenkar.model;
import java.util.*;

public interface IToDoListDAO {
	/*********************************Users methods*********************************/
		//this method should add a new user to the DB
		public void addUser(user obj)throws userAndTaskException;
		//return a list of all users in the DB for clientList page
		public List getUsers()throws userAndTaskException;
		//method to return specific user by userID and password
		public user getUser(int userID, String Password) throws userAndTaskException;
		//check if user can be found in the DB
		public boolean checkUserInDB (int userID, String Password)throws userAndTaskException;
		//delete user from DB - return string with confirmation of reason why cannot delete
		public String deleteUser(int userID, String Password)throws userAndTaskException;
	/*********************************Tasks methods*********************************/
		//adding task to DB
		public void addTask(task obj)throws userAndTaskException;
		//change task's status from Open to complete
		public void changeStatus(int TaskNumber)throws userAndTaskException;
		//return list of tasks object - open tasksk only
		public List<task> getTasksForUser(int id)throws userAndTaskException;
		//return list of tasks object - close tasksk only
		public List<task> getTasksForUserClosed(int id)throws userAndTaskException;
		//update task name or description by task number
		public void updateTask(int taskNumber, String taskName, String description)throws userAndTaskException;
		//delete task from DB
		public void deleteTask(int taskNumber)throws userAndTaskException;
}
