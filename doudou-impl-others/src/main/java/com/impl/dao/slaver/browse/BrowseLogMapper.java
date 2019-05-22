package com.impl.dao.slaver.browse;

import com.service.model.BrowseLog;
import org.apache.ibatis.annotations.Insert;

public interface BrowseLogMapper{

    @Insert("insert into user_browse_log(url,user_id,source,other_info,device_info,create_time) value (#{url},#{userId},#{source},#{otherInfo},#{deviceInfo},NOW())")
    int save (BrowseLog browseLog);
}
