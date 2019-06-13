package com.impl.store;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.master.store.StoreMapper;
import com.service.model.Store;
import com.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;
    @Override
    public JsonResult<Integer> save(Store store) {
        if(EmptyUtil.isEmpty(store)||EmptyUtil.isEmpty(store.getUserId())
                ||EmptyUtil.isEmpty(store.getName())){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        store.setState(Store.STATE_APPLY);
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(storeMapper.save(store));
        return result;
    }

    @Override
    public JsonResult<Integer> save(long userId, String storeName) {
        if(EmptyUtil.isEmpty(userId)||EmptyUtil.isEmpty(storeName)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        Store store = new Store();
        store.setUserId(userId);
        store.setName(storeName);
        return save(store);
    }
}
