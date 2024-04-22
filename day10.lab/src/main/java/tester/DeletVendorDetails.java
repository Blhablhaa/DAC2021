package tester;
import static utils.HibernateUtils.*;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.IVendorDao;
import dao.VendorDaoImpl;
import pojos.Vendor;
public class DeletVendorDetails {
	public static void main(String[] args) {
		try(SessionFactory sf = getSf();Scanner sc = new Scanner(System.in)){
			IVendorDao vendorDao = new VendorDaoImpl();
			System.out.print("Enter reg. amount and reg. date (yyyy-MM-dd):  ");
			String v = vendorDao.deletVendor(sc.nextDouble(),LocalDate.parse(sc.next()));
			System.out.println(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
