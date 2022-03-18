package org.qf.dao.Impl;

import com.mysql.jdbc.StringUtils;
import org.qf.dao.SmbmsUserDao;
import org.qf.pojo.SmbmsUser;
import org.qf.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SmbmsUserDaoImpl  extends BaseDao implements SmbmsUserDao {

    @Override
    public SmbmsUser queryByuserCode(String userCode) throws SQLException {
        SmbmsUser smbmsUser = new SmbmsUser();
        String sql = "select * from smbms_user where userCode=?";
        Object[] params = {userCode};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            smbmsUser.setId(resultSet.getLong("id"));
            smbmsUser.setUserCode(resultSet.getString("userCode"));
            smbmsUser.setUserName(resultSet.getString("userName"));
            smbmsUser.setUserPassword(resultSet.getString("userPassword"));

        }
        return smbmsUser;
    }



    @Override
    public List<SmbmsUser> queryByuserName(String userName,int userRole,int currentPageNo,int pageSize) throws Exception {
        List<SmbmsUser> list = new ArrayList<SmbmsUser>();
        List<Object> obj = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select u.*,r.roleName as userRoleName from smbms_user u inner join smbms_role r on u.userRole=r.id ");
        //判断
        if(!StringUtils.isNullOrEmpty(userName)){
            sql.append("and u.userName like ? ");
            obj.add("%"+userName+"%");
        }
        if(userRole>0){
            sql.append("and u.userRole=? ");
            obj.add(userRole);
        }
        //分页
        sql.append("order by u.creationDate desc limit ?,? ");
        obj.add((currentPageNo-1)*pageSize);
        obj.add(pageSize);
        Object[] params = obj.toArray();
        ResultSet resultSet = this.executeQuery(sql.toString(),params);
        while (resultSet.next()){
            SmbmsUser smbmsUser = new SmbmsUser();
            smbmsUser.setId(resultSet.getLong("id"));
            smbmsUser.setUserCode(resultSet.getString("userCode"));
            smbmsUser.setUserName(resultSet.getString("userName"));
            smbmsUser.setGender(resultSet.getInt("gender"));
            smbmsUser.setPhone(resultSet.getString("phone"));
            smbmsUser.setBirthday(resultSet.getDate("birthday"));
            smbmsUser.setUserRoleName(resultSet.getString("userRoleName"));
            smbmsUser.setUserRole(resultSet.getInt("userRole"));
            list.add(smbmsUser);
        }
        return list;
    }



    @Override
    public int getSmbmsUserCount(String userName,int userRole) throws Exception {
        int count = 0;
        List<SmbmsUser> list = new ArrayList<SmbmsUser>();
        List<Object> obj = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from smbms_user u inner join smbms_role r on u.userRole=r.id ");
        if(!StringUtils.isNullOrEmpty(userName)){
            sql.append("and u.userName like ? ");
            obj.add("%"+userName+"%");
        }
        if(userRole>0){
            sql.append("and u.userRole=? ");
            obj.add(userRole);
        }
        Object[] params=obj.toArray();
        ResultSet resultSet = this.executeQuery(sql.toString(),params);
        while(resultSet.next()){
            count = resultSet.getInt(1);
        }
        return count;
    }

    @Override
    public SmbmsUser queryUserById(Long id) throws Exception {
        SmbmsUser smbmsUser = new SmbmsUser();
        String sql = "select u.*,r.roleName as userRoleName from smbms_user u inner join smbms_role r on u.userRole=r.id and u.id=?";
        Object[] params = {id};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            smbmsUser.setId(resultSet.getLong("id"));
            smbmsUser.setUserCode(resultSet.getString("userCode"));
            smbmsUser.setUserName(resultSet.getString("userName"));
            smbmsUser.setUserPassword(resultSet.getString("userPassword"));
            smbmsUser.setGender(resultSet.getInt("gender"));
            smbmsUser.setPhone(resultSet.getString("phone"));
            smbmsUser.setBirthday(resultSet.getDate("birthday"));
            smbmsUser.setUserRoleName(resultSet.getString("userRoleName"));
            smbmsUser.setAddress(resultSet.getString("address"));
            smbmsUser.setUserRole(resultSet.getInt("userRole"));
        }
        return smbmsUser;
    }

    @Override
    public int updateUser(SmbmsUser smbmsUser) throws Exception {
        int count = 0;
        String sql = "update smbms_user set userName=?,gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? where id=?";
        Object[] params = {smbmsUser.getUserName(),smbmsUser.getGender(),smbmsUser.getBirthday(),smbmsUser.getPhone(),smbmsUser.getAddress(),smbmsUser.getUserRole(),smbmsUser.getModifyBy(),smbmsUser.getModifyDate(),smbmsUser.getId()};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public int addUser(SmbmsUser smbmsUser) throws Exception {
        int count = 0;
        String sql = "insert into smbms_user(userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate) value (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {smbmsUser.getUserCode(),smbmsUser.getUserName(),smbmsUser.getUserPassword(),smbmsUser.getGender(),smbmsUser.getBirthday(),smbmsUser.getPhone(),smbmsUser.getAddress(),smbmsUser.getUserRole(),smbmsUser.getCreatedBy(),smbmsUser.getCreationDate()};
        count = this.executeUpdate(sql,params);
        return  count;
    }

    @Override
    public int deleteUserById(Long id) throws Exception {
        int count = 0;
        String sql = "delete from smbms_user where id=?";
        Object[] params = {id};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public int updateUserPassword(Long id, String userPassword) throws Exception {
        int count = 0;
        String sql = "update smbms_user set userPassword=? where id=?";
        Object[] params = {userPassword,id};
        count = this.executeUpdate(sql,params);
        return count;
    }


}
