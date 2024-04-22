package dao;

import java.time.LocalDate;
import java.util.List;

import pojos.Vendor;

public interface IVendorDao {
	String registerVendor(Vendor vendor);
	
	Vendor getVendorDetails(long vendorId);
    
	List<Vendor> allVendorDetails();
	
	String authenticateVendor(String email, String pass);

	String updateRegAmount(String email, double amount);

	String deletVendor(double amount, LocalDate date);
}
