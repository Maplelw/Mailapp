package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.dao.UserDao;
import com.skpl.mailapp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 记录用户的信息(User)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 17:52:59
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer uId) {
        return this.userDao.queryById(uId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param uEmail 邮箱号
     * @return 实例对象
     */
    @Override
    public User queryByEmail(String uEmail) {
        return this.userDao.queryByEmail(uEmail);
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryAll() {
        return this.userDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getU_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uId) {
        return this.userDao.deleteById(uId) > 0;
    }
}