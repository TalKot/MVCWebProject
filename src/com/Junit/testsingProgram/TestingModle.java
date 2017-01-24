package com.Junit.testsingProgram;

import static org.junit.Assert.*;
import org.junit.Test;

import com.shenkar.model.HibernateToDoListDAO;
import com.shenkar.model.User;

public class TestingModle {

	@Test
	public void testNoUserPasswordInDB() {
		//there is not client 8 in the DB - test should be false
		boolean test  = HibernateToDoListDAO.Instance().CheckUserInDB(8, "8");
		assertEquals(false, test);
	}
	
	@Test
	public void testUserPasswordInDB() {
		//there is  client 123 in the DB - test should be true
		boolean test  = HibernateToDoListDAO.Instance().CheckUserInDB(123, "123");
		assertEquals(true, test);
	}
	
	@Test
	public void testAddingUser() {
		User obj = new User("test", "test", 6666, 123, "test@test.com", "6666","Chrome");
		HibernateToDoListDAO.Instance().addUser(obj);
		boolean test  = HibernateToDoListDAO.Instance().CheckUserInDB(6666, "6666");
		assertEquals(true, test);
	}
	
	@Test
	public void testDeletingUser() {
		HibernateToDoListDAO.Instance().deleteUser(6666, "6666");
		boolean test  = HibernateToDoListDAO.Instance().CheckUserInDB(6666, "6666");
		assertEquals(false, test);
	}
}
