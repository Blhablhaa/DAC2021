package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sf;
	private static Session session;
	static {
		try {
			System.out.println("in static init block...");
			sf=new Configuration().configure().buildSessionFactory();
			System.out.println("sf created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static SessionFactory getSf() {
		return sf;
	}
	public static Session getSession() {
		return sf.getCurrentSession();
	}
	

}
