package com.web.controller.label;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.service.label.LabelService;
import com.service.model.Label;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/user/label")
@Api(value="LabelController",tags={"标签"})
public class LabelController {
    @Autowired
    public LabelService labelService;

    @RequestMapping(value ="/add", method= RequestMethod.GET)
    @ApiOperation(value="新增标签")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "label", value = "标签名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = false, dataType = "String"),
    })
    public JsonResult<Integer> add(String label,String remark){
        if(EmptyUtil.isEmpty(label)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        return labelService.saveLabel(label,remark);
    }

    @RequestMapping(value ="/list", method= RequestMethod.GET)
    @ApiOperation(value="获取标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "state", value = "标签状态，默认1正常", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页大小，默认20", required = false, dataType = "Integer"),
    })
    public JsonResult<List<Label>> getLabels(Integer state, Integer pageNum, Integer pageSize){
        if(EmptyUtil.isEmpty(pageNum)||pageNum<1){
            pageNum = 1;
        }
        if(EmptyUtil.isEmpty(pageSize)||pageSize<1){
            pageSize = 20;
        }
        if(EmptyUtil.isEmpty(state)){
            state = 1;
        }
        return labelService.getLabels(state,pageNum,pageSize);
    }
    @RequestMapping(value ="/updateState", method= RequestMethod.GET)
    @ApiOperation(value="获取标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "state", value = "标签状态，1:正常 2:删除", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "id", value = "标签id", required = true, dataType = "long")
    })
    public JsonResult<Integer> getLabels(long id,Integer state){
        if(EmptyUtil.isEmpty(state)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        return labelService.updateState(id,state);
    }
}
