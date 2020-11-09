package com.herui.admin.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herui.common.pojo.Comment;
import com.herui.common.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/admin")
@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    /**
     * 查询评论列表
     * @return
     */
    @GetMapping("/listComment")
    @ResponseBody
    public Object list(Page<Comment> page){
        // 查询评论列表
        Page<Comment> list = commentService.page(page);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",list.getRecords());
        map.put("count",list.getTotal());
        return map;
    }
}