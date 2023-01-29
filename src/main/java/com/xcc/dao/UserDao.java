package com.xcc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcc.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xcc
 * @version 1.0
 */

@Mapper
public interface UserDao extends BaseMapper<User> {
}
