package com.service.user;

import com.common.jsonResult.JsonResult;
import com.service.model.UserLogin;
import com.service.model.UserNode;

public interface UserService {

    JsonResult<Integer> saveUser(UserNode userNode);

    JsonResult<UserNode> getUser(long id);

    JsonResult<UserNode> getUser(String userCode);

    JsonResult<UserLogin> userLogin(String userName, String password, String source);
}
