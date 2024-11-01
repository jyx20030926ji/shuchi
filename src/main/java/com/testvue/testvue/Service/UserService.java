package com.testvue.testvue.Service;


import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.User;

import java.util.List;


public interface UserService  {




    void deleteByIds(List<Long> ids);

    String login(LoginDTO loginDTO);

    void register(UserRegisterDTO userRegisterDTO);

    void update(User user);

    PageResult<User> pagefind(PageUserDTO pageUserDTO);


    User selectById(Long id);
}
