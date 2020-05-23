package com.skpl.mailapp.controller;

import com.skpl.mailapp.entity.Filter;
import com.skpl.mailapp.service.FilterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Filter)表控制层
 *
 * @author makejava
 * @since 2020-05-22 19:13:14
 */
@RestController
@RequestMapping("filter")
public class FilterController {
    /**
     * 服务对象
     */
    @Resource
    private FilterService filterService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Filter selectOne(Integer id) {
        return this.filterService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @GetMapping("selectAllByLimit")
    public List<Filter> queryAll() {
        return this.filterService.queryAll();
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public List<Filter> queryAllByLimit(int offset, int limit) {
        return this.filterService.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param filter 实例对象
     * @return 实例对象
     */
    @GetMapping("insert")
    public Filter insert(Filter filter) {
        return this.filterService.insert(filter);
    }

    /**
     * 修改数据
     *
     * @param filter 实例对象
     * @return 实例对象
     */
    @GetMapping("update")
    public Filter update(Filter filter) {
        return this.filterService.update(filter);
    }

    /**
     * 通过主键删除数据
     *
     * @param fId 主键
     * @return 是否成功
     */
    @GetMapping("deleteById")
    public boolean deleteById(Integer fId) {
        return this.filterService.deleteById(fId);
    }
    
}