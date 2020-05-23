package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.skpl.mailapp.entity.Admin;
import com.skpl.mailapp.entity.Diary;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.protocol.pop3.server.POP3Server;
import com.skpl.mailapp.protocol.smtp.SMTPServer;
import com.skpl.mailapp.service.AdminService;
import com.skpl.mailapp.service.DiaryService;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.Md5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
     * smtp服务
     *
     * @param action 1开0关
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("smtp")
    public JSONObject smtp(String action) {
        JSONObject response = new JSONObject();
        if (action == null) {
            response.put("flag", 0);
            response.put("msg", "错误的action");
        } else if (action.equals("1")) { //开启smtp
            if (SMTPServer.startServer()) {
                response.put("flag", 1);
                response.put("msg", "smtp开启成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "smtp开启失败");
            }
        } else if (action.equals("0")) { // 关闭smtp
            if (SMTPServer.stopServer()) {
                response.put("flag", 1);
                response.put("msg", "smtp关闭成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "smtp关闭失败");
            }
        } else { // 错误命令
            response.put("flag", "0");
            response.put("msg", "错误的action");
        }
        return response;
    }

    /**
     * pop3服务
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("pop3")
    public JSONObject pop3(String action) {
        POP3Server pop3Server = new POP3Server(109);
        JSONObject response = new JSONObject();
        if (action == null) {
            response.put("flag", 0);
            response.put("msg", "错误的action");
        } else if (action.equals("1")) { //开启smtp
            if (pop3Server.start()) {
                response.put("flag", 1);
                response.put("msg", "pop3开启成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "pop3开启失败");
            }
        } else if (action.equals("0")) { // 关闭smtp
            if (pop3Server.stop()) {
                response.put("flag", 1);
                response.put("msg", "pop3关闭成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "pop3关闭失败");
            }
        } else { // 错误命令
            response.put("flag", "0");
            response.put("msg", "错误的action");
        }
        return response;
    }

    /**
     * 邮箱过滤
     *
     * @author maple
     * @Date 2020/5/21 10:15
     * @Param [action] 1开0关
     */
    @GetMapping("filter")
    public JSONObject mailFilter(String action) {
        JSONObject response = new JSONObject();
        if (action == null) {
            response.put("flag", 0);
            response.put("msg", "错误的action");
        } else if ("0".equals(action)) {
            response.put("flag", 1);
            response.put("msg", "关闭成功");
        } else if ("1".equals(action)) {
            response.put("flag", 1);
            response.put("msg", "开启成功");
        } else {
            response.put("flag", 0);
            response.put("msg", "错误的命令");
        }
        return response;
    }

    /**
     * 登录
     *
     * @author maple
     * @Date 2020/5/20 21:55
     * @Param [userName, password]
     */
    @PostMapping("login")
    public JSONObject login(HttpSession session, String a_name, String a_password) {
        Admin admin = adminService.queryByName(a_name);
        System.out.println(a_name);
        JSONObject response = new JSONObject();
        if (admin == null) {
            response.put("flag", 0);
            response.put("msg", "账号不存在");
        } else {
            if (Md5Util.md5(a_password).equals(admin.getA_password())) {
                admin.setA_password(null);
                response.put("flag", 1);
                response.put("msg", "密码正确");
                response.put("user", admin);
                session.setAttribute("adminName", admin.getA_name());
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
        List<User> list = userService.queryAll();
        List<User.UserToWeb> resList = new ArrayList<>();
        for (User user : list) {
            user.setU_password(null);
            resList.add(user.toUserToWeb(user));
        }
        JSONObject response = new JSONObject();
        response.put("users", resList);
        return response;
    }

    /**
     * 添加用户
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("add")
    public JSONObject addUser(String u_name, String u_email, String u_password, String u_type) {
        JSONObject response = new JSONObject();
        if (userService.queryByEmail(u_email) == null) {
            User user = new User(0, u_email, u_name, u_password, u_type, new Date());
            userService.insert(user);
            response.put("flag", 1);
            response.put("msg", "添加成功");
        } else {
            response.put("flag", 0);
            response.put("msg", "邮箱号已存在");
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
    public JSONObject editUser(String u_id, String u_name, String u_email, String u_type) {
        JSONObject response = new JSONObject();
        Integer id;
        try {
            id = Integer.parseInt(u_id);
            if (userService.queryById(id) != null) {
                User user = new User(id, u_email, u_name, "", u_type, new Date());
                userService.update(user);
                response.put("flag", 1);
                response.put("msg", "修改成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "错误的id");
            }
        } catch (Exception e) {
            response.put("flag", 0);
            response.put("msg", "id不正确");
            System.out.println("修改时给定id不正确");
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
    public JSONObject deleteUser(String u_name, String u_email, String u_type) {
        JSONObject response = new JSONObject();
        User user = userService.queryByEmail(u_email);
        if (user != null) {
            userService.deleteById(user.getU_id());
            response.put("flag", 1);
            response.put("msg", "删除成功");
        } else {
            response.put("flag", 0);
            response.put("msg", "用户不存在");
        }
        return response;
    }

    /**
     * 查看日志  有点问题
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("diary")
    public JSONObject getDiary(String u_name, String u_email, String u_type) {
        JSONObject response = new JSONObject();
        List<Diary> diaries = diaryService.queryAll();
        List<Diary.DiaryToWeb> resDiaries = new ArrayList<>();
        for (Diary diary : diaries) {
            resDiaries.add(diary.toDiaryToWeb(diary));
            System.out.println(diary.toDiaryToWeb(diary));
        }
        response.put("diaries", resDiaries);
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