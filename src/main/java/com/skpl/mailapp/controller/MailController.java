package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.service.MailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Mail)表控制层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@RestController
@RequestMapping("mail")
public class MailController {
    /**
     * 服务对象
     */
    @Resource
    private MailService mailService;

    /*
     * 返回对象
     */
    private JSONObject res;

    @GetMapping("test1")
    public JSONObject test1() {
       return null;
    }

    @GetMapping("test2")
    public JSONArray test2() {
        return null;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @GetMapping("user")
    public List<Mail> queryAllByLimit(int offset, int limit) {
        return this.mailService.queryAllByLimit(offset, limit);
    }

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 是否成功
     */
    @DeleteMapping("user")
    public boolean deleteById(Integer mId) {
        return this.mailService.deleteById(mId);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Mail selectOne(Integer id) {
        return this.mailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @GetMapping("selectAllByLimit")
    public List<Mail> queryAll() {
        return this.mailService.queryAll();
    }


    /**
     * 新增数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    @GetMapping("insert")
    public Mail insert(Mail mail) {
        return this.mailService.insert(mail);
    }

    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    @GetMapping("update")
    public Mail update(Mail mail) {
        return this.mailService.update(mail);
    }

}