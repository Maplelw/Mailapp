package com.skpl.mailapp.dao;

import com.skpl.mailapp.entity.Friendship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 记录好友关系(Friendship)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@Mapper
@Repository
public interface FriendshipDao {

    /**
     * 通过ID查询单条数据
     *
     * @param fNo 主键
     * @return 实例对象
     */
    Friendship queryById(Integer fNo);
   
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Friendship> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 返回所有数据
     *
     * @return 对象列表
     */
    List<Friendship> queryAll();
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param friendship 实例对象
     * @return 对象列表
     */
    List<Friendship> queryAll(Friendship friendship);

    /**
     * 新增数据
     *
     * @param friendship 实例对象
     * @return 影响行数
     */
    int insert(Friendship friendship);

    /**
     * 修改数据
     *
     * @param friendship 实例对象
     * @return 影响行数
     */
    int update(Friendship friendship);

    /**
     * 通过主键删除数据
     *
     * @param fNo 主键
     * @return 影响行数
     */
    int deleteById(Integer fNo);

}