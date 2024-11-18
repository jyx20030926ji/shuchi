package com.testvue.testvue.Service.impl;

import com.testvue.testvue.Service.MessageService;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.dto.MessageSendDTO;
import com.testvue.testvue.enity.po.Message;
import com.testvue.testvue.enity.vo.MessageVO;
import com.testvue.testvue.mapper.MessageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public MessageVO getMessage(Long id) {

       Message message=new Message();
       message.setSenderId(BaseCont.get().longValue());
       message.setReceiverId(id);
      List<Message> senderTOMessageList=messageMapper.getMessage(message);

      message.setReceiverId(BaseCont.get().longValue());
      message.setSenderId(id);
      List<Message>receiverToMessageList=messageMapper.getMessage(message);

        return MessageVO.builder().senderToMessage(senderTOMessageList)
                .receiverToMessage(receiverToMessageList)
                .build();


    }

    @Override
    public void sendMessage(MessageSendDTO messageSendDTO) {

        Message message=new Message();

        BeanUtils.copyProperties(messageSendDTO,message);

        message.setSenderId(BaseCont.get().longValue());
        message.setIsReadMessage(0);

        messageMapper.sendMessage(message);

    }

    @Override
    public void updateMessageStatus(List<Long> ids) {
        messageMapper.updateMessageStatus(ids);
    }
}
