package com.testvue.testvue.controller;


import com.testvue.testvue.Service.UserService;
import com.testvue.testvue.enity.dto.*;

import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin/users")

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/page")
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

    /**
     * 实现用户登录功能
     *
     * @param loginDTO
     * @return
     */

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
 @PutMapping("/{id}/{status}")
    public Result updateUserStatus(@PathVariable Long id,@PathVariable Integer status)
 {
     User user = userService.selectById(id);

     user.setStatus(status);

     userService.update(user);
     return Result.success();
 }
 @PostMapping
    public Result insertUser(@RequestBody User user)
 {
          userService.insertUser(user);
             return Result.success();
 }

    /**
     * 邮箱登录
     * @param code
     * @param account
     * @return
     */

 @GetMapping("/logincode")
    public Result<String> logincode(String code,String account)
 {
     String loginCode = userService.logincode(code, account);

     return Result.success(loginCode);

 }
 @GetMapping("/account")
    public Result<User> getUserByAccount(String account)
 {
   User user=userService.getUserByAccount(account);
   return Result.success(user);
 }

}

