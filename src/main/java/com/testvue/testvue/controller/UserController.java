package com.testvue.testvue.controller;

import com.testvue.testvue.Service.UserService;

import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;

import com.testvue.testvue.enity.po.User;
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

    /**
     * 查询全部用户
     * @return
     */

    @GetMapping("/admin/users")
    public Result<List<User>> userlist()
    {


        List<User> userlist=userService.userlist();


        return Result.success(userlist);

    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */

    @DeleteMapping("/admin/user/{id}")
    public Result deleteById(@PathVariable Integer id)
    {
        if(id == null) {
            throw new DeleteException(CodeMessageMenu.INTERNAL_SERVER_ERROR);
        }
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 用户登录下发token
     * @param loginDTO
     * @return
     */
    @PostMapping("/admin/login")

    public Result<String> login(@RequestBody LoginDTO  loginDTO)
    {
        String jwt = userService.login(loginDTO);
        return Result.success(jwt);
    }
    @PostMapping("/admin/user")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO)
    {
        userService.register(userRegisterDTO);
        return Result.success();



    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping("/admin/user")
    public Result update(@RequestBody User user)
    {

        userService.update(user);
        return Result.success();

    }

   @GetMapping("/admin/page/users")
    public Result<PageResult> pagefind(@RequestBody PageUserDTO pageUserDTO)
   {
       PageResult pageResult = userService.pagefind(pageUserDTO);

       log.info("pagesize:{}",pageUserDTO.getPageSize());
       log.info("currenPage:{}",pageUserDTO.getCurrentPage());

       return Result.success(pageResult);


   }


}
