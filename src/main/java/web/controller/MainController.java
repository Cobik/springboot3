package web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(Authentication authentication){
        if (authentication != null){
            User user = (User) authentication.getPrincipal();
            if (user.getAuthorities().stream().anyMatch(role -> "ADMIN".equals(role.getAuthority()))){
                return "redirect:/admin/usersPage";
            }
            return "redirect:/user";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/usersPage")
    public String getAdminPanelPage() {
        return "adminPage";
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect:/";
        }
        if (request.getParameterMap().containsKey("error")) {
            return "error";
        } else if (request.getParameterMap().containsKey("logout")) {
            return "redirect:/logout";
        }
        return "loginPage";
    }
}