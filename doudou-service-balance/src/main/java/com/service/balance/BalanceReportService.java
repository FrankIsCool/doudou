package com.service.balance;

public interface BalanceReportService {
    /**
     * 每周的统计
     */
    void weekBalanceReport();
    /**
     * 每月的统计
     */
    void monBalanceReport();
    /**
     * 每年的统计
     */
    void yearBalanceReport();
    /**
     * 每季度的统计
     */
    void quarterBalaceReport();
}
