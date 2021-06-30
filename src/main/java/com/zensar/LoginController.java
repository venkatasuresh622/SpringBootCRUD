package com.zensar;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	 LoginService loginService;

	@RequestMapping("/")
    public ModelAndView openIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index.jsp");
        return modelAndView;
    }
	
	@RequestMapping("/login")
	public ModelAndView handleRequest(@RequestParam("userId") String userId,@RequestParam("password") String password ,HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println("userId:"+userId);
		System.out.println("password:"+password);
		
		
		boolean result = loginService.checkLogin(userId, password);
		if(result) {
			System.out.println("Login Successful");
			modelAndView.setViewName("menu.jsp");
		}
		else {
			System.out.println("Login Failed");
			modelAndView.setViewName("index.jsp");
			modelAndView.addObject("message", "Please check your login credentials and try again...");
		}
	return modelAndView;	
	}
	
	@RequestMapping("/openRegisterationPage")
	public ModelAndView openRegisterationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/register")
	public ModelAndView registerNewUser(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView();
		
		loginService.registerUser(userId, password);
		modelAndView.setViewName("index.jsp");
		modelAndView.addObject("message", "Registration Successful. Please try now...");
	
		return modelAndView;
	}
	
	
	
	
}
