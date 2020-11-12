package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("tb_blog")
public class Blog {

    // 博客id
    @TableId(type = IdType.AUTO)
    private Integer blogId;

    // 博客标题
    private String blogTitle;

    // 博客自定义路径url
    private String blogSubUrl;

    // 博客封面图
    private String blogCoverImage;

    // 博客内容
    private String blogContent;

    // 博客分类id
    private Integer blogCategoryId;

    // 博客作者
    private Integer blogAuthor;

    // 博客分类(冗余字段)
    private String blogCategoryName;

    // 博客标签
    private String blogTags;

    // 0-草稿 1-发布
    private String blogStatus;

    // 阅读量
    private Integer blogViews;

    // 0-允许评论 1-不允许评论
    private Integer enableComment;

    // 是否删除 0=否 1=是
    private Integer isDeleted;

    // 添加时间
    @TableField(update = "now()")
    private Date createTime;

    // 修改时间
    @TableField(update = "now()")
    private Date updateTime;
}