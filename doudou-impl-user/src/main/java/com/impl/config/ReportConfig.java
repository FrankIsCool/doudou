package com.impl.config;


import com.service.balance.BalanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportConfig {
    @Autowired
    private BalanceReportService balanceReportService;
    /**
     * 每年一月一日凌晨二点开始执行
     */
    @Scheduled(cron="0 0 3 1 1 ?")
    private void yearBalaceReport() {
        balanceReportService.yearBalanceReport();
    }
    /**
     * 每季度一日凌晨二点开始执行
     */
    @Scheduled(cron="0 0 2 1 1/3 ?")
    private void quarterBalaceReport() {
        balanceReportService.quarterBalaceReport();
    }
    /**
     * 每月一日凌晨一点开始执行
     */
    @Scheduled(cron="0 0 1 1 * ?")
    private void monBalaceReport() {
        balanceReportService.monBalanceReport();
    }
    /**
     * 每周周一凌晨开始执行
     */
    @Scheduled(cron="0 0 0 ? * MON")
    private void weekBalaceReport() {
        balanceReportService.weekBalanceReport();
    }
    /**
     * m每五秒执行一次
     */
//    @Scheduled(cron="0/5 * * * * ?")
//    private void ssBalaceReport() {
//        System.out.println("开始执行----------------");
//        balanceReportService.weekBalanceReport();
//        balanceReportService.monBalanceReport();
//        balanceReportService.quarterBalaceReport();
//        balanceReportService.yearBalanceReport();
//        System.out.println("执行结束----------------");
//    }
}
