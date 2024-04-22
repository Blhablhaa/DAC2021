package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public User getUserDetails(int userId) {
		User ref = null;// ref : state NA
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// valid id
			// L1 cache : empty
			ref = session.load(User.class, userId);// int --> Integer --->Serializable : select from users_tbl where
			//simply call non id getter
			ref.getEmail();									// user_id=?
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		if(ref != null)
			System.out.println(ref.getClass().getName());
		return ref;// ref : DETACHED
	}

}
