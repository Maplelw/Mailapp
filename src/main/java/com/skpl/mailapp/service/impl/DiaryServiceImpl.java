package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.Diary;
import com.skpl.mailapp.dao.DiaryDao;
import com.skpl.mailapp.service.DiaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Diary)表服务实现类
 *
 * @author makejava
 * @since 2020-05-20 21:52:46
 */
@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {
    @Resource
    private DiaryDao diaryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param dId 主键
     * @return 实例对象
     */
    @Override
    public Diary queryById(Integer dId) {
        return this.diaryDao.queryById(dId);
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Diary> queryAllByLimit(int offset, int limit) {
        return this.diaryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Diary> queryAll() {
        return this.diaryDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    @Override
    public Diary insert(Diary diary) {
        this.diaryDao.insert(diary);
        return diary;
    }

    /**
     * 修改数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    @Override
    public Diary update(Diary diary) {
        this.diaryDao.update(diary);
        return this.queryById(diary.getD_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param dId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer dId) {
        return this.diaryDao.deleteById(dId) > 0;
    }
}