package com.impl.dao.slaver.browse;

import com.service.model.BrowseLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrowseLogMapper{
    /**
     * 新增
     * @param browseLog
     * @return
     */
    @Insert("insert into user_browse_log(url,user_id,source,other_info,device_info,create_time) value (#{url},#{userId},#{source},#{otherInfo},#{deviceInfo},NOW())")
    int save (BrowseLog browseLog);

    /**
     * 获取某天有多少次浏览
     * @param date
     * @return
     */
    @Select("select count(1) from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date}")
    Integer getCountByDay (@Param("date") String date);

    /**
     * 获取某天有多少人次访问
     * @param date
     * @return
     */
    @Select("select count(1) from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date} group by user_id")
    Integer getUserCounByDay (@Param("date") String date);
    /**
     * 获取某天访问人的id
     * @param date
     * @return
     */
    @Select("select user_id from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date} group by user_id")
    List<Long> getUserIdsByDay (@Param("date") String date);

    /**
     * 获取某天某个用户的访问情况
     * @param date
     * @param userId
     * @return
     */
    @Select("select * from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date} and user_id = #{userId}")
    List<BrowseLog> getBrowseLogsByDayUserId (@Param("date") String date,@Param("userId") long userId);


    /**
     * 获取某天有多少功能被访问
     * @param date
     * @return
     */
    @Select("select count(url) from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date} group by url")
    Integer getUrlCountByDay (@Param("date") String date);
    /**
     * 获取某天被访问的功能都有哪些
     * @param date
     * @return
     */
    @Select("select url from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date}")
    List<String> getUrlsByDay (@Param("date") String date);

    /**
     * 获取某天某个功能的访问情况
     * @param date
     * @param url
     * @return
     */
    @Select("select * from user_browse_log where DATE_FORMAT(create_time,'%Y-%d-%m') = #{date} and url = #{url}")
    List<BrowseLog> getBrowseLogsByDayUrl (@Param("date") String date,@Param("url")String url);
}
