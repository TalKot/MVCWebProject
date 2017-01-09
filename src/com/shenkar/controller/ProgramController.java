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

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		/*
		Session session =(Session) request.getSession();
		if(((ServletRequest) session).getAttribute("tasks")==null)
		{
			session.setAttribute("tasks", new LinkedList<String>());
		}
		LinkedList<String> list = (LinkedList<String>) request.getAttribute("tasks");
		*/
		
				
		String path = request.getPathInfo();
		if(path.contains("controller"))
		{
			path=path.substring(11,path.length()-4);
			//System.out.println("after change - Path is - "+path);
		}
		try{
		switch (path) 
		{
		default:case "/LoginForm":
			
				System.out.println("I'm in loggin form");
				dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
				dispatcher.forward(request, response);
				break;

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
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(userReg.getId()));
					dispatcher.forward(request, response);
					break;
		case "/UserTask":			
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					String Pawword = request.getParameter("Password");				
					int userid = Integer.parseInt(request.getParameter("UserID"));
					if (HibernateToDoListDAO.Instance().CheckUserInDB(userid,Pawword)==false)
					{
						dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
						
						request.setAttribute("RequestDeleteAnswer","User ID or Password are wrong");
						dispatcher.forward(request, response);
						break;
					}
					com.shenkar.model.User user = HibernateToDoListDAO.Instance().getUser(userid,Pawword);
					request.setAttribute("MyUser",user);
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(user.getId()));
					dispatcher.forward(request, response);
				break;				
		case "/DeleteAccount":
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
					String Pawword1 = request.getParameter("Password");				
					int userid1 = Integer.parseInt(request.getParameter("UserID"));
					String answer = HibernateToDoListDAO.Instance().deleteUser(userid1, Pawword1);
					request.setAttribute("RequestDeleteAnswer",answer);
					dispatcher.forward(request, response);
					break;
		case "/AddingTasks":
				//dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
				String taskName1 = (String)request.getParameter("taskname");
				String taskDescription1 = (String)request.getParameter("taskdescription");
				//User thisUser = (User)request.getAttribute("MyUser1");
				//System.out.println("The user is -"+thisUser.getId());
				//Task newTask = new Task(thisUser.getId(), taskName1, taskDescription1);
				Task newTask = new Task(123, taskName1, taskDescription1);
				HibernateToDoListDAO.Instance().addTask(newTask);
				//request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(thisUser.getId()));
				request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(123));	
				//dispatcher.forward(request, response);
			break;	
		case "/clientList":
				dispatcher = getServletContext().getRequestDispatcher("/clientList.jsp");
				List vec = HibernateToDoListDAO.Instance().getUsers();
				request.setAttribute("UsersList", vec);
				dispatcher.forward(request, response);
				break;
		/*
		default:
				dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				dispatcher.forward(request, response);
				break;
		*/			
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
