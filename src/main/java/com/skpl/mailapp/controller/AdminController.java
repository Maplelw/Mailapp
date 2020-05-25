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
<<<<<<< HEAD
=======
     * pop3服务
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
    @GetMapping("pop3")
    public JSONObject pop3fake(String action) {
        JSONObject response = new JSONObject();
        if (action == null) {
            response.put("flag", 0);
            response.put("msg", "错误的action");
        } else if (action.equals("1")) { //开启smtp
            response.put("flag", 1);
            response.put("msg", "pop3开启成功");
        } else if (action.equals("0")) { // 关闭smtp
            response.put("flag", 1);
            response.put("msg", "pop3关闭成功");
        } else { // 错误命令
            response.put("flag", "0");
            response.put("msg", "错误的action");
        }
        return response;
    }

    /**
>>>>>>> dev
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
<<<<<<< HEAD
        } else if (action.equals("1")) { //开启smtp
=======
        } else if ("1".equals(action)) { //开启smtp
>>>>>>> dev
            if (SMTPServer.startServer()) {
                response.put("flag", 1);
                response.put("msg", "smtp开启成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "smtp开启失败");
            }
<<<<<<< HEAD
        } else if (action.equals("0")) { // 关闭smtp
=======
        } else if ("0".equals(action)) { // 关闭smtp
>>>>>>> dev
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
<<<<<<< HEAD
     * pop3服务
=======
     * pop3服务 real
>>>>>>> dev
     *
     * @author maple
     * @Date 2020/5/20 16:18
     */
<<<<<<< HEAD
    @GetMapping("pop3")
=======
    @GetMapping("pop3r")
>>>>>>> dev
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
<<<<<<< HEAD
        response.put("flag", 0);
        response.put("msg", "还没有做，就测试一下接口");
//        if (action == null) {
//            response.put("flag", 0);
//            response.put("msg", "错误的action");
//        } else if (action.equals("1")) { //开启smtp
//            if (SMTPServer.startServer()) {
//                response.put("flag", 1);
//                response.put("msg", "smtp开启成功");
//            } else {
//                response.put("flag", 0);
//                response.put("msg", "smtp开启失败");
//            }
//        } else if (action.equals("0")) { // 关闭smtp
//            if (SMTPServer.stopServer()) {
//                response.put("flag", 1);
//                response.put("msg", "smtp关闭成功");
//            } else {
//                response.put("flag", 0);
//                response.put("msg", "smtp关闭失败");
//            }
//        } else { // 错误命令
//            response.put("flag", "0");
//            response.put("msg", "错误的action");
//        }
=======
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
>>>>>>> dev
        return response;
    }

    /**
     * 登录
     *
     * @author maple
     * @Date 2020/5/20 21:55
     * @Param [userName, password]
     */
<<<<<<< HEAD
    @RequestMapping("login")
    public JSONObject login(HttpSession session,String a_no, String a_password) {
        Admin admin = adminService.queryByName(a_no);
=======
    @PostMapping("login")
    public JSONObject login(HttpSession session, String a_name, String a_password) {
        Admin admin = adminService.queryByName(a_name);
>>>>>>> dev
        JSONObject response = new JSONObject();
        if (admin == null) {
            response.put("flag", 0);
            response.put("msg", "账号不存在");
<<<<<<< HEAD
            System.out.println(1);
=======
>>>>>>> dev
        } else {
            if (Md5Util.md5(a_password).equals(admin.getA_password())) {
                admin.setA_password(null);
                response.put("flag", 1);
                response.put("msg", "密码正确");
                response.put("user", admin);
<<<<<<< HEAD
                session.setAttribute("adminName",admin.getA_name());
=======
                session.setAttribute("adminName", admin.getA_name());
>>>>>>> dev
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
<<<<<<< HEAD
    public JSONObject userList() {
        List<User> list = userService.queryAll();
        List<User.UserToApp> resList = new ArrayList<>();
=======
    public JSONObject userList(HttpSession session,String u_name) {
        List<User> list;
        // 全部用户
        if(u_name == null || "".equals(u_name)) {
            list = userService.queryAll();
        } else { // 模糊搜索用户
            list = userService.queryALLBySearch(u_name);
        }
        List<User.UserToWeb> resList = new ArrayList<>();
>>>>>>> dev
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
<<<<<<< HEAD
    @GetMapping("add")
    public JSONObject addUser(String u_name, String u_email, String u_password, String u_type) {
        JSONObject response = new JSONObject();
        if (userService.queryByEmail(u_email) == null) {
            User user = new User(0, u_email, u_name, u_password, u_type, new Date());
            userService.insert(user);
            response.put("flag", 1);
            response.put("msg", "添加成功");
=======
    @PostMapping("add")
    public JSONObject addUser(HttpSession session, String u_name, String u_email, String u_password, String u_type) {
        JSONObject response = new JSONObject();
        String adminName = (String) session.getAttribute("adminName");
        if (userService.queryByEmail(u_email) == null) {
            String type;
            // 增加管理员
            if ("0".equals(u_type)) {
                if (adminService.queryByName(u_email) != null) {
                    response.put("flag", 0);
                    response.put("msg", "账号已存在");
                } else {
                    adminService.insert(new Admin(0, u_email, Md5Util.md5(u_password), "admin"));
                    response.put("flag", 1);
                    response.put("msg", "添加成功");
                }
            } else if ("1".equals(u_type)) {// 增加用户
                type = "user";
                User user = new User(0, u_email, u_name, Md5Util.md5(u_password), type, new Date());
                userService.insert(user);
                diaryService.insert(new Diary(0, adminName, "添加用户" + u_email, new Date(), 1));
                response.put("flag", 1);
                response.put("msg", "添加成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "错误的u_type");
            }
>>>>>>> dev
        } else {
            response.put("flag", 0);
            response.put("msg", "邮箱号已存在");
        }
<<<<<<< HEAD

=======
>>>>>>> dev
        return response;
    }

    /**
     * 编辑用户
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
<<<<<<< HEAD
    @GetMapping("edit")
    public JSONObject editUser(String u_name, String u_email, String u_type) {
        JSONObject response = new JSONObject();
        if (userService.queryByEmail(u_email) == null) {
            User user = new User(0, u_email, u_name, "密码", u_type, new Date());
            userService.insert(user);
            response.put("flag", 1);
            response.put("msg", "添加成功");
        } else {
            response.put("flag", 0);
            response.put("msg", "用户已存在");
        }

=======
    @PostMapping("edit")
    public JSONObject editUser(HttpSession session, String u_id, String u_name, String u_email) {
        JSONObject response = new JSONObject();
        int id;
        String adminName = (String) session.getAttribute("adminName");
        try {
            id = Integer.parseInt(u_id);
            User user = userService.queryById(id);
            if (user != null) {
                diaryService.insert(new Diary(0, adminName, "修改用户" + user.getU_email(), new Date(), 1));
                user.setU_name(u_name);
                user.setU_email(u_email);
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
            diaryService.insert(new Diary(0, adminName, "修改用户", new Date(), 0));
            System.out.println("修改时给定id不正确");
        }
>>>>>>> dev
        return response;
    }

    /**
     * 删除用户
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("delete")
<<<<<<< HEAD
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
=======
    public JSONObject deleteUser(HttpSession session, String u_id) {
        JSONObject response = new JSONObject();
        try {
            Integer id = Integer.parseInt(u_id);
            User user = userService.queryById(id);
            String adminName = (String) session.getAttribute("adminName");
            if (user != null) {
                userService.deleteById(user.getU_id());
                diaryService.insert(new Diary(0, adminName, "删除用户" + user.getU_email(), new Date(), 1));
                response.put("flag", 1);
                response.put("msg", "删除成功");
            } else {
                response.put("flag", 0);
                response.put("msg", "用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("flag", 0);
            response.put("msg", "程序遇到异常");
>>>>>>> dev
        }
        return response;
    }

    /**
<<<<<<< HEAD
     * 查看日志  有点问题
=======
     * 查看日志
>>>>>>> dev
     *
     * @author maple
     * @Date 2020/5/20 22:32
     */
    @GetMapping("diary")
    public JSONObject getDiary(String u_name, String u_email, String u_type) {
        JSONObject response = new JSONObject();
        List<Diary> diaries = diaryService.queryAll();
        List<Diary.DiaryToWeb> resDiaries = new ArrayList<>();
<<<<<<< HEAD
        for(Diary diary : diaries) {
            resDiaries.add(diary.toDiaryToWeb(diary));
            System.out.println(diary.toDiaryToWeb(diary));
=======
        for (Diary diary : diaries) {
            resDiaries.add(diary.toDiaryToWeb(diary));
>>>>>>> dev
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