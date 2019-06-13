package com.impl.browse;

import com.common.date.DatesUtil;
import com.common.empty.EmptyUtil;
import com.common.jsonResult.JsonResult;
import com.impl.dao.slaver.browse.BrowseLogReportMapper;
import com.service.browse.BrowseLogReportService;
import com.service.browse.BrowseLogService;
import com.service.model.BrowseLogReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class BrowseLogReportServiceImpl implements BrowseLogReportService {
    @Autowired
    BrowseLogService browseLogService;
    @Autowired
    BrowseLogReportMapper browseLogReportMapper;

    @Override
    public void dayBrowseLogReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date =dateFormat.format(DatesUtil.getBeginDayOfYesterday());
        BrowseLogReport browseLogReport =  new BrowseLogReport();

        browseLogReport.setReportTime(date);
        //总的访问量
        JsonResult<Integer> countByDay = browseLogService.getCountByDay(date);
        int num = EmptyUtil.isEmpty(countByDay.getData())?0:countByDay.getData();
        browseLogReport.setNum(num);
        //访问用户人次
        JsonResult<Integer> userCountByDay = browseLogService.getUserCounByDay(date);
        int userNum = EmptyUtil.isEmpty(userCountByDay.getData())?0:userCountByDay.getData();
        browseLogReport.setUserNum(userNum);

        //获取某天访问人的id
        JsonResult<List<Long>> userIdsByDay = browseLogService.getUserIdsByDay(date);
        List<Long> ids = EmptyUtil.isEmpty(userIdsByDay.getData())? null:userIdsByDay.getData();
        browseLogReport.setUserIdsVo(ids);

        //被访问的功能数
        JsonResult<Integer> urlCountByDay = browseLogService.getUrlCountByDay(date);
        int urlNum = EmptyUtil.isEmpty(urlCountByDay.getData())?0:urlCountByDay.getData();
        browseLogReport.setUrlNum(urlNum);

        //获取某天被访问的功能都有哪些
        JsonResult<List<String>> urlsByDay = browseLogService.getUrlsByDay(date);
        List<String> urls = EmptyUtil.isEmpty(urlsByDay.getData())? null:urlsByDay.getData();
        browseLogReport.setUrlsVo(urls);

        browseLogReportMapper.save(browseLogReport);
    }
}
