package com.skpl.mailapp.dao;

import com.skpl.mailapp.entity.Filter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (Filter)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-22 19:13:14
 */
@Mapper
@Repository
public interface FilterDao {

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
     * @param fMail 主键
     * @return 实例对象
     */
    List<Filter> queryByMail(String fMail);
   
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Filter> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Filter> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit,Filter filter);

    /**
     * 返回所有数据
     *
     * @return 对象列表
     */
    List<Filter> queryAll();
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param filter 实例对象
     * @return 对象列表
     */
    List<Filter> queryAll(Filter filter);

    /**
     * 新增数据
     *
     * @param filter 实例对象
     * @return 影响行数
     */
    int insert(Filter filter);

    /**
     * 修改数据
     *
     * @param filter 实例对象
     * @return 影响行数
     */
    int update(Filter filter);

    /**
     * 通过主键删除数据
     *
     * @param fId 主键
     * @return 影响行数
     */
    int deleteById(Integer fId);

}