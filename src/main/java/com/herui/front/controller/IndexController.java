package com.herui.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.herui.common.pojo.*;
import com.herui.front.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController{

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private ConfigServiceImpl configService;

    // 分类
    @Autowired
    private BlogCategoryServiceImpl blogCategoryService;
    /**
     * 主页博客显示
     * @param model
     * @return 需要返回的值有博客的一些内容还有作者所以我们需要查询三张表然后组合数据
     */
    @GetMapping
    public Object index(Model model){
        // 存放组合数据
        List<Map<String,Object>> blogs = new ArrayList<>();

        // 未删除的博客，并且按照时间倒序
        QueryWrapper<Blog> wrapper = new QueryWrapper<Blog>().orderByDesc("create_time").eq("is_deleted",0);

        // 博客数据
        List<Blog> list = blogService.list(wrapper);

        // 遍历，组合数据
        for (Blog blog : list) {
            // 创建map存储数据
            Map<String,Object> map = new HashMap<>();
            // 博客
            map.put("blog",blog);
            // 查询本篇博客作者
            User user = userService.getById(blog.getBlogAuthor());
            map.put("user",user);
            // 博客分类
            Blog_Category type = blogCategoryService.getById(blog.getBlogCategoryId());
            map.put("type",type);
            // 把每篇博客添加到集合中，返回给页面
            blogs.add(map);
        }

        model.addAttribute("blogList",blogs);
        return "index";
    }

    @GetMapping("/blog_views")
    @ResponseBody
    public Object moreViews(){
        // 根据浏览量来排名
        QueryWrapper<Blog> wrapper = new QueryWrapper<Blog>().orderByDesc("blog_views");
        List<Blog> sortBlogByViews = blogService.list(wrapper);
        return sortBlogByViews;
    }


    /**
     *  热门标签
     * @return 返回json数据
     */
    @GetMapping("/tag")
    @ResponseBody
    public Object hotTags(){
        // 根据创建时间来排序
        QueryWrapper<Tag> wrapper = new QueryWrapper<Tag>().orderByDesc("create_time");
        List<Tag> hotTagList = tagService.list(wrapper);
        return hotTagList;
    }

    @GetMapping("/footer")
    @ResponseBody
    public Object footer(){
        List<Config> list = configService.list();
        return list;
    }

}