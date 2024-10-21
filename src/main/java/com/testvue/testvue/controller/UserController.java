package com.testvue.testvue.controller;

import com.testvue.testvue.Service.UserService;

import com.testvue.testvue.enity.Result;
import com.testvue.testvue.enity.User;

import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;
import com.testvue.testvue.exception.DeleteException;
import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j

public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/admin/users")
    public Result<List<User>> userlist()
    {


        List<User> userlist=userService.userlist();


        return Result.success(userlist);

    }

    @DeleteMapping("/admin/users/{id}")
    public void deleteById(@PathVariable Integer id)
    {
        if(id != null) {
            throw new DeleteException(CodeMessageMenu.INTERNAL_SERVER_ERROR);
        }
        userService.deleteById(id);

    }
    @PostMapping("/admin/login")

    public Result<String> login(@RequestBody LoginDTO  loginDTO)
    {
        String jwt = userService.login(loginDTO);
        return Result.success(jwt);
    }
    @PostMapping("/admin/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO)
    {

        userService.register(userRegisterDTO);
        return Result.success();



    }
    @PutMapping("/admin/users")
    public Result update(@RequestBody User user)
    {

        userService.update(user);
        return Result.success();

    }


}
