package com.xxd.mapper;

import com.xxd.datasource.DS;
import com.xxd.datasource.DataSourceName;
import com.xxd.entity.Blog;
import com.xxd.entity.BlogWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(BlogWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(BlogWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    BlogWithBLOBs selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BlogWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(BlogWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Blog record);

    @DS(name = DataSourceName.SLAVE)
    @Select("select * from blog")
    List<Blog> select();
}