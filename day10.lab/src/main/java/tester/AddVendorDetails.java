package tester;
import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDaoImpl;
import dao.IVendorDao;
import pojos.Vendor;

public class AddVendorDetails {
	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			IVendorDao vendorDao = new VendorDaoImpl();
			System.out.println("Enter vendor details:  name,  email,  password,  regAmount,  regDate(yyyy-MM-dd)");
			Vendor v = new Vendor(sc.next(), sc.next(), sc.next(), sc.nextDouble(), LocalDate.parse(sc.next()));
			System.out.println(vendorDao.registerVendor(v));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
