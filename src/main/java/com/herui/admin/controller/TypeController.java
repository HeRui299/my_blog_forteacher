package com.herui.admin.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herui.common.pojo.Blog_Category;
import com.herui.common.service.BlogCategoryServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/admin")
public class TypeController {

    // 分类
    @Autowired
    private BlogCategoryServiceImpl blogCategoryService;

    /**
     * 查询分类列表
     * @return
     */
    @GetMapping("/listType")
    @ResponseBody
    public Object list(Page<Blog_Category> page){
        // 查询评论列表
        Page<Blog_Category> list = blogCategoryService.page(page);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",list.getRecords());
        map.put("count",list.getTotal());
        return map;
    }

    /**
     * 异步添加分类
     * @param category
     * @return
     */
    @PostMapping(value = "/insertType",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object insertType(Blog_Category category){
        return blogCategoryService.saveOrUpdate(category) ? ResponseUtil.getJSONString(0,"添加成功") : ResponseUtil.getJSONString(1,"添加失败");
    }

    /**
     * 因为修改何新增是同一个页面，所以我们需要初始化分类实体，要不然表单显示不出来喔
     */
    @GetMapping("/addType")
    public String toAddPage(Model model){
        model.addAttribute("type",new Blog_Category());
        return "/admin/addType";
    }

    /**
     *  根据id 查询出来分类的数据，回显在分类页面去
     * @param id 分类id
     * @return 分类页面与新增页面共用
     */
    @GetMapping("/toUpdateType/{typeId}")
    public String toUpdatePage(@PathVariable("typeId") Integer id, Model model){
        Blog_Category type = blogCategoryService.getById(id);
        model.addAttribute("type",type);
        return "/admin/addType";
    }

    /**
     * 批量删除何普通删除共用方法
     * @return
     */
    @PostMapping(value = "/deleteType",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Integer> ids){
        if (ids.size() ==1){
            return blogCategoryService.removeById(ids.get(0)) ? ResponseUtil.getJSONString(0,"删除成功") : ResponseUtil.getJSONString(1,"删除失败");
        }
        return blogCategoryService.removeByIds(ids) ? ResponseUtil.getJSONString(0,"删除成功") : ResponseUtil.getJSONString(1,"删除失败");
    }
}