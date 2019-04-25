package com.web.controller.user;

import com.common.jsonResult.JsonResult;
import com.service.model.UserNode;
import com.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/user")
@Api(value="UserLoginController",tags={"用户登录"})
public class UserLoginController {

    @Autowired
    public UserService userService;

    @RequestMapping(value ="/login", method= RequestMethod.POST)
    @ApiOperation(value="用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String")
    })

    public JsonResult<Integer> userLogin(String userName, String password){
        UserNode userNode = new UserNode();
        userNode.setUserName(userName);
        userNode.setPassword(password);
        return userService.saveUser(userNode);
    }
}
