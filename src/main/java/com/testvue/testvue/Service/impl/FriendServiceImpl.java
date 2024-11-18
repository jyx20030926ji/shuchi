package com.testvue.testvue.Service.impl;

import com.testvue.testvue.Service.FriendService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.dto.PageFriendDTO;
import com.testvue.testvue.enity.po.Friend;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<User> findFriends(PageFriendDTO pageFriendDTO) {
           List<User> userList;
        pageFriendDTO.setUserId(BaseCont.get().longValue());
        pageFriendDTO.setPage((pageFriendDTO.getPage() - 1) * pageFriendDTO.getPageSize());
        if (pageFriendDTO.getStatus() == 2) {
            userList=friendMapper.findFriends(pageFriendDTO);
        } else {
            userList=friendMapper.findRequestFriends(pageFriendDTO);
        }
        return userList;
    }
    @Override
    public void changeFriendStatus(Long id,Integer status) {
        Integer account=0;

        Friend friend=new Friend();

        List<Friend> friendList = friendMapper.findFriendsByUserId(BaseCont.get().longValue());

        List<Long> friendIdList = friendList.stream().map(Friend::getFriendId).toList();

       for(Long friendId:friendIdList)
       {
           if(friendId==id)
           {
               account++;
           }
       }
       if(account==0 && status ==1) {

           friend.setUserId(BaseCont.get().longValue());
           friend.setFriendId(id);
           friend.setStatus(status);
           friendMapper.insertFriend(friend);

           friend.setUserId(id);
           friend.setFriendId(BaseCont.get().longValue());
           friendMapper.insertFriend(friend);
       }
        else {
            friend.setUserId(BaseCont.get().longValue());
            friend.setFriendId(id);
            friend.setStatus(status);
            friendMapper.updateFriendStatus(friend);
            friend.setUserId(id);
            friend.setFriendId(BaseCont.get().longValue());
            friend.setStatus(status);
            friendMapper.updateFriendStatus(friend);
        }
    }

}
