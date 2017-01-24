package com.shenkar.controller;

import com.shenkar.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.catalina.Session;
import org.apache.log4j.BasicConfigurator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class ProgramController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ProgramController.class);
	int secondsPassed=0;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//set timer to check how much time it takes to get the response
		long tStart = System.currentTimeMillis();

		//checking what action needed
		RequestDispatcher dispatcher = null;
		String path = request.getParameter("action");//checking the next URL from form
		if (path==null)
			{
				path = request.getPathInfo();//check URL from browser
				if (path.contains("ChangingTasks"))path = "/ChangingTasks";
				else if (path.contains("AddingTasks"))path = "/AddingTasks";
				else if (path.contains("DeleteTasks"))path = "/DeleteTasks";
				else if (path.contains("Register"))path = "/Register";
				else if (path.contains("LoginForm"))path = "/LoginForm";
				else if (path.contains("UserTask"))path = "/UserTask";
				else if (path.contains("DeleteAccount"))path = "/DeleteAccount";
			}
		else{
				if(path.equals("Delete"))path = "/DeleteTasks";
				else if (path.equals("SignIn"))path = "/Register";
				else if (path.equals("Login"))path = "/UserTask";
				else if (path.contains("DeleteAccount"))path = "/DeleteAccount";
				else if (path.contains("AddingTasks"))path = "/AddingTasks";
				else if (path.contains("DeleteTasks"))path = "/DeleteTasks";
				else if (path.contains("Register"))path = "/Register";
				else if (path.contains("LoginForm"))path = "/LoginForm";
				else if (path.contains("UserTask"))path = "/UserTask";
				else if (path.contains("ChangingTasks"))path = "/ChangingTasks";
			} 
		HttpSession session = request.getSession();

		switch (path) 
		{
		default:case "/LoginForm":
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
					dispatcher.forward(request, response);
					break;
		case "/Register":
			try{
					String firstName = request.getParameter("FirstName");
					String lastName = request.getParameter("LastName");
					String password = request.getParameter("Password");
					String Email = request.getParameter("Email");
					int phoneNumer = Integer.parseInt(request.getParameter("PhoneNumber"));
					int id = Integer.parseInt(request.getParameter("UserID"));
					String strUserAgent = request.getHeader("User-Agent");
					User CreationNewUser = new User(firstName, lastName, id, phoneNumer, Email, password,strUserAgent);
					log.info("user - "+CreationNewUser.getId() +"was created");
					if (HibernateToDoListDAO.Instance().CheckUserInDB(id,password))
					{
						log.debug("Clinet "+ id+" is already in the db.Please choose another ID number.");
						dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");				
						request.setAttribute("queryAnswerLoginform","Clinet "+ id+" is already in the db.Please choose another ID number.");
						dispatcher.forward(request, response);
						break;
					}
					HibernateToDoListDAO.Instance().addUser(CreationNewUser);		
					request.setAttribute("MyUser",CreationNewUser);
					request.setAttribute("TasksLists",new ArrayList<Task>()); 
					request.setAttribute("TasksListsClosed",new ArrayList<Task>()); 
				}
				catch(Exception e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());
					dispatcher.forward(request, response);
					break;				
				}
					//there is no break, UserTask section will start automatically
		case "/UserTask":			
			try{
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					String Pawword = request.getParameter("Password");				
					int userid = Integer.parseInt(request.getParameter("UserID"));
					if (HibernateToDoListDAO.Instance().CheckUserInDB(userid,Pawword)==false)
					{
						log.debug("Clinet number - "+ userid+" is not in the db.");
						dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");				
						request.setAttribute("queryAnswerLoginform","User ID or Password are wrong");
						dispatcher.forward(request, response);
						break;
					}
					log.info("Clinet number - "+ userid+" has connected to his account.");
					//return information
					Cookie cookUserID = new Cookie("UserID", String.valueOf(userid));
					response.addCookie(cookUserID);
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(userid));
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(userid));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(userid)); 
					long tEnd = System.currentTimeMillis();
					long tDelta = tEnd - tStart;
					double elapsedSeconds = tDelta / 1000.0;
					request.setAttribute("timer", elapsedSeconds);
					dispatcher.forward(request, response);
					break;		
				}
				catch(Exception e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());
					dispatcher.forward(request, response);
					break;				
				}
			case "/DeleteAccount":
				try{
					log.info("delete account section");
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
					String Pawword1 = request.getParameter("Password");				
					int userid1 = Integer.parseInt(request.getParameter("UserID"));
					if(HibernateToDoListDAO.Instance().CheckUserInDB(userid1, Pawword1))
					{
						for(Task tsk:HibernateToDoListDAO.Instance().getTasksForUser(userid1))
						{
							HibernateToDoListDAO.Instance().deleteTask(tsk.getTaskNumber());
						}
						for(Task tsk:HibernateToDoListDAO.Instance().getTasksForUserClosed(userid1))
						{
							HibernateToDoListDAO.Instance().deleteTask(tsk.getTaskNumber());
						}
						String str = HibernateToDoListDAO.Instance().deleteUser(userid1, Pawword1);
						log.info("Clinet number - "+ userid1+" was deleted.");
						request.setAttribute("queryAnswerLoginform",str);
					}
					else{
						request.setAttribute("queryAnswerLoginform","User Cannot Be Found!");
					}
					dispatcher.forward(request, response);
					break;
				}
				catch(Exception e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());
					dispatcher.forward(request, response);
					break;				
				}
			case "/AddingTasks":
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					String taskName1 = request.getParameter("taskname");
					String taskDescription1 = request.getParameter("taskdescription");
					session = request.getSession();
					int AddingTaskForUser  = (int) session.getAttribute("userid");
					HibernateToDoListDAO.Instance().addTask(new Task(AddingTaskForUser, taskName1, taskDescription1,"Open"));
					//return information
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(AddingTaskForUser));
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(AddingTaskForUser));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(AddingTaskForUser)); 
					long tEnd = System.currentTimeMillis();
					long tDelta = tEnd - tStart;
					double elapsedSeconds = tDelta / 1000.0;
					request.setAttribute("timer", elapsedSeconds);
					dispatcher.forward(request, response);
					break;	
			case "/ChangingTasks":
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					int taskNumber = Integer.parseInt(request.getParameter("taskNumber"));
					String taskName = request.getParameter("taskname");
					String description = request.getParameter("taskdescription");
					//checking that the task is for this client
					session = request.getSession();
					int checkingClientIDforTask  = (int) session.getAttribute("userid");
					Task tempTask = HibernateToDoListDAO.Instance().getTask(taskNumber);
					if(tempTask!=null&&tempTask.getClientID()==checkingClientIDforTask)
					{
						HibernateToDoListDAO.Instance().updateTask(taskNumber, taskName, description);
						log.info(taskNumber+" was changed.");
					}
					else
					{
						request.setAttribute("queryAnswer","This task isn't for this client - cannot be changed");
						log.info("Client number "+ checkingClientIDforTask + "tried to change a task that isn't his.");
					}
					//return information
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(checkingClientIDforTask));
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(checkingClientIDforTask));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(checkingClientIDforTask)); 
					long tEnd1 = System.currentTimeMillis();
					long tDelta1 = tEnd1 - tStart;
					double elapsedSeconds1 = tDelta1 / 1000.0;
					request.setAttribute("timer", elapsedSeconds1);
					dispatcher.forward(request, response);
					break;	
			case "/DeleteTasks":
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					int taskNumber1 = Integer.parseInt((String)request.getParameter("taskNumber"));
					HibernateToDoListDAO.Instance().ChangeStatus(taskNumber1);
					log.info(taskNumber1+" was cahgned to complete or done.");
					//return information
					session = request.getSession();
					int thisUser11  = (int) session.getAttribute("userid");
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(thisUser11));
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(thisUser11));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(thisUser11)); 
					long tEnd2 = System.currentTimeMillis();
					long tDelta2 = tEnd2 - tStart;
					double elapsedSeconds2 = tDelta2 / 1000.0;
					request.setAttribute("timer", elapsedSeconds2);
					dispatcher.forward(request, response);
					break;		
			}
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
