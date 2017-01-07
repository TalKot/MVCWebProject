package com.shenkar.controller;

import com.shenkar.model.HibernateToDoListDAO;

public class pr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(HibernateToDoListDAO.Instance().getUser(1,"123").toString());
		System.out.println(HibernateToDoListDAO.Instance().getHelloWorld());
	}

}
