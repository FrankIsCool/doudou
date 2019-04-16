package com.service.ip;

import com.common.ip.IPAddressVo;

public interface IPService {

    IPAddressVo getAddress(String ip);
}
