package com.skpl.mailapp.dao;

import com.skpl.mailapp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 记录用户的信息(User)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-07 17:52:59
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    User queryById(Integer uId);

    /**
     * 通过ID查询单条数据
     *
     * @param uEmail 邮箱号
     * @return 实例对象
     */
    User queryByEmail(String uEmail);

    /**
     * 模糊搜索，根据邮箱号或者用户名
     *
     * @param u_name 邮箱号
     * @return 实例对象
     */
    List<User> queryALLBySearch(String u_name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 返回所有数据
     *
     * @return 对象列表
     */
    List<User> queryAll();
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 影响行数
     */
    int deleteById(Integer uId);

}