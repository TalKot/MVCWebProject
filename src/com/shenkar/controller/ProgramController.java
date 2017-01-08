package com.shenkar.controller;

import com.shenkar.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
		
		/*
		System.out.println("Creating new linkedlist");
		LinkedList<String> list = new LinkedList <String>();
		System.out.println("getting parameters from Form");
		String taskName = request.getParameter("taskname");
		String taskDescription = request.getParameter("taskdescription");		
		list.add(taskName +"===>"+ taskDescription);
		System.out.println("Entered data into list");
		System.out.println(list.getFirst().toString());
		*/

		
		//System.out.println(HibernateToDoListDAO.Instance().getUser(1,"123").toString());
		//String str = (String)HibernateToDoListDAO.Instance().getHelloWorld();
		//System.out.println(str);
		
		String path = request.getPathInfo();
		if(path.contains("controller"))
		{
			path=path.substring(11,path.length()-4);
			System.out.println("after change - Path is - "+path);
		}
		switch (path) 
		{

			case "/Register":
				dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
				String firstName = (String)request.getParameter("FirstName");
				String lastName = (String)request.getParameter("LastName");
				String password = (String)request.getParameter("Password");
				String Email = (String)request.getParameter("Email");
				int phoneNumer = Integer.parseInt(request.getParameter("PhoneNumber"));
				int id = Integer.parseInt(request.getParameter("UserID"));
				User userReg = new User(firstName, lastName, id, phoneNumer, Email, password);
				HibernateToDoListDAO.Instance().addUser(userReg);
				request.setAttribute("MyUser",userReg);
				//request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(333));
				Task task11 = new Task(333, "ah", "guy");
				Task task22 = new Task(444, "ama", "orit");
				Task task33 = new Task(555, "ahot", "noga");
				LinkedList<Task> list = new LinkedList<Task>();
				list.add(task11);
				list.add(task22);
				list.add(task33);
				request.setAttribute("TasksLists",list);
				dispatcher.forward(request, response);
				break;
				
			case "/clientList"://need to fix the code below
				dispatcher = getServletContext().getRequestDispatcher("/clientList.jsp");
				List vec = HibernateToDoListDAO.Instance().getUsers();
				request.setAttribute("UsersList", vec);
				dispatcher.forward(request, response);
				break;
				
			case "/ToDoList":
				dispatcher = getServletContext().getRequestDispatcher("/ToDoList.jsp");
				String taskName = (String)request.getParameter("taskname");
				String taskDescription = (String)request.getParameter("taskdescription");
				System.out.println("The take name is - "+taskName);
				System.out.println("The task desctiption is - "+taskDescription);
				request.setAttribute("taskName",taskName);
				request.setAttribute("taskDescription",taskDescription);
				dispatcher.forward(request, response);
				break;
			
			case "/UserTask":			
				try{
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					
					String Pawword = request.getParameter("Password");				
					int userid = Integer.parseInt(request.getParameter("UserID"));
					//com.shenkar.model.User user = HibernateToDoListDAO.Instance().getUser(userid,Pawword);
					User user = new User("TAL", "KOT", 333, 444, "TALKOT@GMAIL.COM", "333");
					if(user==null)
					{
						System.out.println("User is NOT in the DB");	
					}
					else
					{
						System.out.println("User is in the DB");
					}
					request.setAttribute("MyUser",user);
					Task task1 = new Task(333, "ah", "guy");
					Task task2 = new Task(444, "ama", "orit");
					Task task3 = new Task(555, "ahot", "noga");
					LinkedList<Task> listtemp = new LinkedList<Task>();
					listtemp.add(task1);
					listtemp.add(task2);
					listtemp.add(task3);
					request.setAttribute("TasksLists",listtemp);

					//request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(333));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}			
				dispatcher.forward(request, response);
				break;
				
			case "/ToDoListForm":case "/todolistform":
				dispatcher = getServletContext().getRequestDispatcher("/ToDoListForm.jsp");
				dispatcher.forward(request, response);
				break;
			case "/LoginForm":
				dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
				dispatcher.forward(request, response);
				break;
			case "/help": 
				dispatcher = getServletContext().getRequestDispatcher("/help.jsp");
				dispatcher.forward(request, response);
				break;			
			default:
				dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				dispatcher.forward(request, response);
				break;			
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
		
		//HibernateToDoListDAO.Instance().addTask(id,task, description,strUserAgent);
	}
}
