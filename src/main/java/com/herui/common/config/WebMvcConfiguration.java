package com.herui.common.config;

import com.herui.admin.controller.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/friend").setViewName("friend");
        registry.addViewController("/admin").setViewName("admin/login");
        registry.addViewController("/admin/index").setViewName("admin/index");
        registry.addViewController("/admin/Dashboard.html").setViewName("admin/Dashboard");
        registry.addViewController("/admin/addBlog.html").setViewName("admin/addBlog");
        registry.addViewController("/admin/blog_list.html").setViewName("admin/blog_list");
        registry.addViewController("/admin/comment_list.html").setViewName("admin/comment_list");
        registry.addViewController("/admin/type_list.html").setViewName("admin/type_list");
        registry.addViewController("/admin/tags_list.html").setViewName("admin/tags_list");
        registry.addViewController("/admin/friend_list.html").setViewName("admin/friend_list");
    }

//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/admin/kaptcha","/admin");
//    }
}