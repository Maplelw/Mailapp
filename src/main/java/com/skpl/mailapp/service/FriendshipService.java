package com.skpl.mailapp.service;

import com.skpl.mailapp.entity.Friendship;
import java.util.List;

/**
 * 记录好友关系(Friendship)表服务接口
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
public interface FriendshipService {

    /**
     * 通过ID查询单条数据
     *
     * @param fNo 主键
     * @return 实例对象
     */
    Friendship queryById(Integer fNo);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Friendship> queryAllByLimit(int offset, int limit);
    
    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Friendship> queryAll();

    /**
     * 新增数据
     *
     * @param friendship 实例对象
     * @return 实例对象
     */
    Friendship insert(Friendship friendship);

    /**
     * 修改数据
     *
     * @param friendship 实例对象
     * @return 实例对象
     */
    Friendship update(Friendship friendship);

    /**
     * 通过主键删除数据
     *
     * @param fNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer fNo);

}