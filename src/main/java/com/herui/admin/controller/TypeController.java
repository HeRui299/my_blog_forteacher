package com.herui.admin.controller;
import com.herui.common.pojo.Blog_Category;
import com.herui.common.service.BlogCategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/type")
public class TypeController extends BaseControllerImpl<BlogCategoryServiceImpl, Blog_Category>{

    /**
     * 因为修改何新增是同一个页面，所以我们需要初始化分类实体，要不然表单显示不出来喔
     */
    @GetMapping("/add")
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
        Blog_Category type = this.getServiceImpl().getById(id);
        model.addAttribute("type",type);
        return "/admin/addType";
    }
}