package web.controller;

import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.interf.RoleService;
import web.service.interf.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("admin/users")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final List<Role> allRoles;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        allRoles = roleService.getAllRoles();
    }

//    @PostMapping
//    public String saveUser(@ModelAttribute User user, @RequestParam String[] rolesString) throws SQLException {
//        List<Role> rolesFromBD = new ArrayList<>(allRoles);
//        List<Role> roleList = Stream.of(rolesString).map(Role::new).collect(Collectors.toList());
//        rolesFromBD.retainAll(roleList);
//        user.setRoles(new HashSet<>(rolesFromBD));
//
//
//
//
//        if (userService.addUser(user)) {
//            return "success";
//        }
//        return "failure";
//    }

    @PostMapping
    public String saveUser(@ModelAttribute User user, @RequestParam String[] rolesString) throws SQLException {
        getRoles(user, rolesString);

        if (userService.addUser(user)) {
            return "success";
        }
        return "failure";
    }


    @PutMapping
    public String updateUser(@ModelAttribute User user, @RequestParam String[] rolesString) {
        getRoles(user, rolesString);

        if (userService.updateUser1(user)) {
            return "success";
        }
        return "failure";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "success";
    }

    @GetMapping("/byUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUsersById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    private void getRoles(@ModelAttribute User user, @RequestParam String[] rolesString) {
        List<Role> roleList = Stream.of(rolesString).map(Role::new).collect(Collectors.toList());
        Set<Role> roles = new HashSet<>();

        if (roleList.size() > 1) {
            roles.add(roleService.getRole("ADMIN"));
            roles.add(roleService.getRole("USER"));
        } else if (roleList.get(0).getRoleName().equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        } else if (roleList.get(0).getRoleName().equals("USER")) {
            roles.add(roleService.getRole("USER"));
        }
        user.setRoles(roles);
    }
}