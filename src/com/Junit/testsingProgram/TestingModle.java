package com.Junit.testsingProgram;

import static org.junit.Assert.*;
import org.junit.Test;

import com.shenkar.model.HibernateToDoListDAO;

public class TestingModle {

	@Test
	public void testNoUserPasswordInDB() {
		//JUnitT test = new JUnit();

		boolean test  = HibernateToDoListDAO.Instance().CheckUserInDB(8, "8");
		assertEquals(false, test);
		
	}

}
