package com.app.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exc.InvalidStudentDetailsException;
import com.app.dao.IStudentDao;
import com.app.pojos.Student;
@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
	//dependency
	@Autowired
	private IStudentDao studentDao;

	@Override
	public String studentAdmission(Student student, String courseTitle) {
		// B.L validations
		if(student.getCgpa() < 7 || student.getDob().isBefore(LocalDate.of(1990, 1, 1)))
			throw new InvalidStudentDetailsException("Invalid student details");
		return studentDao.admitStudent(student, courseTitle);
	}

}
