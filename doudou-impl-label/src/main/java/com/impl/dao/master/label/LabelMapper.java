package com.impl.dao.master.label;

import com.service.model.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LabelMapper {

    @Insert("insert into label (label,state,remark,create_time) value(#{label},#{state},#{remark},NOW())")
    int save (Label label);

    @Select("select * from label where state = #{state} LIMIT #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),})
    List<Label> getLabels (@Param("state")Integer state,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);

    @Update("update label SET state = #{state} where id = #{id}")
    int updateState (@Param("id") long id,@Param("state")int state);

    @Select("select * from label where label = #{label}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = java.sql.Date.class),})
    Label getLabel (@Param("label")String label);
}
