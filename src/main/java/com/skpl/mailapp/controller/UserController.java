package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSON;
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
import javax.sound.midi.Soundbank;
import com.skpl.mailapp.entity.Filter;
import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.service.FilterService;
import com.skpl.mailapp.service.MailService;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.Md5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Resource
    private FilterService filterService;

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
        List<User.UserToApp> resList = new ArrayList<>();
        for (User user : list) {
            user.setU_password(null);
            resList.add(user.toUserToApp(user));
        }
        data.put("list", resList);
        res.put("data", data);
        return res;
    }

    /**
     * 登录
     *
     * @return JSON对象
     * @Date 2020/5/9 18:38
     * @Param [userName, userPassword]
     **/
    @PostMapping("/login")
    public JSONObject login(@RequestBody User user) {
        init();
        User correctUser;
        if ((correctUser = userService.queryByEmail(user.getU_email())) != null) {
            if (correctUser.getU_password().equals(Md5Util.md5(user.getU_password()))) {
                data.put("userName", correctUser.getU_name());
                data.put("userMail", correctUser.getU_email());
                data.put("userId", correctUser.getU_id());
                data.put("jwt", new String(Base64.getEncoder().encode(user.getU_email().getBytes())));
                res.put("status", "success");
                res.put("data", data);
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
     * 用户注册
     * Done
     *
     * @Date 2020/4/25 14:14
     * @Param [userName, userMail, userPassword]
     **/
    @PostMapping("user/add")
<<<<<<< HEAD
    public JSONObject insert(@RequestBody Map map ) {
        init();
        String userName =(String) map.get("userName");
        String userMail =(String) map.get("userMail");
        String userPassword =(String) map.get("userPassword");
=======
    public JSONObject insert(@RequestBody Map map) {
        init();
        String userName = (String) map.get("userName");
        String userMail = (String) map.get("userMail");
        String userPassword = (String) map.get("userPassword");
>>>>>>> dev
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
     * @param map 注销的邮箱号
     * @return 是否成功
     */
    @DeleteMapping("user")
    public JSONObject deleteById(@RequestBody Map map) {
<<<<<<< HEAD
        Integer userId = Integer.parseInt((String)map.get("userId"));
=======
        Integer userId = Integer.parseInt((String) map.get("userId"));
>>>>>>> dev
        System.out.println(userId);
        init();
        if (userService.queryById(userId) != null) {
            User user = new User();
            user.setU_id(userId);
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
     * 修改密码
     *
     * @return
     * @author maple
     * @Date 2020/5/9 23:27
     * @Param [session, oldPassword, newPassword]
     */
    @PutMapping("user")
    public JSONObject changePassword(HttpServletRequest request, @RequestBody Map map) {
        init();
        String newPassword = (String) map.get("UserPassword");
        String oldPassword = (String) map.get("UserOldPassword");
        String userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
<<<<<<< HEAD
        User user =  userService.queryByEmail(userMail);
        if(user == null) {
=======
        User user = userService.queryByEmail(userMail);
        if (user == null) {
>>>>>>> dev
            res.put("status", "error");
            res.put("error", "用户不存在");
        } else {
            if (Md5Util.md5(oldPassword).equals(user.getU_password())) {
                user.setU_password(Md5Util.md5(newPassword));
                userService.update(user);
                res.put("status", "success");
            } else {
                res.put("status", "error");
                res.put("error", "原密码错误");
            }
        }
        return res;
    }

<<<<<<< HEAD

=======
>>>>>>> dev
    /**
     * 查看邮件过滤列表
     *
     * @return 是否成功
     */
<<<<<<< HEAD
    @GetMapping("fliter")
    public JSONObject getFilter() {
        init();
        JSONObject list = new JSONObject();
        JSONObject data = new JSONObject();
        list.put("filterMail","qaq@skpl.com");
        list.put("fliterId","001");
        list.put("fliterIp","125.246.223.332");
        data.put("list",list);
        res.put("status","success");
        res.put("data",data);
=======
    @GetMapping("filter")
    public JSONObject getFilter(HttpServletRequest request) {
        init();
        String userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
        List<Filter> filters = filterService.queryByMail(userMail);
        List<Filter.FilterToApp> filterToApps = new ArrayList<>();
        for (Filter filter : filters) {
            filterToApps.add(filter.toFilterToApp(filter));
        }
        data.put("list", filterToApps);
        res.put("status", "success");
        res.put("data", data);
>>>>>>> dev
        return res;
    }

    /**
     * 添加过滤
     *
     * @return 是否成功
     */
<<<<<<< HEAD
    @PostMapping("fliter")
    public JSONObject addFilter() {
        init();
        JSONObject filterId = new JSONObject();
        JSONObject data = new JSONObject();
        filterId.put("fliterID","001");
        data.put("filterId",filterId);
        res.put("status","success");
        res.put("data",data);
=======
    @PostMapping("filter")
    public JSONObject addFilter(HttpServletRequest request, @RequestBody Map map) {
        init();
        String filterIp = (String) map.get("filterIp");
        String filterMail = (String) map.get("filterMail");
        String userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
        User user = userService.queryByEmail(userMail);
        if (userService.queryByEmail(filterMail) == null) {
            res.put("status", "error");
            res.put("error", "过滤邮箱不存在");
        } else {
            List<Filter> filters = filterService.queryByMail(userMail);
            HashSet<Object> set = new HashSet<>();
            for (Filter filter : filters) {
                set.add(filter.getFRefuseMail());
            }
            if (set.contains(filterMail)) {
                res.put("status", "error");
                res.put("error", "已经过滤该邮箱");
            } else {
                filterService.insert(new Filter(0, userMail, filterMail, filterIp));
                res.put("status", "success");
                res.put("data", data);
                Filter filter = new Filter(null, userMail, filterMail, null);
                List<Filter> resFilters = filterService.queryAllByLimit(0, 1, filter);
                data.put("filterId", resFilters.get(0).getFId());
            }

        }
>>>>>>> dev
        return res;
    }

    /**
     * 删除一个过滤
     *
     * @return 是否成功
     */
<<<<<<< HEAD
    @DeleteMapping("fliter")
    public JSONObject deleteFilter(@RequestBody Map map) {
        init();
        //Integer filterId = Integer.parseInt((String) map.get("fliterId"));
        System.out.println(map.get("fliterId"));
        res.put("status","success");
=======
    @DeleteMapping("filter")
    public JSONObject deleteFilter(HttpServletRequest request, @RequestBody Map map) {
        init();
        try {
            Integer filterId = Integer.parseInt((String) map.get("filterId"));
            System.out.println(filterId);
            String userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
            Filter filter = filterService.queryById(filterId);
            if (filter != null) {
                if (filter.getFMail().equals(userMail)) {
                    filterService.deleteById(filterId);
                    res.put("status", "success");
                } else {
                    res.put("status", "error");
                    res.put("error", "无法删除不属于您的过滤id");
                }
            } else {
                res.put("status", "error");
                res.put("error", "过滤Id不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "error");
            res.put("error", "过滤Id错误");
        }
>>>>>>> dev
        return res;
    }

}