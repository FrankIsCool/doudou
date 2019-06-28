package com.impl.dao.master.article;

import com.common.jsonResult.Page;
import com.service.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {

    @Insert("insert into article (user_id,title,content,browse_num,like_num,labels,create_time) value (#{userId},#{title},#{content},0,0,#{labels},NOW())")
    int save (Article article);

    @Select("select * from article where id = #{id}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
            @Result(column = "browse_num", property = "browseNum", javaType = java.lang.Integer.class),
            @Result(column = "like_num", property = "likeNum", javaType = java.lang.Integer.class),
            @Result(column = "user_id", property = "userId", javaType = java.lang.Long.class)})
    Article getArticleById (@Param("id") long id);

    @Update("update article SET browse_num = browse_num+1 where id = #{id}")
    int addBrowse(@Param("id") long id);

    @Update("update article SET like_num = like_num+1 where id = #{id}")
    int addLike(@Param("id") long id);

    @Update("update article SET like_num = like_num-1 where id = #{id}")
    int subLike(@Param("id") long id);

    @Update("update article SET labels = #{labels} where id = #{id}")
    int updateLabels(@Param("id") long id,@Param("labels") String labels);

    @Select("select * from article")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
            @Result(column = "browse_num", property = "browseNum", javaType = java.lang.Integer.class),
            @Result(column = "like_num", property = "likeNum", javaType = java.lang.Integer.class),
            @Result(column = "user_id", property = "userId", javaType = java.lang.Long.class)})
    List<Article> getArticles (@Param("page") Page page);
}
