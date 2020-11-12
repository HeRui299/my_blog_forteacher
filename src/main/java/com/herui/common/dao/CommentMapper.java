package com.herui.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herui.common.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Update({"<script>" +

                "update tb_blog_comment set comment_status = #{value} " +
                "where comment_id in " +
                "<foreach collection='ids' item='id' open='(' close=')' separator=','>" +
                    "#{id}" +
                "</foreach>" +
            "</script>"})
    int updateBatchById(@Param("ids")List<Integer> ids,@Param("value") Object value);

}