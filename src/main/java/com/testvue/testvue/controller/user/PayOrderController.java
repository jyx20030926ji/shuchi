package com.testvue.testvue.controller.user;


import com.testvue.testvue.Service.PayOrderService;
import com.testvue.testvue.enity.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/pays")
public class PayOrderController {

    @Autowired
    private PayOrderService payOrderService;

  @PutMapping("/{id}")
    public Result payOrders(@PathVariable Long id)

  {
      payOrderService.payOrders(id);
      return Result.success();
  }


}
