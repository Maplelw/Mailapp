package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.skpl.mailapp.entity.Admin;
import com.skpl.mailapp.entity.Diary;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.protocol.pop3.server.POP3Server;
import com.skpl.mailapp.protocol.smtp.SMTPServer;
import com.skpl.mailapp.service.AdminService;
import com.skpl.mailapp.service.DiaryService;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.Md5Util;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统管理员  服务端管理员(Admin)表控制层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @Resource
    private DiaryService diaryService;

    /**
     * 返回整体对象
     */
    private JSONObject res;

    /**
     * 返回整体对象
     */
    private JSONObject data;

    /**
     * 初始化函数
     */
    private void init() {
        res = new JSONObject();
        data = new JSONObject();
    }

    /**
     * 开启smtp服务
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("startSmtp")
    public JSONObject startSmtp() {
        init();
        if (SMTPServer.startServer()) {
            res.put("status", "success");
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     * 关闭smtp服务
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("stopSmtp")
    public JSONObject stopSmtp() {
        init();
        if (SMTPServer.stopServer()) {
            res.put("status", "success");
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     * 开启pop3服务
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("startPop3")
    public JSONObject startPop3() {
        POP3Server pop3Server = new POP3Server(109);
        pop3Server.start();
        init();
        res.put("status", "success");
        return res;
    }

    /**
     * 关闭pop3服务
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("stopPop3")
    public JSONObject stopPop3() {
        POP3Server pop3Server = new POP3Server(109);
        pop3Server.stop();
        init();
        res.put("status", "success");
        return res;
    }

    /**
     * 登录
     *
     * @author maple
     * @Date 2020/5/20 21:55
     * @Param [userName, password]
     */
    @RequestMapping("login")
    public JSONObject login(String userName, @RequestParam("passWord") String password) {
        Admin admin = adminService.queryByName(userName);
        JSONObject response = new JSONObject();
        if (admin == null) {
            response.put("flag", 0);
            response.put("msg", "账号不存在");
            System.out.println(1);
        } else {
            System.out.println(Md5Util.md5("123456"));
            System.out.println(admin.getA_password());
            if (Md5Util.md5(password).equals(admin.getA_password())) {
                admin.setA_password(null);
                response.put("flag", 1);
                response.put("msg", "密码正确");
                response.put("user", admin);
            } else {
                response.put("flag", 0);
                response.put("msg", "密码错误");
            }
        }
        return response;
    }

    /**
     * 获取用户列表
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("list")
    public JSONObject userList() {
        List<User> users = userService.queryAll();
        JSONObject response = new JSONObject();
        response.put("users",users);
        return response;
    }

    /**
     * 添加用户
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("add")
    public JSONObject addUser(String u_name,String u_email,String u_password,String u_type) {
        JSONObject response = new JSONObject();
        if(userService.queryByEmail(u_email) == null) {
            User user = new User(0,u_email,u_name,u_password,u_type,new Date());
            userService.insert(user);
            response.put("flag",1);
            response.put("msg","添加成功");
        } else {
            response.put("flag",0);
            response.put("msg","邮箱号已存在");
        }

        return response;
    }

    /**
     * 编辑用户
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("edit")
    public JSONObject editUser(String u_name,String u_email,String u_type) {
    JSONObject response = new JSONObject();
        if(userService.queryByEmail(u_email) == null) {
            User user = new User(0,u_email,u_name,"密码",u_type,new Date());
            userService.insert(user);
            response.put("flag",1);
            response.put("msg","添加成功");
        } else {
            response.put("flag",0);
            response.put("msg","用户已存在");
        }

        return response;
    }
    /**
     * 删除用户
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("delete")
    public JSONObject deleteUser(String u_name,String u_email,String u_type) {
        JSONObject response = new JSONObject();
        User user = userService.queryByEmail(u_email);
        if( user != null) {
            userService.deleteById(user.getU_id());
            response.put("flag",1);
            response.put("msg","删除成功");
        } else {
            response.put("flag",0);
            response.put("msg","用户不存在");
        }
        return response;
    }

    /**
     * 查看日志
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("diary")
    public JSONObject getDiary(String u_name,String u_email,String u_type) {
        JSONObject response = new JSONObject();
        List<Diary> diaries = diaryService.queryAll();
        response.put("diaries",diaries);
        return response;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Admin selectOne(int id) {
        return this.adminService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @GetMapping("selectAllByLimit")
    public List<Admin> queryAll() {
        return this.adminService.queryAll();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public List<Admin> queryAllByLimit(int offset, int limit) {
        return this.adminService.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @GetMapping("insert")
    public Admin insert(Admin admin) {
        return this.adminService.insert(admin);
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @GetMapping("update")
    public Admin update(Admin admin) {
        return this.adminService.update(admin);
    }

    /**
     * 通过主键删除数据
     *
     * @param aNo 主键
     * @return 是否成功
     */
    @GetMapping("deleteById")
    public boolean deleteById(int aNo) {
        return this.adminService.deleteById(aNo);
    }

}