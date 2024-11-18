package com.testvue.testvue.Service;

import com.testvue.testvue.enity.dto.PageFriendDTO;
import com.testvue.testvue.enity.po.User;

import java.util.List;

public interface FriendService {
    List<User> findFriends(PageFriendDTO pageFriendDTO);

    void changeFriendStatus(Long id,Integer status);


}
