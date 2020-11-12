package com.herui.admin.controller;
import com.herui.common.dao.CommentMapper;
import com.herui.common.pojo.Comment;
import com.herui.common.service.CommentServiceImpl;
import com.herui.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@Controller
public class CommentController extends BaseControllerImpl<CommentServiceImpl,Comment>{

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 因为修改何新增是同一个页面，所以我们需要初始化链接实体，要不然表单显示不出来喔
     */
    @GetMapping("/addComment")
    public String toAddPage(Model model){
        model.addAttribute("comment",new Comment());
        return "/admin/addComment";
    }

    /**
     *  根据id 查询出来分类的数据，回显在分类页面去
     * @param id 分类id
     * @return 分类页面与新增页面共用
     */
    @GetMapping("/toUpdateComment/{commentId}")
    public String toUpdatePage(@PathVariable("commentId") Integer id, Model model){
        Comment link = this.getServiceImpl().getById(id);
        model.addAttribute("comment",link);
        return "/admin/addComment";
    }

    @RequestMapping(value = "/insertComment",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object insertComment(Comment comment) {
        Comment db = this.getServiceImpl().getById(comment.getCommentId());
        if(comment.getReplyBody() != null) {
            if (!comment.getReplyBody().substring(0, db.getReplyBody().length()).equals(db.getReplyBody())) {
                return ResponseUtil.getJSONString(1, "不可修改原来的评论喔");
            }
        }
        return this.getServiceImpl().saveOrUpdate(comment) ? ResponseUtil.getJSONString(0,"添加成功") : ResponseUtil.getJSONString(1,"添加失败");
    }

    /**
     * 根据数据的id 批量改变数据的状态
     * @param ids id集合
     * @return
     */
    @RequestMapping(value = "/updateStatus",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updateStatus(@RequestParam("ids") List<Integer> ids){
        return commentMapper.updateBatchById(ids,1) > 0 ? ResponseUtil.getJSONString(0,"修改成功") : ResponseUtil.getJSONString(1,"修改失败");
    }


}