package com.testvue.testvue.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.testvue.testvue.Service.AddressService;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.dto.AddressDTO;
import com.testvue.testvue.enity.po.Address;
import com.testvue.testvue.enity.vo.AddressVO;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.AddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {


    /**
     * 根据用户id查询用户全部的收货地址
     *
     *
     */

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<AddressVO> getAllAddress() {

        List<Address> addressList=addressMapper.getAllAddress(BaseCont.get().longValue());

        List<AddressVO> addressVOS = BeanUtil.copyToList(addressList, AddressVO.class);

        return addressVOS;

    }

    /**
     * 插入一条新的地址记录
     * @param addressDTO
     */
    @Override
    public void insert(AddressDTO addressDTO) {
        Address address=new Address();
        BeanUtils.copyProperties(addressDTO,address);
        address.setUserId(BaseCont.get().longValue());

        addressMapper.insert(address);

    }

    /**
     * 根据地址id查询地址信息
     * @param id
     * @return
     */
    @Override
    public AddressVO getAddressById(Long id) {
        Address address=addressMapper.getAddressById(id);
        AddressVO addressVO=new AddressVO();
        BeanUtils.copyProperties(address,addressVO);
        return addressVO;
    }

    /**
     * 批量删除地址信息
     * @param ids
     */

    @Override
    public void deleteAddressByIds(List<Long> ids) {
        addressMapper.deleteAddressByIds(ids);
    }

    /**
     * 修改默认地址
     * @param id
     * @param status
     */
    @Override
    public void updateAddressStatus(Long id, Integer status) {
        Address address=new Address();
        if(status==StatusConstant.ONE)
        {
            Address address1 = addressMapper.selectDefault(BaseCont.get().longValue());
            if(address1!=null)
            {
                throw new AccountNoExistException(500,"默认地址已经存在");
            }
        }



        address.setId(id);
        address.setStatus(status);
        addressMapper.updateAddress(address);
    }

    /**
     * 修改地址信息
     * @param addressDTO
     */
    @Override
    public void updateAddress(AddressDTO addressDTO) {

        Address address=new Address();
        BeanUtils.copyProperties(addressDTO,address);
        addressMapper.updateAddress(address);

    }



}


