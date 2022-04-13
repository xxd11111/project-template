package com.xxd.mapper;

import com.xxd.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxd
 * @since 2021-12-12
 */
// dynamic-druid数据源可用使用该注解切换数据源，默认是主数据库，若配置了采用就近原则
// @DS("slave")
public interface UserMapper extends BaseMapper<User> {

}
