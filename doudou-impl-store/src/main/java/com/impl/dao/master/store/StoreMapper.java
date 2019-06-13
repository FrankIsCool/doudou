package com.impl.dao.master.store;

import com.service.model.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StoreMapper {

    @Insert("insert into store (user_id,name,state,create_time) value(#{userId},#{name},#{state},NOW())")
    int save(Store store);

    @Select("select * from store where state = #{state} LIMIT #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),
            @Result(column = "user_id", property = "userId", javaType = java.lang.Long.class)})
    List<Store> getStores(@Param("state") Integer state, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
//
//    @Update("update label SET state = #{state} where id = #{id}")
//    int updateState(@Param("id") long id, @Param("state") int state);
//
//    @Select("select * from label where label = #{label}")
//    @Results({
//            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),})
//    Label getLabel(@Param("label") String label);
}
