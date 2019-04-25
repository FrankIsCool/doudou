package com.web.controller.user;

import com.common.exception.ExceptionCode;
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
@Api(value="UserRegisController",tags={"用户注册"})
public class UserRegisController {
    @Autowired
    public UserService userService;

    @RequestMapping(value ="/regis", method= RequestMethod.POST)
    @ApiOperation(value="用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "confirmPassword", value = "确认密码", required = true, dataType = "String")
    })

    public JsonResult<Integer> userRegis(String userName,String password,String confirmPassword){
        if(!password.equals(confirmPassword)){
           return JsonResult.error(ExceptionCode.ERRO_101001);
        }
        UserNode userNode = new UserNode();
        userNode.setUserName(userName);
        userNode.setPassword(password);
        return userService.saveUser(userNode);
    }
}
