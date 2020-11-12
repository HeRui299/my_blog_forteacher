package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_config")
public class Config {

    @TableField("config_name")
    private String configName;

    @TableField("config_value")
    private String configValue;

    @TableField(value = "create_time",update = "now()")
    private Date createTime;

    @TableField(value = "update_time",update = "now()")
    private Date updateTime;
}