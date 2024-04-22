package tester;
import static utils.HibernateUtils.*;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.IVendorDao;
import dao.VendorDaoImpl;
import pojos.Vendor;
public class UpdateRegiAmount {
	public static void main(String[] args) {
		try(SessionFactory sf = getSf();Scanner sc = new Scanner(System.in)){
			IVendorDao vendorDao = new VendorDaoImpl();
			System.out.print("Enter vendor email and reg. amount offset: ");
			String v = vendorDao.updateRegAmount(sc.next(), sc.nextDouble());
			System.out.println(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
