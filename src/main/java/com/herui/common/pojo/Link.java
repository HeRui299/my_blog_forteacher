package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_link")
public class Link {

    @TableId("link_id")
    private Integer link_id;

    @TableField("link_type")
    private Integer linkType;

    @TableField("link_name")
    private String linkName;

    @TableField("link_url")
    private String linkUrl;

    @TableField("link_description")
    private String linkDescription;

    @TableField("link_rank")
    private Integer linkRank;

    @TableField("is_deleted")
    private boolean isDeleted;

    @TableField("create_time")
    private Date createTime;
}
