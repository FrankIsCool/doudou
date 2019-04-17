package com.impl.ip;

import com.common.ip.IPAddressVo;
import com.service.ip.IPService;
import org.springframework.stereotype.Service;

@Service("ipService")
public class IPServiceImpl implements IPService {
    public IPAddressVo getAddress(String ip) {
        System.out.println("-----------------");
        return null;
    }
}
