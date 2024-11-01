package com.testvue.testvue.Service;

import com.testvue.testvue.enity.dto.AddressDTO;
import com.testvue.testvue.enity.vo.AddressVO;

import java.util.List;

public interface AddressService {

    List<AddressVO> getAllAddress();

    void insert(AddressDTO addressDTO);

    void updateAddress(AddressDTO addressDTO);

    AddressVO getAddressById(Long id);

    void deleteAddressByIds(List<Long> ids);

    void updateAddressStatus(Long id, Integer status);
}
