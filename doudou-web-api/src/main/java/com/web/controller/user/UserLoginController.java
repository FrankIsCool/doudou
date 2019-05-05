package com.web.controller.user;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.service.model.UserLogin;
import com.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "v1/user")
@Api(value="UserLoginController",tags={"用户登录"})
public class UserLoginController {

    @Autowired
    public UserService userService;

    @RequestMapping(value ="/login", method= RequestMethod.POST)
    @ApiOperation(value="用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String")
    })

    public JsonResult<UserLogin> userLogin(String userName, String password, HttpServletRequest request){
        if(EmptyUtil.isEmpty(userName,password)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        String source = request.getHeader("source");
        if(EmptyUtil.isEmpty(source)){
            return JsonResult.error(ExceptionCode.ERRO_100001);
        }
        return userService.userLogin(userName,password,source);
    }
}
