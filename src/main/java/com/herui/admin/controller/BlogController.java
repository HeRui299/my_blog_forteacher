package com.herui.admin.controller;

import com.herui.common.pojo.Blog;
import com.herui.front.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    /**
     * 新增博客
     */
    @PostMapping("/insertBlog")
    @ResponseBody
    public Object addBlog(Blog blog){
        return "";
    }

    /**
     * 博客列表
     */
    @GetMapping("/listBlog")
    @ResponseBody
    public Object list(){
        // 查询博客列表
        List<Blog> list = blogService.list();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",list);
        map.put("count",list.size());
        return map;
    }

}