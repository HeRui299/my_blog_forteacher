package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_blog_category")
@Data
public class Blog_Category {

    @TableId(value = "category_id",type = IdType.AUTO)
    // 分类表主键
    private Integer categoryId;

    @TableField("category_name")
    // 分类的名称
    private String categoryName;

    @TableField("category_icon")
    // 分类的图标
    private String categoryIcon;

    @TableField("category_rank")
    // 分类的排序值
    private Integer categoryBlank;

    @TableField("is_deleted")
    // 是否删除 0 = 否 1 = 是
    private Integer isDeleted;

    @TableField(value = "create_time",update = "now()")
    // 创建时间
    private Date createTime;
}