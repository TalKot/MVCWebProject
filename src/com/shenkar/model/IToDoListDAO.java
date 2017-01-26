package com.shenkar.model;
import java.util.*;

public interface IToDoListDAO {
	/*********************************Users methods*********************************/
		public void addUser(user obj)throws userAndTaskException;
		public List getUsers()throws userAndTaskException;
		public user getUser(int userID, String Password) throws userAndTaskException;
		public user getUserWithourPassword(int userID)throws userAndTaskException;
		public boolean checkUserInDB (int userID, String Password)throws userAndTaskException;
		public String deleteUser(int userID, String Password)throws userAndTaskException;
	/*********************************Tasks methods*********************************/
		public void addTask(task obj)throws userAndTaskException;
		public void ChangeStatus(int TaskNumber)throws userAndTaskException;
		public List<task> getTasksForUser(int id)throws userAndTaskException;
		public List<task> getTasksForUserClosed(int id)throws userAndTaskException;
		public void updateTask(int taskNumber, String taskName, String description)throws userAndTaskException;
		public void deleteTask(int taskNumber)throws userAndTaskException;
		public task getTask(int taskID)throws userAndTaskException;	
}
