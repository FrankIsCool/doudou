package com.impl.dao.slaver.article;

import com.service.model.ArticleLikeLog;
import org.apache.ibatis.annotations.*;

public interface ArticleLikeLogMapper {

    @Insert("insert into article_like_log(user_id,article_id,state,source,create_time) value(#{userId},#{articleId},1,#{source},NOW())")
    int save(ArticleLikeLog articleLikeLog);

    @Select("select * from article_like_log where user_id = #{userId} and article_id = #{articleId}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
            @Result(column = "user_id", property = "userId", javaType = Long.class),
            @Result(column = "article_id", property = "articleId", javaType = Long.class)})
    ArticleLikeLog getArticleLikeLog(@Param("userId") long userId,@Param("articleId") long articleId);

    @Update("update article_like_log SET state = #{state},update_time = NOW() where id = #{id}")
    int updateState (@Param("id") long id, @Param("state")int state);
}
