package com.impl.dao.slaver.balance;

import com.service.model.BalanceReport;
import org.apache.ibatis.annotations.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Mapper
public interface BalanceReportMapper {
    @Insert("insert into balance_report (user_id,balance_id,type,report_time,report_result,create_time) value (#{userId},#{balanceId},#{type},#{reportTime},#{reportResult},NOW())")
    int save(BalanceReport balanceReport);

    @InsertProvider(type = BalanceReportProvider.class, method = "batchInsert")
    int batchInsert(List<BalanceReport> balanceReports);

    @Select("select * from balance_report where user_id = #{userId} and balance_id = #{balanceId} and type = #{type} and report_time = #{reportTime}")
    BalanceReport getBalanceReport(@Param("userId")long userId,@Param("balanceId")long balanceId,@Param("type") int type,@Param("reportTime") String reportTime);

    class BalanceReportProvider{
        /* 批量插入 */
        public String batchInsert(Map map) {
            List<BalanceReport> balanceReports = (List<BalanceReport>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("insert into balance_report (user_id,balance_id,type,report_time,report_result,create_time) value ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].userId},#'{'list[{0}].balanceId},#'{'list[{0}].type},#'{'list[{0}].reportTime},#'{'list[{0}].reportResult},NOW())"
            );

            for (int i = 0; i < balanceReports.size(); i++) {
                sb.append(mf.format(new Object[] {i}));
                if (i < balanceReports.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }
    }
}
