package com.suzy.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.reader.SheetReader;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.suzy.service.IUserService;
import com.suzy.entity.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suzy
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
@Resource
private IUserService userService;

@PostMapping
public boolean save(@RequestBody User user) {
    return userService.saveOrUpdate(user);
}

@DeleteMapping("/{id}")
public boolean delete(@PathVariable Integer id) {
    return userService.removeById(id);
}

@GetMapping
public List<User> findAll() {
    return userService.list();
}
@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
    return userService.removeByIds(ids);
}

@GetMapping("/{id}")
public User findOne(@PathVariable Integer id) {
    return userService.getById(id);
}

@GetMapping("/export")
public void export(HttpServletResponse response) throws Exception {
    List<User> list = userService.list();
    ExcelWriter writer = ExcelUtil.getWriter(true);
//    writer.addHeaderAlias("id", "id");
//    writer.addHeaderAlias("username", "用户名");
//    writer.addHeaderAlias("password", "密码");
//    writer.addHeaderAlias("nickname", "昵称");
//    writer.addHeaderAlias("email", "邮箱");
//    writer.addHeaderAlias("phone", "电话");
//    writer.addHeaderAlias("address", "地址");
//    writer.addHeaderAlias("createTime", "创建时间");
//    writer.addHeaderAlias("avatarUrl", "头像");
    writer.write(list, true);

    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
    String fileName = URLEncoder.encode("用户信息", "UTF-8");
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
    ServletOutputStream out = response.getOutputStream();
    writer.flush(out, true);
    out.close();
    writer.close();
}

@PostMapping("/import")
public Boolean imp(MultipartFile file) throws Exception {
    InputStream inputStream = file.getInputStream();
    ExcelReader reader = ExcelUtil.getReader(inputStream);
    List<User> list = reader.readAll(User.class);
    userService.saveBatch(list);
    return true;
}

@GetMapping("/page")
public Page<User> findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String address,
                           @RequestParam(defaultValue = "") String email) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (!"".equals(username)) { queryWrapper.like("username", username);}
    if (!"".equals(address)) { queryWrapper.like("address", address);}
    if (!"".equals(email)) { queryWrapper.like("email", email);}
    queryWrapper.orderByDesc("id");
    return userService.page(new Page<>(pageNum, pageSize),queryWrapper);
}
}

