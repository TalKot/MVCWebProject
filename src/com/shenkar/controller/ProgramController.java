package com.shenkar.controller;

import com.shenkar.model.HibernateToDoListDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.ResponseAPDU;

import org.apache.catalina.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ProgramController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;	


	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		/*
		Session session =(Session) request.getSession();
		if(((ServletRequest) session).getAttribute("tasks")==null)
		{
			session.setAttribute("tasks", new LinkedList<String>());
		}
		LinkedList<String> list = (LinkedList<String>) request.getAttribute("tasks");
		*/
		
		
		/*
		LinkedList<String> list;
		if(request.getAttribute("task")==null)
		{
			list = new LinkedList <String>();
		}
		else
		{
			list = (LinkedList)request.getAttribute("task");
		}
		*/
		System.out.println("Creating new linkedlist");
		LinkedList<String> list = new LinkedList <String>();
		System.out.println("getting parameters from Form");
		String taskName = request.getParameter("taskname");
		String taskDescription = request.getParameter("taskdescription");		
		list.add(taskName +"===>"+ taskDescription);
		System.out.println("Entered data into list");
		System.out.println(list.getFirst().toString());
		
		
		String path = request.getPathInfo();
		//String path = (String)request.getAttribute("path");
		//System.out.println("The Path is - "+path);
		
		//String path = "ToDOList";
		System.out.println("The path is - "+path);
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
				request.setAttribute("Name","Tal");
				request.setAttribute("LastName","Kot");
				request.setAttribute("tasks", list);
				dispatcher.forward(request, response);
				break;
								
			case "/ToDoListForm":case "/todolistform":
				dispatcher = getServletContext().getRequestDispatcher("/ToDoListForm.jsp");
				dispatcher.forward(request, response);
				break;

			default:
				//dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				//dispatcher.forward(request, response);
				//break;			
		}
		/*
			//adding the user-agent to the DB also		
			String strUserAgent = request.getHeader("User-Agent");
			if(strUserAgent.contains("Chrome"))
			{
				strUserAgent = "Chrome";
			}
			else if(strUserAgent.contains("OS"))
			{
				strUserAgent = "Safari";
			}
			else if(strUserAgent.contains("Firefox"))
			{
				strUserAgent = "Firefox";
			}
			else strUserAgent = "Explorer/Edge";
	
			addTask2DB(request.getRequestedSessionId(),taskName,taskDescription,strUserAgent);
		*/
	}
	
	public void addTask2DB(String id,String task,String description, String strUserAgent)
	{
		
		HibernateToDoListDAO.Instance().addTask(id,task, description,strUserAgent);
	}
}
