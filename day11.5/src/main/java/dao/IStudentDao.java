package dao;

import pojos.Address;
import pojos.Student;

public interface IStudentDao {
//student admission
	String admitNewStudent(Student student, String courseTitle);

	// cancel student admission
	String cancelAdmission(String email, String courseTitle);
	//assign student address
	String assignStudentAddress(int studentId,Address address);
	//update city
	String updateCity(int studentId,String newCity);
}
