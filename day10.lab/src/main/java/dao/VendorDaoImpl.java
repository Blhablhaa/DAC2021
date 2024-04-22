package dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;

import pojos.Vendor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static utils.HibernateUtils.getSf;
import pojos.Vendor;

public class VendorDaoImpl implements IVendorDao {

	@Override
	public String registerVendor(Vendor vendor) {
		String mesg = "registration failed....";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			Serializable id =  session.save(vendor);
			tx.commit();
			mesg = "Vendor registered successfully with ID " + id;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return mesg;
	}
   
	@Override
	public Vendor getVendorDetails(long vendorId) {
		Vendor ven=null;
		
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
            ven=session.get(Vendor.class, vendorId);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return ven;
	}

	@Override
	public List<Vendor> allVendorDetails() {
		List<Vendor> vendorList=null;
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			vendorList=session.createQuery("select v from Vendor v").list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return vendorList;
	}

	@Override
	public String authenticateVendor(String email, String pass) {
          Vendor vendor=null;
		  String mesg="Login Failed.....";
		  String jpql="select v from Vendor v where v.email=:em and v.password=:pass";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			vendor= session.createQuery(jpql, Vendor.class)
					      .setParameter("em", email).setParameter("pass",pass)
					      .getSingleResult();
			tx.commit();
			mesg="Login successful....";
		} catch (NoResultException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return mesg;
	}

	@Override
	public String updateRegAmount(String email, double amount) {
		String mesg="Registration amount update failed";
		String  jpql="select v from Vendor v where v.email=:em";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			Vendor vendor=session.createQuery(jpql, Vendor.class)
            		.setParameter("em", email)
            		.getSingleResult();
            		if(vendor!= null) {
            			vendor.setRegAmount(vendor.getRegAmount() +amount );
            			mesg="Updated Registration Amount "+vendor+" of Vendor";
            		}
			tx.commit();
		    mesg="Updated Registration Amount "+vendor+" of Vendor";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return mesg;
	}

	@Override
	public String deletVendor(double amount, LocalDate date) {
		String mesg="Deletation failed.....";
		String jpql="delet from Vendor v where where reg_amount < :amt and reg_date > :date";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
            int Count=session.createQuery("jpql").setParameter("amt", amount)
            		.setParameter("date", date).executeUpdate();
			tx.commit();
			if(Count>0)
			{
				mesg=Count+" Vendor details Deleted succed.....";
			}
			
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return mesg;
	}
	
	
}