package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.Filter;
import com.skpl.mailapp.dao.FilterDao;
import com.skpl.mailapp.service.FilterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Filter)表服务实现类
 *
 * @author makejava
 * @since 2020-05-22 19:13:14
 */
@Service("filterService")
public class FilterServiceImpl implements FilterService {
    @Resource
    private FilterDao filterDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fId 主键
     * @return 实例对象
     */
    @Override
    public Filter queryById(Integer fId) {
        return this.filterDao.queryById(fId);
    }

    /**
     * 通过userMail查询
     *
     * @param fMail
     * @return 实例对象
     */
    @Override
    public List<Filter> queryByMail(String fMail) {
        return this.filterDao.queryByMail(fMail);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Filter> queryAllByLimit(int offset, int limit) {
        return this.filterDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Filter> queryAllByLimit(int offset, int limit, Filter filter) {
        return this.filterDao.queryAllByLimit(offset, limit, filter);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Filter> queryAll() {
        return this.filterDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param filter 实例对象
     * @return 实例对象
     */
    @Override
    public Filter insert(Filter filter) {
        this.filterDao.insert(filter);
        return filter;
    }

    /**
     * 修改数据
     *
     * @param filter 实例对象
     * @return 实例对象
     */
    @Override
    public Filter update(Filter filter) {
        this.filterDao.update(filter);
        return this.queryById(filter.getFId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer fId) {
        return this.filterDao.deleteById(fId) > 0;
    }
}