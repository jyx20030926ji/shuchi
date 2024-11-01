package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.AddressService;
import com.testvue.testvue.enity.dto.AddressDTO;

import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.vo.AddressVO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addresses")
public class AddressController {


    @Autowired
    private AddressService addressService;

    /**
     * 根据用户id查询全部地址
     * @return
     */

    @GetMapping
    public Result<List<AddressVO>> getAllAddress()
    {
    List<AddressVO>addressVOList=addressService.getAllAddress();
        return Result.success(addressVOList);
    }

    /**
     * 插入一条新的地址
     * @param addressDTO
     * @return
     */
    @PostMapping
    public Result insertAddress(@RequestBody AddressDTO addressDTO)
    {
        addressService.insert(addressDTO);
        return Result.success();

    }

    /**
     * 根据地址id查询地址信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getAddressById(@PathVariable Long id)
    {
       AddressVO addressvo= addressService.getAddressById(id);

       return Result.success(addressvo);

    }

    /**
     * 修改地址信息
     * @param addressDTO
     * @return
     */

    @PutMapping
    public Result updateAddress(@RequestBody AddressDTO addressDTO)
    {
        addressService.updateAddress(addressDTO);
        return Result.success();
    }

    /**
     * 批量删除地址信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result deleteAddressById(@PathVariable List<Long> ids)
    {
        addressService.deleteAddressByIds(ids);

        return Result.success();
    }

    /**
     * 修改地址状态
     * @param id
     * @param status
     * @return
     */
    @PutMapping("/{id}/{status}")
    public Result updateAddressStatus(@PathVariable("id")Long id,@PathVariable("status") Integer status)
    {
        addressService.updateAddressStatus(id,status);
        return Result.success();
    }

}
