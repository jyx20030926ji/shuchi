package com.testvue.testvue.controller.user;


import com.testvue.testvue.Service.PayOrderService;
import com.testvue.testvue.enity.dto.PayOrderDTO;
import com.testvue.testvue.enity.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
   @PostMapping
   public Result payOrder(@RequestBody PayOrderDTO payOrderDTO)
   {
       payOrderService.payOrder(payOrderDTO);
       return Result.success();
   }

}
