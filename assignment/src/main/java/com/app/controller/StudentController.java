package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Student;
import com.app.service.IStudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private IStudentService studentService;

	public StudentController() {
		System.out.println("in student  controller ");
	}

	// add req handling method to show student admission form
	@GetMapping("/admit")
	public String showStudentAdmitForm(Student s) {
		System.out.println("in show form " + s);// model --> view
		return "/students/admit";
	}

	// add req handling method to process student admission form
	@PostMapping("/admit")
	public String processStudentAdmitForm(@Valid Student s, BindingResult result,  @RequestParam String courseTitle, Model map,RedirectAttributes flashMap) {
		System.out.println("in process form " + s + " " + courseTitle);// view ---> model
		//chk if there are any Presentation logic validation errs
		if(result.hasErrors())
		{
			System.out.println("P.L errs "+result);
			//in case of P.L errs --simply forward the clnt to the view layer
			return "/students/admit";//forward view (logical view name)
			
		}
		// invoke service layer method
		try {
			flashMap.addFlashAttribute("mesg",studentService.studentAdmission(s, courseTitle));
			return "redirect:/courses/list"; //redirect view
		} catch (Exception e) {
			System.out.println("error " + e);
			map.addAttribute("mesg", e.getMessage());
			return "/students/admit";//forward view (logical view name)
		}

	}
}
