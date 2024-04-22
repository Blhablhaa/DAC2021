package dao;

import org.hibernate.*;

import pojos.Customer;

import java.util.Date;
import java.util.List;

import org.hibernate.*;

import utils.HibernateUtils;

public class CustomerDAO {
	public Customer validateCustomer(String email, String pass)
			throws Exception {
		Customer c = null;
		Session sess = HibernateUtils.getSession();
		// tx
		Transaction tx = sess.beginTransaction();
		try {
			c =(Customer) sess.createQuery("select c from Customer c where c.email = :em and c.password = :pa",Customer.class)
					.setParameter("em", email).setParameter("pa", pass)
					.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		System.out.println("dao " + c);
		return c;//detached pojo
	}
}
