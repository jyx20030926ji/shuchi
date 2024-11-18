package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.MessageService;
import com.testvue.testvue.enity.dto.MessageSendDTO;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public Result<MessageVO> getMessage(@PathVariable Long id)
    {
      MessageVO messageList=messageService.getMessage(id);
      return Result.success(messageList);
    }
    @PostMapping
    public Result sendMessage(@RequestBody MessageSendDTO messageSendDTO)
    {
        messageService.sendMessage(messageSendDTO);
        return Result.success();
    }
    @PutMapping("/{ids}")
    public Result updateMessageStatus(@PathVariable List<Long> ids)
    {
        messageService.updateMessageStatus(ids);
        return Result.success();
    }

}
