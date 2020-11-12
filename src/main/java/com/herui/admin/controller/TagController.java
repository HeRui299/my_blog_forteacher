package com.herui.admin.controller;
import com.herui.common.pojo.Tag;
import com.herui.common.service.TagServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/tag")
public class TagController extends BaseControllerImpl<TagServiceImpl,Tag>{

    /**
     * 因为修改何新增是同一个页面，所以我们需要初始化标签实体，要不然表单显示不出来喔
     */
    @GetMapping("/add")
    public String toAddPage(Model model){
        model.addAttribute("tag",new Tag());
        return "/admin/addTag";
    }

    /**
     *  根据id 查询出来分类的数据，回显在分类页面去
     * @param id 分类id
     * @return 分类页面与新增页面共用
     */
    @GetMapping("/toUpdateTag/{tagId}")
    public String toUpdatePage(@PathVariable("tagId") Integer id, Model model){
        Tag tag = this.getServiceImpl().getById(id);
        model.addAttribute("tag",tag);
        return "/admin/addTag";
    }
}