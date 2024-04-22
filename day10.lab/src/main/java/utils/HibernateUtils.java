package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory Sf;
	static {
		try {
			Sf = new Configuration().configure().buildSessionFactory();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSf() {
		return Sf;
	}
}