package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	// dependency : service layer i/f
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor of " + getClass().getName() + " " + userService);
	}

	// add request handling method for showing the login form (show form phase)
	// key in HandlerMapping bean : /user/login:method=get
	// value : com.app.controller.UserController.showLoginForm()
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/user/login";// actual view name : /WEB-INF/views/user/login.jsp
	}

	// add request handling method for processing the login form (process form
	// phase)
	// key in HandlerMapping bean : /user/login:method=post
	// value : com.app.controller.UserController.processLoginForm()
	@PostMapping("/login")
	// SC invokes : String email=request.getParameter("email")
	// SC invokes : String pwd=request.getParameter("pass")
	public String processLoginForm(@RequestParam String email,
			@RequestParam(name = "pass") String pwd, Model map,HttpSession session)
	// For convenience : MATCH req param names with method params.
	{
		System.out.println("in process login form " + email + " " + pwd);
		try {
			// invoke service layer method
			User validatedUser = userService.validateUser(email, pwd);
			// valid login : add the validated user details under model map(saved in request
			// scope)
			session.setAttribute("user_details", validatedUser);
			session.setAttribute("message","Login Successful under role of "+validatedUser.getUserRole());
			// chk the role
			if (validatedUser.getUserRole().equals(Role.ADMIN)) // admin logged in
				return "redirect:/admin/list";
			return "redirect:/vendor/details";

		} catch (RuntimeException e) {
			e.printStackTrace();
			// invalid login --forward the clnt to login.jsp
			map.addAttribute("message", "Invalid Login ! Pls retry....");
			return "/user/login";// actual view name : /WEB-INF/views/user/login.jsp
		}
		
	}

}
