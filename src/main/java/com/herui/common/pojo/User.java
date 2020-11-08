package com.herui.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_admin_user")
public class User {

    // 用户id
    @TableId("admin_user_id")
    private Integer adminUserId;
    // 用户名称
    @TableField("login_user_name")
    private String loginUserName;
    // 用户密码
    @TableField("login_password")
    private String loginPassword;
    // 用户的昵称
    @TableField("nick_name")
    private String nickName;
    // 是否被锁了
    @TableField("locked")
    private Integer locked;
}