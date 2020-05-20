package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.Admin;
import com.skpl.mailapp.dao.AdminDao;
import com.skpl.mailapp.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理员  服务端管理员(Admin)表服务实现类
 *
 * @author makejava
 * @since 2020-05-20 22:08:34
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param aNo 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer aNo) {
        return this.adminDao.queryById(aNo);
    }

    /**
     * 通过userName查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    @Override
    public Admin queryByName(String userName) {
        return this.adminDao.queryByName(userName);
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAllByLimit(int offset, int limit) {
        return this.adminDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAll() {
        return this.adminDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getA_no());
    }

    /**
     * 通过主键删除数据
     *
     * @param aNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer aNo) {
        return this.adminDao.deleteById(aNo) > 0;
    }
}