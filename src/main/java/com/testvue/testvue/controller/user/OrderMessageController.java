package com.testvue.testvue.controller.user;


import com.testvue.testvue.Service.OrderMessageService;
import com.testvue.testvue.enity.po.OrderMessage;
import com.testvue.testvue.enity.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/orderMessage")
public class OrderMessageController {

    @Autowired
    private OrderMessageService orderMessageService;

    @GetMapping
    public Result<List<OrderMessage>> getOrderMessages()
    {

       return Result.success(orderMessageService.getOrderMessages());


    }
    @DeleteMapping("/{messageId}")
    public Result deleteOrderMessageById(@PathVariable Long messageId)
    {
         orderMessageService.deleteOrderMessageById(messageId);
         return Result.success(messageId);
    }




}
