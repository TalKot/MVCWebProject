package com.shenkar.model;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateToDoListDAO implements IToDoListDAO {
	private static HibernateToDoListDAO DAO;
	private Session session = null;
	
	private SessionFactory factory;
	private SessionFactory factoryTask;
	
	private HibernateToDoListDAO() 
	{ 
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		factoryTask	= new AnnotationConfiguration().configure("hibernateTask.cfg.xml").buildSessionFactory();

	}
	
	public static HibernateToDoListDAO Instance()
	{
	    if (DAO == null) 
	    {
	    	return new HibernateToDoListDAO();
	    }
	    return DAO;
	}
	
	public void addUser(User obj)
	{
		try
		{
			session = factory.openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
	    	if ( session.getTransaction() != null )
	    	session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	
	}
	
	public void PrintDB()
	{
		try
		{
			session = factory.openSession();
			session.beginTransaction();
			
			List users = session.createQuery("from User").list();

			for (int i = 0; i < 120; i++) {
				System.out.print("*");
			}
			System.out.println("");
			System.out.println("There are " + users.size() + " users(s)");
			
			Iterator i = users.iterator();
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
			
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
	    	if ( session.getTransaction() != null )session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}


	public List getUsers()
	{
		session = factory.openSession();
		session.beginTransaction();
		List users = session.createQuery("from User").list();
		session.getTransaction().commit();
		session.close();
		return users;
	}
	
	public void addTask(String id,String task,String Description,String strUserAgent)
	{
		try
		{
			Task obj = new Task();
			obj.setDescription(Description);
			obj.setTask(task);
			obj.setId(0);
			obj.setConnectionID(id);
			obj.setStrUserAgent(strUserAgent);
			session = factoryTask.openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
	    	if ( session.getTransaction() != null )session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}
	
}
