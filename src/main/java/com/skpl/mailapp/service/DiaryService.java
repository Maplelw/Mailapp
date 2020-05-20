package com.skpl.mailapp.service;

import com.skpl.mailapp.entity.Diary;
import java.util.List;

/**
 * (Diary)表服务接口
 *
 * @author makejava
 * @since 2020-05-20 21:52:46
 */
public interface DiaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param dId 主键
     * @return 实例对象
     */
    Diary queryById(Integer dId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Diary> queryAllByLimit(int offset, int limit);
    
    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Diary> queryAll();

    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    Diary insert(Diary diary);

    /**
     * 修改数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    Diary update(Diary diary);

    /**
     * 通过主键删除数据
     *
     * @param dId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer dId);

}