package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.Friendship;
import com.skpl.mailapp.dao.FriendshipDao;
import com.skpl.mailapp.service.FriendshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 记录好友关系(Friendship)表服务实现类
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@Service("friendshipService")
public class FriendshipServiceImpl implements FriendshipService {
    @Resource
    private FriendshipDao friendshipDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fNo 主键
     * @return 实例对象
     */
    @Override
    public Friendship queryById(Integer fNo) {
        return this.friendshipDao.queryById(fNo);
    }
    
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Friendship> queryAllByLimit(int offset, int limit) {
        return this.friendshipDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Friendship> queryAll() {
        return this.friendshipDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param friendship 实例对象
     * @return 实例对象
     */
    @Override
    public Friendship insert(Friendship friendship) {
        this.friendshipDao.insert(friendship);
        return friendship;
    }

    /**
     * 修改数据
     *
     * @param friendship 实例对象
     * @return 实例对象
     */
    @Override
    public Friendship update(Friendship friendship) {
        this.friendshipDao.update(friendship);
        return this.queryById(friendship.getFNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param fNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer fNo) {
        return this.friendshipDao.deleteById(fNo) > 0;
    }
}