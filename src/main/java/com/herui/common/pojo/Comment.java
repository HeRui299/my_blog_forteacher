package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@TableName("tb_blog_comment")
@Data
public class Comment {

    // 主键
    @TableId("comment_id")
    private Integer commentId;

    // 对应的Blog
    @TableField("blog_id")
    private Integer blogId;

    // 评论者名称
    @TableField("commentator")
    private String commentator;

    // 评论人的邮箱
    @TableField("email")
    private String email;

    // 网址
    @TableField("website_url")
    private String websiteUrl;

    // 评论内容
    @TableField("comment_body")
    private String commentBody;

    // 评论提交时间
    @TableField("comment_create_time")
    private Date commentCreateTime;

    //评论时的ip地址
    @TableField("commentator_ip")
    private String commentatorIp;

    // 回复内容
    @TableField("reply_body")
    private String replyBody;

    // 回复时间
    @TableField("reply_create_time")
    private Date replyCreateTime;

    // 是否审核通过 0-未审核 1- 审核通过
    @TableField("comment_status")
    private boolean commentStatus;

    // 是否删除
    @TableField("is_deleted")
    private boolean isDeleted;
}