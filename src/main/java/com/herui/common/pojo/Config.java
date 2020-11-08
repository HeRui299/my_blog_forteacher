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

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}