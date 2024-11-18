package com.testvue.testvue.Service.impl;






import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.testvue.testvue.Service.UserService;
import com.testvue.testvue.Utils.JwtUtils;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.dto.LoginDTO;
import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.dto.PasswordDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;

import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.UserMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    Long ttlMillis = 36000000L;


    @Override
    @CacheEvict(value = "userList", allEntries = true)
    public void deleteByIds(List<Long> ids) {
        userMapper.deleteByIds(ids);
    }

    @Override
    public String login(LoginDTO loginDTO) {

        User user = new User();
        BeanUtils.copyProperties(loginDTO, user);
        User user1 = userMapper.selectByAccount(user.getAccount());
        if (user1 == null) {
            throw new AccountNoExistException(CodeMessageMenu.ACCOUNT_BUSYS);
        } else if (!user1.getPassword().equals(SecureUtil.md5(user.getPassword()))) {
            throw new AccountNoExistException(CodeMessageMenu.PASSWORD_BUSYS);
        } else {
            Map claims = new HashMap<>();
            claims.put("userId", user1.getId());

            return jwtUtils.encoding(user1.getName(), ttlMillis, claims);

        }
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        User user = userMapper.selectByAccount(userRegisterDTO.getAccount());

        if (user != null) {
            throw new AccountNoExistException(CodeMessageMenu.USER_ALREADY_EXIST);
        }
        else{
             userRegisterDTO.setPassword(SecureUtil.md5(userRegisterDTO.getPassword()));
             User user1=new User();
             BeanUtils.copyProperties(userRegisterDTO,user1);
             user1.setStatus(StatusConstant.ONE);
             userMapper.insertUser(user1);
        }
    }

    @Override
    @CacheEvict(value = "userList", allEntries = true)
    public void update(User user) {

        User user1 = userMapper.selectByAccount(user.getAccount());
        if (user1 != null) {
            throw new AccountNoExistException(CodeMessageMenu.USER_ALREADY_EXIST);
        }

        userMapper.update(user);

    }


    @Override
    @Cacheable(value = "userList", key = "'userLish'+#pageUserDTO.page+#pageUserDTO.name+#pageUserDTO.account+#pageUserDTO.status")
    public PageResult<User> pagefind(PageUserDTO pageUserDTO) {
        // 开始分页
        Long total = userMapper.usertotal(pageUserDTO);


        pageUserDTO.setPage((pageUserDTO.getPage() - 1) * pageUserDTO.getPageSize());


        List<User> userList = userMapper.pagefind(pageUserDTO);

        // 组装结果
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setPageList(userList);

        return pageResult;


    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @CacheEvict(value = "userList", allEntries = true)
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);

    }

    /**
     * 验证码登录
     *
     * @param code
     * @param account
     */
    @Override
    public String logincode(String code, String account) {

        String checkCode = stringRedisTemplate.opsForValue().get("code");

        if (checkCode == null || !checkCode.equals(code)) {
            throw new AccountNoExistException(404, "验证码错误");
        } else {
            User user = userMapper.selectByAccount(account);

            LoginDTO loginDTO = new LoginDTO();

            BeanUtils.copyProperties(user, loginDTO);

            return login(loginDTO);

        }


    }

    @Override
    public void changePassWord(PasswordDTO passwordDTO) {
        User user = userMapper.selectById(BaseCont.get().longValue());
        if (user == null) {
            throw new AccountNoExistException(CodeMessageMenu.ACCOUNT_BUSYS);
        }
        if (!SecureUtil.md5(passwordDTO.getOldPassword()).equals(user.getPassword())) {
            throw new AccountNoExistException(CodeMessageMenu.PASSWORD_BUSYS);
        }

        user.setPassword(SecureUtil.md5(passwordDTO.getNewPassword()));
        userMapper.update(user);

    }

    @Override
    public User getUserByAccount(String account) {
        return  userMapper.selectByAccount(account);

    }

    @Override
    public void emailRegister(String email, String code) {

      User user=userMapper.getUserByemail(email);
      if(user!=null)
      {
          throw new AccountNoExistException(CodeMessageMenu.USER_EMAIL_EXIST);
      }
        String vCode = stringRedisTemplate.opsForValue().get("code");

      //判断验证码是否为空,
      if(StrUtil.isBlank(vCode))
      {
          throw  new AccountNoExistException(CodeMessageMenu.CODE_NOT_EXIST);
      }
      if(!vCode.equals(code))
      {
          throw  new AccountNoExistException(CodeMessageMenu.CODE_CHECK_ERROR);
      }

    }
}

