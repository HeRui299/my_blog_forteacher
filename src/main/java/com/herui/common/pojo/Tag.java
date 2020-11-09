package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_blog_tag")
@Data
public class Tag {

    @TableId(value = "tag_id",type = IdType.AUTO)
    private Integer tagId;

    @TableField("tag_name")
    private String tagName;

    @TableField("is_deleted")
    private boolean isDeleted;

    @TableField(value = "create_time",update = "now()")
    private Date createTime;

}
