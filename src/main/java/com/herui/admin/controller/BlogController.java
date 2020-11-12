package com.herui.admin.controller;
import com.herui.common.pojo.Blog;
import com.herui.common.service.BlogServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseControllerImpl<BlogServiceImpl,Blog>{

}