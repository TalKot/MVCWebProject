package com.shenkar.controller;

import com.shenkar.model.HibernateToDoListDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ProgramController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		switch (path) 
		{
			
			case "/clientList":
				dispatcher = getServletContext().getRequestDispatcher("/clientList.jsp");
				List vec = HibernateToDoListDAO.Instance().getUsers();
				request.setAttribute("UsersList", vec);
				dispatcher.forward(request, response);
				break;

			case "/help":
				dispatcher = getServletContext().getRequestDispatcher("/help.jsp");
				dispatcher.forward(request, response);
				break;
			
			case "/ToDoList":
				dispatcher = getServletContext().getRequestDispatcher("/ToDoList.jsp");
				dispatcher.forward(request, response);
				break;
								
			case "/ToDoListForm":
				dispatcher = getServletContext().getRequestDispatcher("/ToDoListForm.jsp");
				dispatcher.forward(request, response);
				break;
			//also in lower case
			case "/todolistform":
				dispatcher = getServletContext().getRequestDispatcher("/ToDoListForm.jsp");
				dispatcher.forward(request, response);
				break;
			default:
				dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				dispatcher.forward(request, response);
				break;			
		}
	}
	
	public void addTask2DB(String id,String task,String description, String strUserAgent)
	{
		HibernateToDoListDAO.Instance().addTask(id,task, description,strUserAgent);
	}
}
