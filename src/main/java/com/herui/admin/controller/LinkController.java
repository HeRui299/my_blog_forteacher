package com.herui.admin.controller;
import com.herui.common.pojo.Link;
import com.herui.common.service.LinkServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller("/Linkl")
@RequestMapping("/link")
public class LinkController extends BaseControllerImpl<LinkServiceImpl,Link>{

    /**
     * 因为修改何新增是同一个页面，所以我们需要初始化链接实体，要不然表单显示不出来喔
     */
    @GetMapping("/addLink")
    public String toAddPage(Model model){
        model.addAttribute("link",new Link());
        return "/admin/addLink";
    }

    /**
     *  根据id 查询出来分类的数据，回显在分类页面去
     * @param id 分类id
     * @return 分类页面与新增页面共用
     */
    @GetMapping("/toUpdateLink/{linkId}")
    public String toUpdatePage(@PathVariable("linkId") Integer id, Model model){
        Link link = this.getServiceImpl().getById(id);
        model.addAttribute("link",link);
        return "/admin/addLink";
    }
}