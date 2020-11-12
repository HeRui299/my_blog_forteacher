package com.herui.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaseController<T>{

    /**
     * 分页查询
     * @return
     */
    public Object list(Page<T> page);

    /**
     * 异步添加标签
     * @param t
     * @return
     */
    public Object insert(T t);

    /**
     * 批量删除何普通删除共用方法
     * @return
     */
    public Object delete(@RequestParam("ids") List<Integer> ids);

//    /**
//     * 根据数据的id 批量改变数据的状态
//     * @param ids id集合
//     * @return
//     */
//    public Object updateStatus(List<Integer> ids);

}