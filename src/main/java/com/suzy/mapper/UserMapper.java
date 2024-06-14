package com.suzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suzy.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {}
//    @Select("SELECT * FROM sys_user")
//    List<User> findAll();
//
//    @Insert("INSERT into sys_user(username, password, nickname, email, phone, address) VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
//
//    int insert(User user);
//
//    int update(User user);
//    @Delete("delete from sys_user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like #{username} limit #{pageNum}, #{pageSize}")
////    List<User> selectPage(Integer pageNum, Integer pageSize);存在问题，多参数时要用@param来指明
//    List<User> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);
//    @Select("select count(*) from sys_user where username like concat('%',#{username}, '%')")
//    Integer selectTotal(String username);

