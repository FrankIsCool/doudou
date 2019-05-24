package com.impl.balance;

import com.common.date.DatesUtil;
import com.common.empty.EmptyUtil;
import com.common.jsonResult.JsonResult;
import com.common.week.WeekUtil;
import com.impl.dao.slaver.balance.BalanceReportMapper;
import com.service.balance.BalanceBillLogService;
import com.service.balance.BalanceReportService;
import com.service.balance.BalanceService;
import com.service.model.*;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceReportServiceImpl implements BalanceReportService {
    @Autowired
    private UserService userService;
    @Autowired
    private BalanceReportMapper balanceReportMapper;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private BalanceBillLogService balanceBillLogService;

    private void balanceReport(int num,String startTime,String endTime,String lastStartTime,String lastEndTime,int reportType){
        //获取总的用户群体
        JsonResult<List<UserNode>> usersResult = userService.getUserAll();
        if(usersResult.getCode()!=JsonResult.SUCCESS){
            return ;
        }
        if(usersResult.getData().size()<1){
            return ;
        }
        List<UserNode> users = usersResult.getData();


        List<BalanceReport> balanceReports = new ArrayList<>();
        //循环用户群体，统计每个用户的资金
        for(UserNode userNode : users){
            long userId = userNode.getId();
            //获取当前用户的自己账户
            JsonResult<Balance> balanceResult = balanceService.getBalance(userId);
            if(EmptyUtil.isEmpty(balanceResult.getData())){
                continue;
            }
            //获取当前统计周期内的资金变化，充值，消费
            JsonResult<BigDecimal> rechargeResult = balanceBillLogService.getBalanceBillLogs(userId, startTime, endTime, BalanceBillLog.OPERATING_RECHARGE);
            JsonResult<BigDecimal> consumptionResult = balanceBillLogService.getBalanceBillLogs(userId, startTime, endTime, BalanceBillLog.OPERATING_CONSUMPTION);
            JsonResult<BigDecimal> freezeResult = balanceBillLogService.getBalanceBillLogs(userId, startTime, endTime, BalanceBillLog.OPERATING_FREEZE);
            JsonResult<BigDecimal> unfreezeResult = balanceBillLogService.getBalanceBillLogs(userId, startTime, endTime, BalanceBillLog.OPERATING_UNFREEZE);
            //获取上次的统计情况
            BalanceReport<ReportResultVo> balanceWeekReportOld = balanceReportMapper.getBalanceReport(userId, balanceResult.getData().getId(), BalanceBillLog.OPERATING_RECHARGE,
                    lastStartTime + "至" + lastEndTime);
            if(EmptyUtil.isEmpty(balanceWeekReportOld)){
                balanceWeekReportOld = new BalanceReport<>();
                ReportResultVo weekBalanceJsonReportOld = new ReportResultVo();
                weekBalanceJsonReportOld.setBalance(new BigDecimal("0.00").setScale(2,BigDecimal.ROUND_DOWN));
                weekBalanceJsonReportOld.setFreeze(new BigDecimal("0.00").setScale(2,BigDecimal.ROUND_DOWN));
                weekBalanceJsonReportOld.setTotalBalance(new BigDecimal("0.00").setScale(2,BigDecimal.ROUND_DOWN));
                StringBuilder str = new StringBuilder();
                str.append(weekBalanceJsonReportOld.getBalance()).append("&&").append(weekBalanceJsonReportOld.getFreeze()).append("&&").append(weekBalanceJsonReportOld.getTotalBalance()).append("doudou#@!");
                weekBalanceJsonReportOld.setMd5(DigestUtils.md5DigestAsHex(str.toString().getBytes()));
                balanceWeekReportOld.setReportResultVo(weekBalanceJsonReportOld);
            }
            //封装这次的统计结果
            BalanceReport<ReportResultVo> balanceReport = new BalanceReport<>();
            balanceReport.setUserId(userId);
            balanceReport.setBalanceId(balanceResult.getData().getId());
            balanceReport.setType(reportType);
            balanceReport.setReportTime(startTime+"至"+endTime);

            BigDecimal recharge = EmptyUtil.isEmpty(rechargeResult.getData())?new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN):rechargeResult.getData();
            BigDecimal consumption = EmptyUtil.isEmpty(consumptionResult.getData())?new BigDecimal("0.00").setScale(2,BigDecimal.ROUND_DOWN):consumptionResult.getData();
            BigDecimal freeze = EmptyUtil.isEmpty(freezeResult.getData())?new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN):freezeResult.getData();
            BigDecimal unfreeze = EmptyUtil.isEmpty(unfreezeResult.getData())?new BigDecimal("0.00").setScale(2,BigDecimal.ROUND_DOWN):unfreezeResult.getData();
            //封装统计结果中的子集结果数据
            ReportResultVo weekBalanceJsonReport = new ReportResultVo();
            weekBalanceJsonReport.setRecharge(recharge);
            weekBalanceJsonReport.setConsumption(consumption);
            weekBalanceJsonReport.setNum(num);
            weekBalanceJsonReport.setFreeze(balanceWeekReportOld.getReportResultVo().getFreeze().add(freeze).subtract(unfreeze));
            weekBalanceJsonReport.setBalance(balanceWeekReportOld.getReportResultVo().getBalance().add(recharge).subtract(consumption));
            weekBalanceJsonReport.setTotalBalance(weekBalanceJsonReport.getBalance().add(weekBalanceJsonReport.getFreeze()));
            StringBuilder str = new StringBuilder();

            str.append(weekBalanceJsonReport.getBalance()).append("&&")
                    .append(weekBalanceJsonReport.getFreeze()).append("&&")
                    .append(weekBalanceJsonReport.getTotalBalance()).append("&&")
                    .append(weekBalanceJsonReport.getConsumption()).append("&&")
                    .append(weekBalanceJsonReport.getRecharge()).append("&&")
                    .append(weekBalanceJsonReport.getNum()).append("&&")
                    .append("doudou#@!");
            weekBalanceJsonReport.setMd5(DigestUtils.md5DigestAsHex(str.toString().getBytes()));

            balanceReport.setReportResultVo(weekBalanceJsonReport);
            balanceReports.add(balanceReport);
        }
        balanceReportMapper.batchInsert(balanceReports);
    }

    @Override
    public void weekBalanceReport() {
        //获取当前需要统计的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime =dateFormat.format(DatesUtil.getBeginDayOfLastWeek());
        String endTime = dateFormat.format(DatesUtil.getEndDayOfLastWeek());
        int num = WeekUtil.getWeek(startTime);
        String lastStartTime =dateFormat.format(DatesUtil.getBeginDayOfLastTwoWeek());
        String lastEndTime = dateFormat.format(DatesUtil.getEndDayOfLastTwoWeek());
        balanceReport(num,startTime,endTime,lastStartTime,lastEndTime,BalanceReport.REPORT_WEEK);
    }

    @Override
    public void monBalanceReport() {
        //获取当前需要统计的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime =dateFormat.format(DatesUtil.getBeginDayOfLastMonth());
        String endTime = dateFormat.format(DatesUtil.getEndDayOfLastMonth());
        int num = WeekUtil.getMon(startTime);
        String lastStartTime =dateFormat.format(DatesUtil.getBeginDayOfLastTwoMonth());
        String lastEndTime = dateFormat.format(DatesUtil.getEndDayOfLastTwoMonth());
        balanceReport(num,startTime,endTime,lastStartTime,lastEndTime,BalanceReport.REPORT_MON);
    }
    @Override
    public void yearBalanceReport() {
        //获取当前需要统计的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime =dateFormat.format(DatesUtil.getBeginDayOfLastYear());
        String endTime = dateFormat.format(DatesUtil.getEndDayOfLastYear());
        int num = DatesUtil.getNowYear()-1;
        String lastStartTime =dateFormat.format(DatesUtil.getBeginDayOfLastTwoYear());
        String lastEndTime = dateFormat.format(DatesUtil.getEndDayOfLastTwoYear());
        balanceReport(num,startTime,endTime,lastStartTime,lastEndTime,BalanceReport.REPORT_YEAR);
    }

    @Override
    public void quarterBalaceReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime =dateFormat.format(DatesUtil.getBeginDayOfLastQuarter());
        String endTime = dateFormat.format(DatesUtil.getEndDayOfLastQuarter());
        int num = WeekUtil.getQuarter(startTime);
        String lastStartTime =dateFormat.format(DatesUtil.getBeginDayOfLastTwoQuarter());
        String lastEndTime = dateFormat.format(DatesUtil.getEndDayOfLastTwoQuarter());
        balanceReport(num,startTime,endTime,lastStartTime,lastEndTime,BalanceReport.REPORT_QUARTER);
    }
}
