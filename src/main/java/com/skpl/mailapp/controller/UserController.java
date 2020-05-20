package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.service.MailService;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.Md5Util;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 记录用户的信息(User)表控制层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

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
     * 获取用户列表
     *
     * @return 对象列表
     */
    @GetMapping("user/list")
    public JSONObject queryAllBy() {
        init();
        res.put("status", "success");
        List<User> list = this.userService.queryAll();
        for (User item : list) {
            item.setU_password(null);
        }
        data.put("list", list);
        res.put("data", data);
        return res;
    }

    /**
     * 用户注册
     * Done
     *
     * @Date 2020/4/25 14:14
     * @Param [userName, userMail, userPassword]
     **/
    @GetMapping("user/add")
    public JSONObject insert(String userName, String userMail, String userPassword) {
        init();
        User user = new User(0, userMail, userName, Md5Util.md5(userPassword), "user", new Date());
        if (userService.queryByEmail(userMail) != null) {
            res.put("status", "error");
            res.put("error", "邮箱已存在");
        } else {
            userService.insert(user);
            res.put("status", "success");
            user = userService.queryByEmail(userMail);
            data.put("userId", user.getU_id());
            res.put("data", data);
        }
        return res;
    }

    /**
     * 用户注销
     *
     * @param uId 注销的邮箱号
     * @return 是否成功
     */
    @DeleteMapping("user")
    public JSONObject deleteById(@RequestParam("userId") Integer uId) {
        System.out.println(uId);
        init();
        if (userService.queryById(uId) != null) {
            User user = new User();
            user.setU_id(uId);
            user.setU_type("logoff");
            userService.update(user);
            res.put("status", "success");
        } else {
            res.put("status", "error");
            res.put("error", "用户不存在");
        }
        return res;
    }

    /**
     * 登录
     *
     * @return JSON对象
     * @Date 2020/5/9 18:38
     * @Param [userName, userPassword]
     **/
    @PatchMapping("/login")
    public JSONObject login(HttpSession session, String userName, String userPassword) {
        init();
        User user;
        if ((user = userService.queryByEmail(userName)) != null) {
            if (user.getU_password().equals(Md5Util.md5(userPassword))) {
                data.put("userName", user.getU_name());
                data.put("userMail", user.getU_email());
                data.put("userId", user.getU_id());
                res.put("status", "success");
                res.put("data", data);
                // 登录状态保持
                session.setAttribute("user", user);
            } else {
                res.put("status", "error");
                res.put("error", "用户名密码不匹配");
            }
        } else {
            res.put("status", "error");
            res.put("error", "用户不存在");
        }
        return res;
    }

    /**
     * 修改密码
     *
     * @author maple
     * @Date 2020/5/9 23:27
     * @Param [session, oldPassword, newPassword]
     * @return
     */
    @PutMapping("user")
    public JSONObject changePassword(HttpSession session, @RequestParam("userOldPassword") String oldPassword, @RequestParam(("userPassword")) String newPassword) {
        init();
        User user = (User) session.getAttribute("user");
        if(Md5Util.md5(oldPassword).equals(user.getU_password())) {
            user.setU_password(Md5Util.md5(newPassword));
            userService.update(user);
            res.put("status","success");
        } else {
            res.put("status", "error");
            res.put("error", "原密码错误");
        }
        return res;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userService.queryAllByLimit(offset, limit);
    }

    /**
     * 修改用户信息
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @GetMapping("update")
    public User update(User user) {
        return this.userService.update(user);
    }

    /**
     * 用户修改密码
     *
     * @param oldPassword 旧密码 newPassword 新密码
     * @return 是否成功
     */
    @PutMapping()
    public JSONObject changePassword(@RequestParam("UserOldPassword") String oldPassword, @RequestParam("UserPassword") String newPassword) {
        init();
        User user = userService.queryById(123);
        if (user.getU_password().equals(oldPassword)) {
            user.setU_password(newPassword);
            userService.update(user);
            res.put("status", "success");
        } else {
            res.put("status", "error");
            res.put("error", "验证密码错误");
        }
        return res;
    }

}