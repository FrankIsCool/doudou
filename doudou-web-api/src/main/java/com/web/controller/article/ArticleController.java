package com.web.controller.article;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.article.ArticleService;
import com.service.model.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "v1/")
@Api(value="BalanceController",tags={"文章"})
public class ArticleController {

    @Autowired
    public ArticleService articleService;
    @RequestMapping(value ="/user/article/save", method= RequestMethod.GET)
    @ApiOperation(value="创建文章")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "title", value = "标题", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "content", value = "内容", required = true, dataType = "String"),
    })
    public JsonResult<Integer> addArticle(HttpServletRequest request, String title,String content){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return articleService.addArticle(title,content,Long.valueOf(userId));
    }
    @RequestMapping(value ="/article/info", method= RequestMethod.GET)
    @ApiOperation(value="文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "articleId", value = "文章id", required = true, dataType = "Long")
    })
    public JsonResult<Article> getArticle(HttpServletRequest request, long articleId){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        String source = tokenVo.getSource();
        return articleService.getArticle(articleId,Long.valueOf(userId),Integer.valueOf(source));
    }
    @RequestMapping(value ="/user/article/isLike", method= RequestMethod.GET)
    @ApiOperation(value="点赞或取消")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "articleId", value = "文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "type", value = "1：点赞  2：取消", required = true, dataType = "Integer"),
    })
    public JsonResult<Integer> isLike(HttpServletRequest request, long articleId,int type){
        if(EmptyUtil.isEmpty(articleId)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        String source = tokenVo.getSource();
        return articleService.updateLike(articleId,Long.valueOf(userId),type, Integer.valueOf(source));
    }
}
