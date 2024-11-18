package com.testvue.testvue.enity.vo;

import com.testvue.testvue.enity.po.ItemCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {
   private Long id;

   private List<ItemCart> itemCartList;

   private Double total;

}
