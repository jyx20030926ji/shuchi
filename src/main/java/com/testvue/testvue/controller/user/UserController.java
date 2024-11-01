package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.UserService;
import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/users")
@RestController("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result<PageResult<User>> pagefind(@RequestBody PageUserDTO pageUserDTO)
    {
        return Result.success(userService.pagefind(pageUserDTO)) ;
    }
    @GetMapping("/{id}")
    public Result<User> selectById(@PathVariable Long id)
    {
        User user= userService.selectById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{ids}")
    public Result  deleteById(@PathVariable List<Long> ids)
    {

        userService.deleteByIds(ids);

        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user)
    {
        userService.update(user);
        return Result.success();
    }

    @PostMapping("/login")
    public   Result<String> login(@RequestBody LoginDTO loginDTO)
    {
        String token = userService.login(loginDTO);
        return Result.success(token);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO)
    {
        userService.register(userRegisterDTO);
        return Result.success();
    }



}
