package com.herui.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.herui.common.pojo.Comment;
import com.herui.common.service.CommentServiceImpl;
import com.herui.common.service.UserServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于我模块
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private CommentServiceImpl commentService;

    /**
     *  查询评论，并且分好一级评论和二级评论
     * @return
     */
    @RequestMapping
    public String commentList(Model model){
        // 查询出所有的评论
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted",0);

        List<Comment> list = commentService.list(wrapper);

        // 评论 Vo 列表
        List<Map<String,Object>> commentsVo = new ArrayList<>();

        if (list != null) {
            for (Comment comment : list) {
                Map<String,Object> map = new HashMap<>();
                // 评论内容
                String email = comment.getEmail().substring(0, comment.getEmail().indexOf("@"));
                comment.setEmail(email);
                map.put("comment",comment);
                if(!StringUtils.isEmpty(comment.getReplyBody())){
                    map.put("replyBody",comment.getReplyBody());
                    map.put("replyTime",comment.getReplyCreateTime());
                }
                commentsVo.add(map);
            }
        }

        model.addAttribute("commentVo",commentsVo);

        return "about";
    }

    /**
     * 添加评论
     */
    @GetMapping(value = "/addComment",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object addComment(Comment comment,String code,@CookieValue("kaptchaOwner") String kaptchaOwner){
        if (!code.equalsIgnoreCase(kaptchaOwner)){
            return ResponseUtil.getJSONString(2,"验证码不正确,看不清点击图片切换");
        }
        comment.setReplyBody("");
        comment.setCommentBody(HtmlUtils.htmlEscape(comment.getCommentBody()));
        return commentService.save(comment);
    }

}