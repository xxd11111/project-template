package com.xxd.mapper;

import com.xxd.datasource.DS;
import com.xxd.datasource.DataSourceName;
import com.xxd.entity.Ejdcxx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface EjdcxxMapper {

    @DS(name = DataSourceName.MASTER)
    @Select("select * from E_JDCXX")
    List<Ejdcxx> select();

    /**
     *
     * @mbg.generated
     */
    int insert(Ejdcxx record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Ejdcxx record);
}