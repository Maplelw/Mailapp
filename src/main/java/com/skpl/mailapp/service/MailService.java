package com.skpl.mailapp.service;

import com.skpl.mailapp.entity.Mail;
import java.util.List;

/**
 * (Mail)表服务接口
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
public interface MailService {

    /**
     * 通过ID查询单条数据
     *
     * @param mId 主键
     * @return 实例对象
     */
    Mail queryById(Integer mId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Mail> queryAllByLimit(int offset, int limit);
    
    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Mail> queryAll();

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Mail> queryAll(Mail mail);

    /**
     * 获取用户所有邮件，包括收件和发件，除去过滤器的
     *
     * @param userMail 实例对象
     * @return 对象列表
     */
    List<Mail> queryAllExFilter(String userMail);

    /**
     * 新增数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    Mail insert(Mail mail);

    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    Mail update(Mail mail);

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer mId);

}