package com.herui.admin.controller;
import com.herui.common.pojo.Blog;
import com.herui.common.pojo.Comment;
import com.herui.common.pojo.User;
import com.herui.common.service.BlogCategoryServiceImpl;
import com.herui.common.service.BlogServiceImpl;
import com.herui.common.service.TagServiceImpl;
import com.herui.common.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseControllerImpl<BlogServiceImpl,Blog>{

    @Autowired
    private BlogCategoryServiceImpl blogCategoryService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private HttpSession session;

    /**
     * 新增博客
     * @param blog
     * @return
     */
    @Override
    public Object insert(Blog blog) {
        User user = (User) session.getAttribute("user");

        blog.setBlogAuthor(user.getAdminUserId()); // 设置博客作者
        blog.setBlogCategoryName(blogCategoryService.getById(blog.getBlogCategoryId()).getCategoryName());//设置分类名
        blog.setBlogCategoryName(blogCategoryService.getById(blog.getBlogCategoryId()).getCategoryName());
        return this.getServiceImpl().saveOrUpdate(blog);
    }

    /**
     * 因为修改何新增是同一个页面，所以我们需要初始化链接实体，要不然表单显示不出来喔
     */
    @GetMapping("/addBlog")
    public String toAddPage(Model model){
        setTypeAndTag(model);
        model.addAttribute("Blog",new Blog());
        return "/admin/addBlog";
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",blogCategoryService.list());
        model.addAttribute("tags",tagService.list());
    }

    /**
     *  根据id 查询出来分类的数据，回显在分类页面去
     * @param id 分类id
     * @return 分类页面与新增页面共用
     */
    @GetMapping("/toUpdateBlog/{blogId}")
    public String toUpdatePage(@PathVariable("blogId") Integer id, Model model){
        setTypeAndTag(model);
        Blog blog = this.getServiceImpl().getById(id);
        model.addAttribute("Blog",blog);
        return "/admin/addBlog";
    }

}