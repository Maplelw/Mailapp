package com.skpl.mailapp.dao;

import com.skpl.mailapp.entity.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (Diary)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-20 21:52:46
 */
@Mapper
@Repository
public interface DiaryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param dId 主键
     * @return 实例对象
     */
    Diary queryById(Integer dId);
   
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Diary> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 返回所有数据
     *
     * @return 对象列表
     */
    List<Diary> queryAll();
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param diary 实例对象
     * @return 对象列表
     */
    List<Diary> queryAll(Diary diary);

    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 影响行数
     */
    int insert(Diary diary);

    /**
     * 修改数据
     *
     * @param diary 实例对象
     * @return 影响行数
     */
    int update(Diary diary);

    /**
     * 通过主键删除数据
     *
     * @param dId 主键
     * @return 影响行数
     */
    int deleteById(Integer dId);

}