package com.web.controller.user;

import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.model.UserNode;
import com.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "v1/user")
@Api(value="UserController",tags={"用户信息"})
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value ="/info", method= RequestMethod.GET)
    @ApiOperation(value="获取指定用户详细信息")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true, dataType = "Long")
    public JsonResult<UserNode> getUserInfo(Long userId){
        return userService.getUser(userId);
    }
    @RequestMapping(value ="/current/info", method= RequestMethod.GET)
    @ApiOperation(value="获取当前用户详细信息")
    public JsonResult<UserNode> getUserInfo(HttpServletRequest request){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        return userService.getUser(tokenVo.getUserCode());
    }
}
