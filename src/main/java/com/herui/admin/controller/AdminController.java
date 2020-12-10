package com.herui.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.herui.common.pojo.User;
import com.herui.common.service.*;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private BlogCategoryServiceImpl blogCategoryService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private LinkServiceImpl linkService;
    /**
     * 管理首页的数据
     */
    @GetMapping("/Dashboard")
    public ModelAndView index(ModelAndView modelAndView){
        int blogCount = blogService.count();
        modelAndView.addObject("blogCount",blogCount);

        int commentCount = commentService.count();
        modelAndView.addObject("commentCount",commentCount);

        int categoryCount = blogCategoryService.count();
        modelAndView.addObject("categoryCount",categoryCount);

        int tagCount = tagService.count();
        modelAndView.addObject("tagCount",tagCount);

        int linkCount = linkService.count();
        modelAndView.addObject("linkCount",linkCount);

        modelAndView.setViewName("/admin/Dashboard");

        return modelAndView;
    }

    /**
     * 异步登录
     * @param user 用户实体
     * @return 返回json数据表示状态
     */
    @GetMapping(value = "/login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object login(User user,String code,@CookieValue String kaptchaOwner){
        if (!code.equalsIgnoreCase(kaptchaOwner)){
            return ResponseUtil.getJSONString(2,"验证码不正确,看不清点击图片切换");
        }
        // 构建查询条件
        QueryWrapper<User> wrapper  = new QueryWrapper<>();
        wrapper.eq("login_user_name",user.getLoginUserName());
        wrapper.eq("login_password",ResponseUtil.md5(user.getLoginPassword()));
        List<User> list = userService.list(wrapper);
        if (list != null && !list.isEmpty()){
            list.get(0).setLoginPassword(null);
            session.setAttribute("user",list.get(0));
            return ResponseUtil.getJSONString(0);
        }else{
            return ResponseUtil.getJSONString(1,"请检查你的账号或密码输入是否正确");
        }
    }

    @GetMapping("/kaptcha")
    @ResponseBody
    public void kaptcha(HttpServletResponse response){
        // 生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);

        // 验证码的归属
        String kaptchaOwner = ResponseUtil.generateUUID();
        Cookie cookie = new Cookie("kaptchaOwner",text);
        cookie.setMaxAge(60);
        cookie.setPath("/");
        response.addCookie(cookie);
        // 将图片输出给浏览器
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/logout")
    public String logout(){
        if (session.getAttribute("user")!=null) {
            session.removeAttribute("user");
        }
        return "/admin/login";
    }

}