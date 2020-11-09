package com.herui.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herui.common.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
