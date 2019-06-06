package com.service.label;

import com.common.jsonResult.JsonResult;
import com.service.model.Label;

import java.util.List;

public interface LabelService {

    /**
     * 创建标签
     * @param label     标签名称
     * @param remark    备注
     * @return
     */
    JsonResult<Integer> saveLabel(String label,String remark);

    /**
     * 创建标签
     * @param label     标签实体类
     * @return
     */
    JsonResult<Integer> saveLabel(Label label);

    /**
     * 获取标签列表
     * @param state     标签状态
     * @param pageNum   当前页码
     * @param pageSize  每页大小
     * @return
     */
    JsonResult<List<Label>> getLabels(int state, Integer pageNum,Integer pageSize);

    /**
     * 获取标签详情
     * @param label     标签名称
     * @return
     */
    JsonResult<Label> getLabelInfo(String label);

    /**
     * 修改标签状态
     * @param id
     * @param state
     * @return
     */
    JsonResult<Integer> updateState(long id,int state);

}
