package com.herui.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herui.common.pojo.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友情链接
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}