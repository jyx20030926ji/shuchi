package com.testvue.testvue.Service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.testvue.testvue.Service.UserService;
import com.testvue.testvue.Utils.JwtUtils;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;

import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.UserMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
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

    @Override
    public void update(User user) {

        User user1 = userMapper.selectByAccount(user.getAccount());
        if(user1!=null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.USER_ALREADY_EXIST);
        }


        user.setId(BaseCont.get().longValue());

        userMapper.update(user);

    }

    /**
     * 分页条件查询 根据姓名和账号名
     * @param pageUserDTO
     * @return
     */


        @Override
        public PageResult<User> pagefind(PageUserDTO pageUserDTO) {
            // 开始分页
            PageHelper.startPage(pageUserDTO.getCurrentPage(),pageUserDTO.getPageSize());

            Page<User> page;
            try {
                // 查询
                page = userMapper.pagefind(pageUserDTO);

                log.info("page------{}----",page);
            } catch (Exception e) {
                // 处理异常
                throw new RuntimeException("Error while fetching users", e);
            }

            // 组装结果
            PageResult<User> pageResult = new PageResult<>();
            pageResult.setPageList(page.getResult());
            pageResult.setTotal(page.getTotal());

            return pageResult;
        }

    }

