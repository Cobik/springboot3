package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.interf.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserPageController {
	@Autowired
	private UserService userService;
	@GetMapping
	public String printWelcome(HttpServletRequest request, ModelMap model) {
		User user = userService.findByUsername(request.getSession().getAttribute("userName").toString());
		model.addAttribute("user",user);
		return "user";
	}
}