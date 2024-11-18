package com.testvue.testvue.Service;

import com.testvue.testvue.enity.dto.MessageSendDTO;
import com.testvue.testvue.enity.vo.MessageVO;

import java.util.List;

public interface MessageService {
    MessageVO getMessage(Long id);

    void sendMessage(MessageSendDTO messageSendDTO);

    void updateMessageStatus(List<Long> ids);
}
