package com.skpl.mailapp.dao;

import com.skpl.mailapp.entity.Mail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (Mail)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@Mapper
@Repository
public interface MailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param mId 主键
     * @return 实例对象
     */
    Mail queryById(Integer mId);
   
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Mail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 返回所有数据
     *
     * @return 对象列表
     */
    List<Mail> queryAll();
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param mail 实例对象
     * @return 对象列表
     */
    List<Mail> queryAll(Mail mail);

    /**
     * 新增数据
     *
     * @param mail 实例对象
     * @return 影响行数
     */
    int insert(Mail mail);

    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 影响行数
     */
    int update(Mail mail);

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 影响行数
     */
    int deleteById(Integer mId);

    /**
     * 根据mailID删除邮件
     *
     * @Param mailId 邮箱号
     * @return
     **/
    int deleteByMailId(String mailId);

}