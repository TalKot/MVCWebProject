package com.shenkar.model;

import org.apache.jasper.tagplugins.jstl.core.Set;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User abc = new User();
		abc.setEmail("aaa@aaa.com");
		abc.setFirstName("shlomi");
		abc.setId(123);
		abc.setLastName("slgkhk");
		abc.setPhoneNumber(123214);
		
		HibernateToDoListDAO.Instance().addUser(abc);
		System.out.println("complete!");
	}

}
