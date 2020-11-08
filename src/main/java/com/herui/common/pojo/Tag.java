package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_blog_tag")
@Data
public class Tag {

    @TableId("tag_id")
    private Integer tagId;

    @TableField("tag_name")
    private String tagName;

    @TableField("is_deleted")
    private boolean isDeleted;

    @TableField("create_time")
    private Date createTime;

}
