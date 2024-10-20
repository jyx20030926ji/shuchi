package com.testvue.testvue.Service;

import com.testvue.testvue.enity.User;
import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {


    List<User> userlist();

    User adduser(User user);

    void deleteById(Integer id);

    String login(LoginDTO loginDTO);

    void register(UserRegisterDTO userRegisterDTO);
}
