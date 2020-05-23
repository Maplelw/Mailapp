package com.skpl.mailapp.service;

import com.skpl.mailapp.entity.Filter;
import java.util.List;

/**
 * (Filter)表服务接口
 *
 * @author makejava
 * @since 2020-05-22 19:13:14
 */
public interface FilterService {

    /**
     * 通过ID查询单条数据
     *
     * @param fId 主键
     * @return 实例对象
     */
    Filter queryById(Integer fId);

    /**
     * 通过userMail查询
     *
     * @param f_mail
     * @return 实例对象
     */
    List<Filter> queryByMail(String f_mail);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Filter> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Filter> queryAllByLimit(int offset, int limit,Filter filter);
    
    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Filter> queryAll();

    /**
     * 新增数据
     *
     * @param filter 实例对象
     * @return 实例对象
     */
    Filter insert(Filter filter);

    /**
     * 修改数据
     *
     * @param filter 实例对象
     * @return 实例对象
     */
    Filter update(Filter filter);

    /**
     * 通过主键删除数据
     *
     * @param fId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer fId);

}