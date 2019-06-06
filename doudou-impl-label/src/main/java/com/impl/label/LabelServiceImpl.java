package com.impl.label;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.master.label.LabelMapper;
import com.service.label.LabelService;
import com.service.model.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;
    @Override
    public JsonResult<Integer> saveLabel(String label, String remark) {
        if(EmptyUtil.isEmpty(label)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        Label labels = new Label();
        labels.setLabel(label);
        labels.setRemark(remark);
        labels.setState(Label.STATE_NORMAL);
        return saveLabel(labels);
    }

    @Override
    public JsonResult<Integer> saveLabel(Label label) {
        if(EmptyUtil.isEmpty(label)||EmptyUtil.isEmpty(label.getLabel())){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        JsonResult<Integer> result = new JsonResult<>();
        Label label1 = labelMapper.getLabel(label.getLabel());
        if(EmptyUtil.isEmpty(label1)){
            result.setData(labelMapper.save(label));
            return result;
        }
        if(label1.getState()==Label.STATE_NORMAL){
            result.setData(1);
            return result;
        }
        result.setData(labelMapper.updateState(label1.getId(),Label.STATE_NORMAL));
        return result;
    }

    @Override
    public JsonResult<List<Label>> getLabels(int state, Integer pageNum,Integer pageSize) {
        if(EmptyUtil.isEmpty(state)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        if(EmptyUtil.isEmpty(pageNum)||pageNum<1){
            pageNum = 1;
        }
        if(EmptyUtil.isEmpty(pageSize)||pageSize<1){
            pageSize = 20;
        }
        pageNum=(pageNum-1)*pageSize;
        JsonResult<List<Label>> result = new JsonResult<>();
        result.setData(labelMapper.getLabels(state,pageNum,pageSize));
        return result;
    }

    @Override
    public JsonResult<Label> getLabelInfo(String label) {
        if(EmptyUtil.isEmpty(label)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        JsonResult<Label> result = new JsonResult<>();
        result.setData(labelMapper.getLabel(label));
        return result;
    }

    @Override
    public JsonResult<Integer> updateState(long id, int state) {
        if(EmptyUtil.isEmpty(state)||EmptyUtil.isEmpty(id)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(labelMapper.updateState(id,state));
        return result;
    }
}
