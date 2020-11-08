package com.herui.front.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herui.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}