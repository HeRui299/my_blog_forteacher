package com.herui.admin.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.herui.common.pojo.Config;
import com.herui.common.pojo.User;
import com.herui.common.service.ConfigServiceImpl;
import com.herui.common.service.UserServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/config")
public class ConfigController{

    @Autowired
    private ConfigServiceImpl configService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HttpSession session;

    /**
     * 异步返回底部设置信息
     * @return
     */
    @RequestMapping("/footer")
    @ResponseBody
    public Object footer(){
        QueryWrapper<Config> wrapper = new QueryWrapper<>();
        wrapper.likeRight("config_name","footer");
        List<Config> list = configService.list(wrapper);
        return list;
    }

    /**
     * 异步返回个人信息
     * @return
     */
    @RequestMapping("/your")
    @ResponseBody
    public Object your(){
        QueryWrapper<Config> wrapper = new QueryWrapper<>();
        wrapper.likeRight("config_name","your");
        List<Config> list = configService.list(wrapper);
        return list;
    }

    /**
     * 异步返回网站设置
     * @return
     */
    @RequestMapping("/website")
    @ResponseBody
    public Object website(){
        QueryWrapper<Config> wrapper = new QueryWrapper<>();
        wrapper.likeRight("config_name","website");
        List<Config> list = configService.list(wrapper);
        return list;
    }

    /**
     * 异步返回用户信息
     * @return
     */
    @RequestMapping("/userInformation")
    @ResponseBody
    public Object information(){
        User user = (User) session.getAttribute("user");
        return user;
    }

    /**
     * 异步修改网站信息
     */
    @RequestMapping(value = "/updateWebsite",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updateWebsite(@RequestParam(value = "websiteName", required = false) String websiteName,
                                @RequestParam(value = "websiteDescription", required = false) String websiteDescription,
                                @RequestParam(value = "websiteLogo", required = false) String websiteLogo,
                                @RequestParam(value = "websiteIcon", required = false) String websiteIcon){
        boolean updateResult = false;
        UpdateWrapper<Config> wrapper = null;
        if (!StringUtils.isEmpty(websiteName)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","websiteName");
            wrapper.set("config_value",websiteName);
            updateResult = configService.update(wrapper);
        }
        if (!StringUtils.isEmpty(websiteDescription)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","websiteDescription");
            wrapper.set("config_value",websiteDescription);
            updateResult = configService.update(wrapper);
        }
        if (!StringUtils.isEmpty(websiteLogo)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","websiteLogo");
            wrapper.set("config_value",websiteLogo);
            updateResult = configService.update(wrapper);
        }
        if (!StringUtils.isEmpty(websiteIcon)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","websiteIcon");
            wrapper.set("config_value",websiteIcon);
            updateResult = configService.update(wrapper);
        }

        return ResponseUtil.getJSONString(0,"修改成功");
    }
    /**
     * 异步修改用户信息
     */
    @RequestMapping(value = "/updateYour",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updateUser(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
                             @RequestParam(value = "yourName", required = false) String yourName,
                             @RequestParam(value = "yourEmail", required = false) String yourEmail){
        boolean updateResult = false;
        UpdateWrapper<Config> wrapper = null;
        System.out.println(yourName);
        if (!StringUtils.isEmpty(yourAvatar)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","yourAvatar");
            wrapper.set("config_value",yourAvatar);
            updateResult = configService.update(wrapper);
        }

        if (!StringUtils.isEmpty(yourName)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","yourName");
            wrapper.set("config_value",yourName);
            updateResult = configService.update(wrapper);
        }

        if (!StringUtils.isEmpty(yourEmail)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","yourEmail");
            wrapper.set("config_value",yourEmail);
            updateResult = configService.update(wrapper);
        }

        return ResponseUtil.getJSONString(0,"修改成功");
    }
    /**
     * 异步修改底部信息
     */
    @RequestMapping(value = "/updateFooter",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updateFooter(@RequestParam(value = "footerAbout", required = false) String footerAbout,
                               @RequestParam(value = "footerICP", required = false) String footerICP,
                               @RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
                               @RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
                               @RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL){
        boolean updateResult = false;
        UpdateWrapper<Config> wrapper = null;

        if (!StringUtils.isEmpty(footerAbout)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","footerAbout");
            wrapper.set("config_value",footerAbout);
            updateResult = configService.update(wrapper);
        }

        if (!StringUtils.isEmpty(footerICP)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","footerICP");
            wrapper.set("config_value",footerICP);
            updateResult = configService.update(wrapper);
        }

        if (!StringUtils.isEmpty(footerCopyRight)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","footerCopyRight");
            wrapper.set("config_value",footerCopyRight);
            updateResult = configService.update(wrapper);
        }

        if (!StringUtils.isEmpty(footerPoweredBy)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","footerPoweredBy");
            wrapper.set("config_value",footerPoweredBy);
            updateResult = configService.update(wrapper);
        }

        if (!StringUtils.isEmpty(footerPoweredByURL)) {
            wrapper = new UpdateWrapper<>();
            wrapper.eq("config_name","footerPoweredByURL");
            wrapper.set("config_value",footerPoweredByURL);
            updateResult = configService.update(wrapper);
        }

        return ResponseUtil.getJSONString(0,"修改成功");
    }

    /**
     * 异步修改用户信息
     */
    @RequestMapping(value = "/updateInfoMation",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updateInfoMation(User user){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

        wrapper.set("login_user_name",user.getLoginUserName());
        wrapper.set("nick_name",user.getNickName());
        wrapper.eq("admin_user_id",user.getAdminUserId());

        return userService.update(wrapper) ? ResponseUtil.getJSONString(0,"修改成功,请重新登录!") : ResponseUtil.getJSONString(0,"修改失败");
    }

    /**
     * 异步修改密码
     */
    @RequestMapping(value = "/updatePassword",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updatePassword(int adminUserId,String password,String newPassword){
        User user = userService.getById(adminUserId);
        if (!user.getLoginPassword().equals(ResponseUtil.md5(password))){
            return ResponseUtil.getJSONString(1,"旧密码与原密码不匹配");
        }

        if(!newPassword.isEmpty()){
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.set("login_password",ResponseUtil.md5(newPassword));
            wrapper.eq("admin_user_id",adminUserId);
            return userService.update(wrapper) ? ResponseUtil.getJSONString(0) : ResponseUtil.getJSONString(1);
        }
        return "其他意外情况喔";
    }
}