package com.skpl.mailapp.service;

import com.skpl.mailapp.entity.User;
import java.util.List;

/**
 * 记录用户的信息(User)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 17:52:59
 */
public interface UserService {

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
     * @param uEmail 主键
     * @return 实例对象
     */
    User queryByEmail(String uEmail);

    /**
<<<<<<< HEAD
=======
     * 模糊搜索，根据邮箱号或者用户名
     *
     * @param u_name 邮箱号
     * @return 实例对象
     */
    List<User> queryALLBySearch(String u_name);

    /**
>>>>>>> dev
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);
    
    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<User> queryAll();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uId);

}