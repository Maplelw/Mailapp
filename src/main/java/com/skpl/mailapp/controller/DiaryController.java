package com.skpl.mailapp.controller;

import com.skpl.mailapp.entity.Diary;
import com.skpl.mailapp.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Diary)表控制层
 *
 * @author makejava
 * @since 2020-05-20 21:52:46
 */
@RestController
@RequestMapping("diary")
public class DiaryController {
    /**
     * 服务对象
     */
    @Resource
    private DiaryService diaryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Diary selectOne(Integer id) {
        return this.diaryService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @GetMapping("selectAllByLimit")
    public List<Diary> queryAll() {
        return this.diaryService.queryAll();
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public List<Diary> queryAllByLimit(int offset, int limit) {
        return this.diaryService.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    @GetMapping("insert")
    public Diary insert(Diary diary) {
        return this.diaryService.insert(diary);
    }

    /**
     * 修改数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    @GetMapping("update")
    public Diary update(Diary diary) {
        return this.diaryService.update(diary);
    }

    /**
     * 通过主键删除数据
     *
     * @param dId 主键
     * @return 是否成功
     */
    @GetMapping("deleteById")
    public boolean deleteById(Integer dId) {
        return this.diaryService.deleteById(dId);
    }
    
}