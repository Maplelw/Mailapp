package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.dao.MailDao;
import com.skpl.mailapp.service.MailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Mail)表服务实现类
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Resource
    private MailDao mailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param mId 主键
     * @return 实例对象
     */
    @Override
    public Mail queryById(Integer mId) {
        return this.mailDao.queryById(mId);
    }
<<<<<<< HEAD
    
=======

>>>>>>> dev
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
<<<<<<< HEAD
     * @param limit 查询条数
=======
     * @param limit  查询条数
>>>>>>> dev
     * @return 对象列表
     */
    @Override
    public List<Mail> queryAllByLimit(int offset, int limit) {
        return this.mailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Mail> queryAll() {
        return this.mailDao.queryAll();
    }

    /**
<<<<<<< HEAD
=======
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Mail> queryAll(Mail mail) {
        return this.mailDao.queryAll(mail);
    }

    /**
     * 获取用户所有邮件，包括收件和发件，除去过滤器的
     *
     * @param userMail 实例对象
     * @return 对象列表
     */
    @Override
    public List<Mail> queryAllExFilter(String userMail) {
        return this.mailDao.queryAllExFilter(userMail);
    }

    /**
>>>>>>> dev
     * 新增数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    @Override
    public Mail insert(Mail mail) {
        this.mailDao.insert(mail);
        return mail;
    }

    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    @Override
    public Mail update(Mail mail) {
        this.mailDao.update(mail);
        return this.queryById(mail.getM_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer mId) {
        return this.mailDao.deleteById(mId) > 0;
    }
}