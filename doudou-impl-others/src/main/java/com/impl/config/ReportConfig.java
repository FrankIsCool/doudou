package com.impl.config;


import com.service.browse.BrowseLogReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BrowseLogReportService browseLogReportService;
    /**
     * 每天凌晨开始执行
     */
    @Scheduled(cron="0 0 * * * ?")
    private void monBalaceReport() {
        browseLogReportService.dayBrowseLogReport();
    }
    /**
     * m每五秒执行一次
     */
    @Scheduled(cron="0/5 * * * * ?")
    private void ssBalaceReport() {
//        logger.info("定时任务《浏览记录的统计》，开始执行---------");
//        browseLogReportService.dayBrowseLogReport();
//        logger.info("定时任务《浏览记录的统计》，执行结束---------");
    }
}
