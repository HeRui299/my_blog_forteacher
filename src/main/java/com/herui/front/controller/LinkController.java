package com.herui.front.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.herui.common.pojo.Link;
import com.herui.front.service.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
@Controller
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    @GetMapping("/link")
    @ResponseBody
    public Object link(){
        // 构建查询条件
        QueryWrapper<Link> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("link_rank");
        wrapper.eq("is_deleted",0);
        // 根据条件查询出来的结果
        List<Link> linkList = linkService.list(wrapper);
        return linkList;
    }
}