package com.herui.admin.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BaseControllerImpl<S extends ServiceImpl, T> implements BaseController<T> {

    // 注入Service
    @Autowired
    private S s;
     // 如果Controller层要自己定义方法，需要用到Service组件就用这个方法返回给他
    public S getServiceImpl(){
        return this.s;
    }

    /**
     * 通用查询方法
     * @param page
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @Override
    public Object list(Page<T> page) {
        Page<T> list = (Page<T>) s.page(page);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",list.getRecords());
        map.put("count",list.getTotal());
        return map;
    }

    /**
     * 通用添加方法
     * @param t
     * @return
     */
    @RequestMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    public Object insert(T t) {
        return s.saveOrUpdate(t) ? ResponseUtil.getJSONString(0,"添加成功") : ResponseUtil.getJSONString(1,"添加失败");
    }

    /**
     * 批量删除通用方法
     * 但不过不是真的删除只是说改变个状态
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    public Object delete(List<Integer> ids) {
        if (ids.size() ==1){
            return s.removeById(ids.get(0)) ? ResponseUtil.getJSONString(0,"删除成功") : ResponseUtil.getJSONString(1,"删除失败");
        }
        return s.removeByIds(ids) ? ResponseUtil.getJSONString(0,"删除成功") : ResponseUtil.getJSONString(1,"删除失败");
    }
}