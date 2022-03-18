package org.qf.dao;

import org.qf.pojo.SmbmsUser;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户接口
 */
public interface SmbmsUserDao {
    /**
     * 按照用户编号进行查询
     * @param userCode  用户编号
     * @return  用户
     * @throws Exception
     */
    SmbmsUser queryByuserCode(String userCode) throws Exception;

    /**
     * 按照用户名进行模糊查询并支持按照角色查询，还支持分页功能
     * @param userName  用户名
     * @param userRole  用户角色
     * @param currentPageNo 当前页面
     * @param pageSize  页容量
     * @return  用户集合
     * @throws Exception
     */
    List<SmbmsUser> queryByuserName(String userName,int userRole,int currentPageNo,int pageSize)throws Exception;

    /**
     * 返回按照用户名进行模糊查询并支持按照角色查询的总记录数
     * @param userName  用户名
     * @param userRole  用户角色
     * @return 查询结果的总记录数
     * @throws Exception
     */
    int getSmbmsUserCount(String userName,int userRole)throws Exception;

    /**
     * 通过ID查询用户信息
     * @param id 用户ID
     * @return 用户
     * @throws Exception
     */
    SmbmsUser queryUserById(Long id)throws Exception;

    /**
     * 更新用户
     * @param smbmsUser 要更新的用户
     * @return 影响的行数
     * @throws Exception
     */
    int updateUser(SmbmsUser smbmsUser)throws Exception;

    /**
     * 增加用户信息
     * @param smbmsUser 用户对象
     * @return  影响的行数
     * @throws Exception
     */
    int addUser(SmbmsUser smbmsUser)throws Exception;

    /**
     * 通过id删除用户
     * @param id 用户id
     * @return 影响的行数
     * @throws Exception
     */
    int deleteUserById(Long id)throws Exception;

    /**
     * 通过用户id修改用户密码
     * @param id    用户id
     * @param userPassword 新的密码
     * @return  影响的行数
     * @throws Exception
     */
    int updateUserPassword(Long id,String userPassword)throws Exception;
}
