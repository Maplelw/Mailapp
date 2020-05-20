package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skpl.mailapp.entity.Friendship;
import com.skpl.mailapp.service.FriendshipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 记录好友关系(Friendship)表控制层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@RestController
@RequestMapping("friendship")
public class FriendshipController {
    /**
     * 服务对象
     */
    @Resource
    private FriendshipService friendshipService;

    /*
     * 返回状态  success  error
     */
    private JSONObject status;

    /*
     * 错误原因
     */
    private JSONObject error;

    /*
     * 返回数据
     */
    private JSONArray data;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Friendship selectOne(Integer id) {
        return this.friendshipService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @GetMapping("selectAllByLimit")
    public List<Friendship> queryAllByLimit() {
        return this.friendshipService.queryAll();
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public List<Friendship> queryAllByLimit(int offset, int limit) {
        return this.friendshipService.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param friendship 实例对象
     * @return 实例对象
     */
    @GetMapping("insert")
    public Friendship insert(Friendship friendship) {
        return this.friendshipService.insert(friendship);
    }

    /**
     * 修改数据
     *
     * @param friendship 实例对象
     * @return 实例对象
     */
    @GetMapping("update")
    public Friendship update(Friendship friendship) {
        return this.friendshipService.update(friendship);
    }

    /**
     * 通过主键删除数据
     *
     * @param fNo 主键
     * @return 是否成功
     */
    @GetMapping("deleteById")
    public boolean deleteById(Integer fNo) {
        return this.friendshipService.deleteById(fNo);
    }
    
}