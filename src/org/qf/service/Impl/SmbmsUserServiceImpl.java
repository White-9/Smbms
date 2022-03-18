package org.qf.service.Impl;

import org.qf.dao.Impl.SmbmsUserDaoImpl;
import org.qf.dao.SmbmsUserDao;
import org.qf.pojo.SmbmsUser;
import org.qf.service.SmbmsUserService;

import java.sql.SQLException;
import java.util.List;

public class SmbmsUserServiceImpl implements SmbmsUserService {

    //注入dao
    private final SmbmsUserDao smbmsUserDao = new SmbmsUserDaoImpl();
    //登录功能
    @Override
    public SmbmsUser login(String userCode,String userPassword) throws Exception {
        //调用数据访问层
        SmbmsUser smbmsUser = smbmsUserDao.queryByuserCode(userCode);
        if(null!=smbmsUser){
            if (!userPassword.equals(smbmsUser.getUserPassword())){
                smbmsUser = null;
            }
        }
        return smbmsUser;
    }

    @Override
    public List<SmbmsUser> queryByuserName(String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        return smbmsUserDao.queryByuserName(userName,userRole,currentPageNo,pageSize);
    }

    @Override
    public int getSmbmsUserCount(String userName, int userRole) throws Exception {
        return smbmsUserDao.getSmbmsUserCount(userName,userRole);
    }

    @Override
    public SmbmsUser queryUserById(Long id) throws Exception {
        return smbmsUserDao.queryUserById(id);
    }

    @Override
    public int updateUser(SmbmsUser smbmsUser) throws Exception {
        return smbmsUserDao.updateUser(smbmsUser);
    }

    @Override
    public int addUser(SmbmsUser smbmsUser) throws Exception {
        return smbmsUserDao.addUser(smbmsUser);
    }

    @Override
    public SmbmsUser queryByuserCode(String userCode) throws Exception {
        return smbmsUserDao.queryByuserCode(userCode);
    }

    @Override
    public int deleteUserById(Long id) throws Exception {
        return smbmsUserDao.deleteUserById(id);
    }

    @Override
    public int updateUserPassword(Long id, String userPassword) throws Exception {
        return smbmsUserDao.updateUserPassword(id,userPassword);
    }


}
