package com.impl.dao.slaver.user;

import com.impl.model.UserLoginLog;
import org.apache.ibatis.annotations.Insert;

public interface UserLoginLogMapper{

    @Insert("insert into user_login_log(user_id,source,other_info,login_time,create_time) value(#{userId},#{source},#{otherInfo},NOW(),NOW())")
    int save (UserLoginLog userLoginLog);
}
