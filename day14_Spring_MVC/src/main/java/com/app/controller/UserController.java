package com.app.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
public UserController() {
	System.out.println("in ctor of"+getClass().getName()+""+userService);
}
//add request handling method for showing the loginform(show from phase)
//key in the HandlerMapping bean : /user/login:method=post 
@GetMapping("/login")
 public String showLoginForm()
 {
	 System.out.println("in show login form");
	 return "/user/login";//actual view name : WEB-INF/views/user/login.jsp
 }
//key in HandlerMapping bean : /user/login:method=post 
      @PostMapping("/login")
      //SC invoke : String email=request.getparameter("email")
      //SC invoke : String pwd=request.getparameter("pass")
	 public String processLoginForm(@RequestParam String email,
			  @RequestParam(name = "pass") String pwd,Model map,HttpSession session)
	 //For conversion  : Match req parameter with 
      {
	   System.out.println("in process login form"+email+""+pwd);
	   
	 
	 try {
		//invoke service layer method
		 User validatedUser =  userService.validateUser(email, pwd);
	     //valid login
		 session.setAttribute("user_detail", validatedUser);
		 session.setAttribute("message","Login Successful under role "+validatedUser.getUserRole());
		 //check role
		 if(validatedUser.getUserRole().equals("ADMIN"))//admin login 
			 return "redirect:/admin/list"; 
		 return "redirect:/vendor/details";
	 } catch (Exception e) {
		e.printStackTrace();
		//invalid login--->forward the client to the login.jsp
		map.addAttribute("message","Invalid Login ! please retry");
		return "/user/login";//actual view name : WEB-INF/views/user/login.jsp
	}
	  
      }
}
