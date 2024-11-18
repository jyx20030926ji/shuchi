package com.testvue.testvue.controller.user;



import com.testvue.testvue.Service.FriendService;
import com.testvue.testvue.Service.UserService;
import com.testvue.testvue.enity.dto.PageFriendDTO;
import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/friends")
public class FriendController {
    @Autowired
    private UserService userService;


    @Autowired
    private FriendService friendService;
    @PostMapping("/page")
    public Result<List<User>> findFriends(@RequestBody PageFriendDTO pageFriendDTO)
    {
     List<User> userList=friendService.findFriends(pageFriendDTO);

     return Result.success(userList);

    }
    @PutMapping("/{id}/{status}")
    public Result requestFriend(@PathVariable Long id,@PathVariable Integer status)
    {
        friendService.changeFriendStatus(id,status);
        return Result.success();
    }

    @PostMapping
    public Result<List<User>> getUserByName(@RequestBody PageUserDTO pageUserDTO)
    {

        PageResult<User> pagefind = userService.pagefind(pageUserDTO);

        List<User> userList = pagefind.getPageList();
        return Result.success(userList);
    }

}
