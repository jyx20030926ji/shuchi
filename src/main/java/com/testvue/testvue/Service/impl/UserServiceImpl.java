package com.testvue.testvue.Service.impl;


import com.testvue.testvue.Service.UserService;
import com.testvue.testvue.Utils.JwtUtils;
import com.testvue.testvue.enity.User;
import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.UserMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;


    Long ttlMillis=36000000L;

    @Override
    public List<User> userlist() {
        return userMapper.userlist();
    }

    @Override
    public User adduser(User user) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public String login(LoginDTO loginDTO) {

        User user = new User();
        BeanUtils.copyProperties(loginDTO, user);
        User user1 = userMapper.selectByAccount(user.getAccount());
        if (user1 == null) {
            throw new AccountNoExistException(CodeMessageMenu.ACCOUNT_BUSYS);
        } else if (!user1.getPassword().equals(user.getPassword())) {
            throw new AccountNoExistException(CodeMessageMenu.PASSWORD_BUSYS);
        } else {
            Map claims=new HashMap<>();
            claims.put("userId",user1.getId());

          return  jwtUtils.encoding(user1.getName(),ttlMillis,claims);

        }
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        User user = userMapper.selectByAccount(userRegisterDTO.getAccount());

        if(user!=null)
        {
            throw new AccountNoExistException(CodeMessageMenu.USER_ALREADY_EXIST);

        } else if (userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword())) {
            User user1=new User();
            BeanUtils.copyProperties(userRegisterDTO,user1);
            userMapper.inserBasicInformation(user1);
        }
        else {


            throw new AccountNoExistException(CodeMessageMenu.USER_PASSWORD_DISARESS);
        }

    }
}
