package com.service.article;

import com.common.jsonResult.JsonResult;
import com.service.model.Article;

public interface ArticleService {
    /**
     * 添加文章
     * @param article   文章实体
     * @return
     */
    JsonResult<Integer> addArticle(Article article);

    /**
     * 添加文章
     * @param title     标题
     * @param content   内容
     * @param userId    作者
     * @return
     */
    JsonResult<Integer> addArticle(String title,String content,long userId);

    /**
     * 获取文章详情
     * @param articleId     文章id
     * @param userId        浏览用户id
     * @param source        登录源
     * @return
     */
    JsonResult<Article> getArticle(long articleId, long userId, int source);

    /**
     点赞或者取消点赞
     * @param articleId     文章id
     * @param userId        浏览用户id
     * @param type          1:点赞 2取消点赞
     * @param source        登录源
     * @return
     */
    JsonResult<Integer> updateLike(long articleId,long userId,int type, int source);
}
