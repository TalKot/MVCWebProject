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
	
	private HibernateToDoListDAO() 
	{ 
		factory = new AnnotationConfiguration().configure().buildSessionFactory();

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
	


	public List getUsers()
	{
		List users = null;
		try{
		session = factory.openSession();
		session.beginTransaction();
		users = session.createQuery("from User").list();
		session.getTransaction().commit();
		return users;
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
		return users;
	}

	@Override
	public void PrintDB() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public User getUser(int userID, String Password) {
		try {
			session = factory.openSession();
            session.beginTransaction();
            User DBUser = (User) session.get(User.class, userID);
            if (DBUser.getPassword().equals(Password)){
                session.getTransaction().commit();
                return DBUser;
            }
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
		return null;
	}
	
	public String getHelloWorld()
	{
		return "Hello world";
	}
}
