package com.impl.label;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.jsonResult.Page;
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
        return JsonResult.success(labelMapper.save(label));
    }

    @Override
    public JsonResult<List<Label>> getLabels(int state, Integer pageNum,Integer pageSize) {
        if(EmptyUtil.isEmpty(state)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        Page page = new Page(pageNum,pageSize);
        return JsonResult.success(labelMapper.getLabels(state,page),page);
    }

    @Override
    public JsonResult<Label> getLabelInfo(String label) {
        if(EmptyUtil.isEmpty(label)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        return JsonResult.success(labelMapper.getLabel(label));
    }

    @Override
    public JsonResult<Integer> updateState(long id, int state) {
        if(EmptyUtil.isEmpty(state)||EmptyUtil.isEmpty(id)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        return JsonResult.success(labelMapper.updateState(id,state));
    }
}
