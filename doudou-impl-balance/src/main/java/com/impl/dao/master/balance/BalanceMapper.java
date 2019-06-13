package com.impl.dao.master.balance;

import com.service.model.Balance;
import org.apache.ibatis.annotations.*;

public interface BalanceMapper {

    @Insert("insert into balance(user_id,balance,freeze,total_balance,md5,state,create_time) value(#{userId},#{balance},#{freeze},#{totalBalance},#{md5},#{state},NOW())")
    int save (Balance balance);

    @Select("select * from balance where user_id = #{userId}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
            @Result(column = "user_id", property = "userId", javaType = java.lang.Integer.class),
            @Result(column = "total_balance", property = "totalBalance", javaType = java.math.BigDecimal.class)})
    Balance getBalanceByUserId (@Param("userId") long userId);

    @Update("update balance SET state = #{state} where id = #{id}")
    int updateState (@Param("id") long id,@Param("state")int state);

    @Update("<script> " +
                "update balance" +
                "<set>" +
                    "<if test=\"balance != null \">balance=#{balance},</if>" +
                    "<if test=\"freeze != null \">freeze=#{freeze},</if>" +
                    "<if test=\"totalBalance != null \">total_balance=#{totalBalance},</if>" +
                    "<if test=\"md5 != null and md5 !=''\">md5=#{md5},</if>" +
                    "<if test=\"state !=null \">state=#{state},</if>" +
                "</set>" +
                    "where id=#{id}" +
            " </script> ")
    int updateBalance (Balance balance);
}
