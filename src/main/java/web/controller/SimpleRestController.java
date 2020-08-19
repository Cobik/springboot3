package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.dto.UserDtoList;
import web.model.User;
import web.service.interf.UserService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest")
public class SimpleRestController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser(@PathVariable String username){
        try {
            User user = userService.findByUsername(username);
            return user.toUserDto();
        } catch (NullPointerException n){
            throw new NullPointerException();
        }
    }
    @GetMapping(value = "all",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDtoList printAllUsers(){
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userService.getAllUsers();

        for (User user : users){
            userDtoList.add(user.toUserDto());
        }

        UserDtoList userDtoList1 = new UserDtoList();
        userDtoList1.setUserDtos(userDtoList);

        return userDtoList1;
    }




}
