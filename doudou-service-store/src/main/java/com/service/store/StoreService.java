package com.service.store;

import com.common.jsonResult.JsonResult;
import com.service.model.Store;

public interface StoreService {
    /**
     * 创建店铺
     * @param store     店铺实体类
     * @return
     */
    JsonResult<Integer> save (Store store);

    /**
     * 创建店铺
     * @param userId    创建人id
     * @param storeName 店铺名称
     * @return
     */
    JsonResult<Integer> save (long userId,String storeName);

    /**
     * 修改店铺状态
     * @param storeId   店铺id
     * @param state     店铺状态
     * @return
     */
    JsonResult<Integer> updateState(long storeId,int state);
}
