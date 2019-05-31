package com.impl.dao.slaver.browse;

import com.service.model.BrowseLogReport;
import org.apache.ibatis.annotations.Insert;

public interface BrowseLogReportMapper {
    /**
     * 新增
     * @param browseLogReport
     * @return
     */
    @Insert("insert into browse_log_report(num,user_num,url_num,report_time,urls,user_ids,create_time) value (#{num},#{userNum},#{urlNum},#{reportTime},#{urls},#{userIds},NOW())")
    int save(BrowseLogReport browseLogReport);

}
