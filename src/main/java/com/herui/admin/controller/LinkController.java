package com.herui.admin.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herui.common.pojo.Link;
import com.herui.common.pojo.Tag;
import com.herui.common.service.LinkServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller("/Link")
@RequestMapping("/admin")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    /**
     * 异步加载表格
     * @param page
     * @return
     */
    @GetMapping("/listLink")
    @ResponseBody
    public Object list(Page<Link> page){
        // 查询友链列表
        Page<Link> list = linkService.page(page);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",list.getRecords());
        map.put("count",list.getTotal());
        return map;
    }

    /**
     * 异步添加标签
     * @param link
     * @return
     */
    @PostMapping(value = "/insertLink",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object insertLink(Link link){
        return linkService.saveOrUpdate(link) ? ResponseUtil.getJSONString(0,"添加成功") : ResponseUtil.getJSONString(1,"添加失败");
    }

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
        Link link = linkService.getById(id);
        model.addAttribute("link",link);
        return "/admin/addLink";
    }

    /**
     * 批量删除何普通删除共用方法
     * @return
     */
    @PostMapping(value = "/deleteLink",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Integer> ids){
        if (ids.size() ==1){
            return linkService.removeById(ids.get(0)) ? ResponseUtil.getJSONString(0,"删除成功") : ResponseUtil.getJSONString(1,"删除失败");
        }
        return linkService.removeByIds(ids) ? ResponseUtil.getJSONString(0,"删除成功") : ResponseUtil.getJSONString(1,"删除失败");
    }
}