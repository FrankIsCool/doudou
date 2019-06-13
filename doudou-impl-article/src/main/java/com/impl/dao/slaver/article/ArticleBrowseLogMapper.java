package com.impl.dao.slaver.article;

import com.service.model.ArticleBrowseLog;
import org.apache.ibatis.annotations.Insert;

public interface ArticleBrowseLogMapper {

    @Insert("insert into article_browse_log (user_id,article_id,source,create_time) value(#{userId},#{articleId},#{source},NOW())")
    int save(ArticleBrowseLog articleBrowseLog);
//
//    @Select("select * from article where id = #{id}")
//    @Results({
//            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
//            @Result(column = "user_id", property = "userId", javaType = Long.class)})
//    Article getArticleById(@Param("id") long id);
}
