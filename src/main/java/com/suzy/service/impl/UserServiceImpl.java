package com.suzy.service.impl;

import com.suzy.entity.User;
import com.suzy.mapper.UserMapper;
import com.suzy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suzy
 * @since 2024-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
