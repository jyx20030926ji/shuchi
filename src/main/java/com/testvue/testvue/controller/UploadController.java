package com.testvue.testvue.controller;





import com.testvue.testvue.Utils.AliOssUtil;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.mapper.UserMapper;
import io.swagger.annotations.Api;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.UUID;

@RestController
@Slf4j
@Api(tags = "上传图片")
@RequestMapping("/admin/users")
public class UploadController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private UserMapper userMapper;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.indexOf("."));
        String newname= UUID.randomUUID().toString()+substring;
        log.info("newname{}",newname);

        String url= aliOssUtil.upload(file.getBytes(),newname);


        log.info("url的地址为{}",url);
        User user = userMapper.selectById(BaseCont.get().longValue());
        user.setAvatarUrl(url);
        userMapper.update(user);
        return Result.success(url);

    }
}
