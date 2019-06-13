package com.impl.article;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.master.article.ArticleMapper;
import com.impl.dao.slaver.article.ArticleBrowseLogMapper;
import com.impl.dao.slaver.article.ArticleLikeLogMapper;
import com.service.article.ArticleService;
import com.service.model.*;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleBrowseLogMapper articleBrowseLogMapper;
    @Autowired
    ArticleLikeLogMapper articleLikeLogMapper;
    @Autowired
    UserService userService;
    @Override
    public JsonResult<Integer> addArticle(Article article) {

        if(EmptyUtil.isEmpty(article.getTitle(),article.getContent())||EmptyUtil.isEmpty(article.getUserId())){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(articleMapper.save(article));
        return result;
    }

    @Override
    public JsonResult<Integer> addArticle(String title, String content,List<Label> labels, long userId) {

        if(EmptyUtil.isEmpty(title,content)||EmptyUtil.isEmpty(userId)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        if(EmptyUtil.isNotEmpty(labels)){
            article.setLabelsVo(labels);
        }
        article.setUserId(userId);
        return addArticle(article);
    }
    @Override
    public JsonResult<Article> getArticle(long articleId, long userId, int source) {
        if(EmptyUtil.isEmpty(articleId)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        //获取文章详情
        Article article = articleMapper.getArticleById(articleId);
        if(EmptyUtil.isEmpty(article)){
            return JsonResult.error(ExceptionCode.ERRO_408);
        }

        //获取作者名
        JsonResult<UserNode> userResult = userService.getUser(userId);
        if(EmptyUtil.isEmpty(userResult.getData())){
            return new JsonResult<>();
        }
        article.setUserName(userResult.getData().getUserName());
        //修改访问次数
        articleMapper.addBrowse(article.getId());
        //添加访问记录
        ArticleBrowseLog articleBrowseLog = new ArticleBrowseLog();
        articleBrowseLog.setArticleId(article.getId());
        articleBrowseLog.setSource(source);
        articleBrowseLog.setUserId(userId);
        articleBrowseLogMapper.save(articleBrowseLog);

        JsonResult<Article> result = new JsonResult<>();
        if(EmptyUtil.isEmpty(userId)){
            result.setData(article);
            return result;
        }
        //获取点赞记录
        ArticleLikeLog articleLikeLog = articleLikeLogMapper.getArticleLikeLog(userId, article.getId());
        if(EmptyUtil.isNotEmpty(articleLikeLog)&&articleLikeLog.getState()==ArticleLikeLog.STATE_NORMAL){
            article.setLike(true);
        }
        result.setData(article);
        return result;
    }

    @Override
    public JsonResult<Integer> updateLike(long articleId,long userId, int type, int source) {

        if(EmptyUtil.isEmpty(articleId)||EmptyUtil.isEmpty(userId)||EmptyUtil.isEmpty(type)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        JsonResult<Integer> result = new JsonResult<>();
        //获取点赞记录
        ArticleLikeLog articleLikeLog = articleLikeLogMapper.getArticleLikeLog(userId, articleId);
        //如果没有记录，并且是点赞操作时，需要添加点赞记录
        if(EmptyUtil.isEmpty(articleLikeLog) && type<=1){
            articleLikeLog = new ArticleLikeLog();
            articleLikeLog.setArticleId(articleId);
            articleLikeLog.setState(ArticleLikeLog.STATE_NORMAL);
            articleLikeLog.setUserId(userId);
            articleLikeLog.setSource(source);
            articleLikeLogMapper.save(articleLikeLog);
        }
        //如果有记录，并且是点赞操作时，而且是取消点赞的状态下，需要修改点赞记录的状态
        if(EmptyUtil.isNotEmpty(articleLikeLog) && type<=1 && articleLikeLog.getState()==ArticleLikeLog.STATE_CANCEL){
            articleLikeLogMapper.updateState(articleLikeLog.getId(),ArticleLikeLog.STATE_NORMAL);
            result.setData(articleMapper.addLike(articleId));
            return result;
        }
        //如果有记录，并且是点赞操作时，而且是点赞的状态下，不处理
        if(EmptyUtil.isNotEmpty(articleLikeLog) && type<=1 && articleLikeLog.getState()==ArticleLikeLog.STATE_NORMAL){
            return result;
        }
        //如果没有记录，并且是取消点赞操作时，不处理
        if(EmptyUtil.isEmpty(articleLikeLog) && type>1){
            return result;
        }
        //如果没有记录，并且是取消点赞操作时，而且是取消点赞的状态下，不处理
        if(EmptyUtil.isNotEmpty(articleLikeLog) && type>1 && articleLikeLog.getState()==ArticleLikeLog.STATE_CANCEL){
            return result;
        }
        //如果没有记录，并且是取消点赞操作时，而且是点赞的状态下，需要修改点赞记录的状态
        if(EmptyUtil.isNotEmpty(articleLikeLog) && type>1 && articleLikeLog.getState()==ArticleLikeLog.STATE_NORMAL){
            articleLikeLogMapper.updateState(articleLikeLog.getId(),ArticleLikeLog.STATE_CANCEL);
            result.setData(articleMapper.subLike(articleId));
            return result;
        }
        return result;
    }

    @Override
    public JsonResult<Integer> updateLabel(long articleId, List<Label> labels) {
        if(EmptyUtil.isEmpty(articleId) || EmptyUtil.isEmpty(labels)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(articleMapper.updateLabels(articleId, JSONObject.toJSONString(labels)));
        return result;
    }

    @Override
    public JsonResult<List<Article>> getArticles(Integer pageNum, Integer pageSize) {
        if(EmptyUtil.isEmpty(pageNum)||pageNum<1){
            pageNum = 1;
        }
        if(EmptyUtil.isEmpty(pageSize)||pageSize<1){
            pageSize = 20;
        }
        pageNum=(pageNum-1)*pageSize;
        JsonResult<List<Article>> result = new JsonResult<>();
        List<Article> articles = articleMapper.getArticles(pageNum, pageSize);
        if(EmptyUtil.isEmpty(articles)){
            result.setData(articles);
            return result;
        }
        //去重（用户id），获取最少的用户id及对应的文章
        Map<Long,List<Article>> map = new HashMap<>();
        for (Article article : articles) {
            List<Article> articles1 ;
            if(!map.containsKey(article.getUserId())){
                articles1 = new ArrayList<>();
            }else {
                articles1 = map.get(article.getUserId());
            }
            articles1.add(article);
            map.put(article.getUserId(),articles1);
        }
        //获取所有的作者
        Set<Long> keys = map.keySet();
        List<Long> userIds = new ArrayList<>();
        for(long userId:keys){
            userIds.add(userId);
        }
        //组装数据
        articles.clear();
        JsonResult<List<UserNode>> users = userService.getUsers(userIds);
        for(UserNode userNode:users.getData()){
            List<Article> articles1 = map.get(userNode.getId());
            for(Article article : articles1){
                article.setUserName(userNode.getUserName());
            }
            articles.addAll(articles1);
        }
        result.setData(articles);
        return result;
    }
}