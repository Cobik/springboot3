//package web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import web.model.Role;
//import web.model.User;
//import web.service.interf.RoleService;
//import web.service.interf.UserService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLException;
//import java.util.*;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    @GetMapping
//    public String printAllUsers(HttpServletRequest request, ModelMap model) {
//
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//
//        List<Role> roles = roleService.getAllRoles();
//
//        model.addAttribute("allRoles", roles);
//
//        model.addAttribute("userName", request.getSession().getAttribute("userName"));
//
//      //  User user = userService.findByUsername(request.getSession().getAttribute("userName").toString());
//
//
//        return "mainpage";
//    }
//
//
//    @GetMapping("addUser")
//    public ModelAndView newUser() {
//        User user = new User();
//        ModelAndView mav = new ModelAndView("adduser");
//        mav.addObject("user", user);
//
//        List<Role> roles = roleService.getAllRoles();
//
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }
//
//    @PostMapping("addUser")
//    public String addUser(@ModelAttribute("userForm") User user) {
//
//        try {
//            if (Objects.nonNull(userService.findByUsername(user.getUsername()))) {
//                throw new RuntimeException("User is already exists");
//            } else {
//                userService.addUser(user);
//            }
//            return "redirect:/admin";
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("edit")
//    public String printEditUser(@RequestParam("id") long id, ModelMap model) throws SQLException {
//        User user = userService.getUsersById(id);
//
//        model.addAttribute("user", user);
//        model.addAttribute("id", id);
//
//        List<Role> roles = roleService.getAllRoles();
//
//        model.addAttribute("allRoles", roles);
//        model.addAttribute("userRoles", user.getRoles());
//
//
//
//
//        return "edit";
//    }
//
//    @PostMapping("edit")
//    public String updateUser(User user) {
//        if (Objects.nonNull(userService.findByUsername(user.getUsername())) && userService.findByUsername(user.getUsername()).getId() != user.getId()) {
//            throw new RuntimeException("User is already exists");
//        } else {
//            userService.updateUser(user);
//        }
//        return "redirect:/admin";
//    }
//
//    @GetMapping("delete")
//    public String deleteUser(@RequestParam("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//
//
//}
