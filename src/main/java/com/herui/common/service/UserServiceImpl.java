package com.herui.common.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herui.common.dao.UserMapper;
import com.herui.common.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>{

}