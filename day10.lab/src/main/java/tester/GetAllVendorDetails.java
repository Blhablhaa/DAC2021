package tester;
import static utils.HibernateUtils.getSf;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IVendorDao;
import dao.VendorDaoImpl;
import pojos.Vendor;
public class GetAllVendorDetails {
	public static void main(String[] args) {
		try(SessionFactory sf = getSf()){
			IVendorDao vendorDao = new VendorDaoImpl();
			List<Vendor> vendorList = vendorDao.allVendorDetails();
			System.out.println(vendorList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
