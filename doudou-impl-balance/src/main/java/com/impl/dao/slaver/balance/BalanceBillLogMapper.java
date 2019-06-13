package com.impl.dao.slaver.balance;

import com.service.model.BalanceBillLog;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
@Mapper
public interface BalanceBillLogMapper {

    @Insert("insert into balance_bill_log (user_id,balance_id,com.service.balance,operating,remark,create_time) value(#{userId},#{balanceId},#{com.service.balance},#{operating},#{remark},NOW())")
    int save (BalanceBillLog balanceLog);

    @Select("select * from balance_bill_log where user_id = #{userId} and (operating = 1 or operating = 2) LIMIT #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
            @Result(column = "user_id", property = "userId", javaType = java.lang.Integer.class),
            @Result(column = "balance_id", property = "balanceId", javaType = java.lang.Integer.class)})
    List<BalanceBillLog> getBalanceBillLogs (@Param("userId") long userId,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);

    @Select("select sum(com.service.balance) from balance_bill_log where user_id = #{userId} and balance_id = #{balanceId} and operating = #{operating}")
    BigDecimal getBalances (@Param("userId") long userId, @Param("balanceId") long balanceId, @Param("operating") int operating);

    @Select("select sum(com.service.balance) from balance_bill_log where user_id = #{userId} and operating = #{operating} and create_time > #{startTime} and create_time < #{endTime} ")
    BigDecimal getBalance (@Param("userId")long userId,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operating") int operating);
}
