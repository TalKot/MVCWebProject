package com.Junit.testsingProgram;

import static org.junit.Assert.*;
import org.junit.Test;

import com.shenkar.model.hibernateToDoListDAO;
import com.shenkar.model.user;
import com.shenkar.model.userAndTaskException;

public class testingModle {

	@Test
	public void testNoUserPasswordInDB() throws userAndTaskException {
		//there is not client 8 in the DB - test should be false
		boolean test  = hibernateToDoListDAO.getInstance().checkUserInDB(8, "8");
		assertEquals(false, test);
	}
	
	@Test
	public void testUserPasswordInDB() throws userAndTaskException{
		//there is  client 123 in the DB - test should be true
		boolean test  = hibernateToDoListDAO.getInstance().checkUserInDB(123, "123");
		assertEquals(true, test);
	}
	
	@Test
	public void testAddingUser() throws userAndTaskException{
		user obj = new user("test", "test", 6666, 123, "test@test.com", "6666","Chrome");
		hibernateToDoListDAO.getInstance().addUser(obj);
		boolean test  = hibernateToDoListDAO.getInstance().checkUserInDB(6666, "6666");
		assertEquals(true, test);
	}
	
	@Test
	public void testDeletingUser() throws userAndTaskException{
		hibernateToDoListDAO.getInstance().deleteUser(6666, "6666");
		boolean test  = hibernateToDoListDAO.getInstance().checkUserInDB(6666, "6666");
		assertEquals(false, test);
	}
}
