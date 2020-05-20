package com.skpl.mailapp.service;

import com.skpl.mailapp.entity.Admin;
import java.util.List;

/**
 * 系统管理员  服务端管理员(Admin)表服务接口
 *
 * @author makejava
 * @since 2020-05-20 22:08:34
 */
public interface AdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param aNo 主键
     * @return 实例对象
     */
    Admin queryById(Integer aNo);

    /**
     * 通过userName查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    Admin queryByName(String userName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(int offset, int limit);
    
    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Admin> queryAll();

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param aNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer aNo);

}