package org.qf.dao.Impl;

import com.mysql.jdbc.StringUtils;
import org.qf.dao.SmbmsProviderDao;
import org.qf.pojo.SmbmsProvider;
import org.qf.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SmbmsProviderDaoImpl extends BaseDao implements SmbmsProviderDao {
    @Override
    public SmbmsProvider queryProviderByproCode(String proCode) throws Exception {
        SmbmsProvider smbmsProvider = new SmbmsProvider();
        String sql = "select * from smbms_provider where proCode=?";
        Object[] params = {proCode};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            smbmsProvider.setId(resultSet.getLong("id"));
            smbmsProvider.setProCode(resultSet.getString("proCode"));
            smbmsProvider.setProName(resultSet.getString("proName"));
            smbmsProvider.setProContact(resultSet.getString("proContact"));
            smbmsProvider.setProPhone(resultSet.getString("proPhone"));
            smbmsProvider.setProFax(resultSet.getString("proFax"));
            smbmsProvider.setCreationDate(resultSet.getDate("creationDate"));
            smbmsProvider.setProDesc(resultSet.getString("proDesc"));
            smbmsProvider.setProAddress(resultSet.getString("proAddress"));
            smbmsProvider.setCreatedBy(resultSet.getLong("createdBy"));
            smbmsProvider.setModifyBy(resultSet.getLong("modifyBy"));
            smbmsProvider.setModifyDate(resultSet.getDate("modifyDate"));
        }
        return smbmsProvider;
    }

    @Override
    public List<SmbmsProvider> queryProviderByproName(String proName, int currentPageNo, int pageSize)throws Exception {
        List<SmbmsProvider> list = new ArrayList<SmbmsProvider>();
        List<Object> obj = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from smbms_provider ");//"select * from smbms_provider where proName like ? order by creationDate desc limit ?,?";
        if(!StringUtils.isNullOrEmpty(proName)) {
            sql.append("where proName like ? ");
            obj.add("%"+proName+"%");
        }
        sql.append("order by creationDate desc limit ?,? ");
        obj.add((currentPageNo-1)*pageSize);
        obj.add(pageSize);
        Object[] params = obj.toArray();
        ResultSet resultSet = this.executeQuery(sql.toString(),params);
        while(resultSet.next()){
            SmbmsProvider smbmsProvider = new SmbmsProvider();
            smbmsProvider.setId(resultSet.getLong("id"));
            smbmsProvider.setProCode(resultSet.getString("proCode"));
            smbmsProvider.setProName(resultSet.getString("proName"));
            smbmsProvider.setProContact(resultSet.getString("proContact"));
            smbmsProvider.setProPhone(resultSet.getString("proPhone"));
            smbmsProvider.setProFax(resultSet.getString("proFax"));
            smbmsProvider.setCreationDate(resultSet.getDate("creationDate"));
            smbmsProvider.setProDesc(resultSet.getString("proDesc"));
            smbmsProvider.setProAddress(resultSet.getString("proAddress"));
            smbmsProvider.setCreatedBy(resultSet.getLong("createdBy"));
            smbmsProvider.setModifyBy(resultSet.getLong("modifyBy"));
            smbmsProvider.setModifyDate(resultSet.getDate("modifyDate"));
            list.add(smbmsProvider);
        }
        return list;

    }

    @Override
    public int getSmbmsProviderCount(String proName)throws Exception {
        int conut=0;
        String sql = "select count(*) from smbms_provider where proName like ? ";
        Object[] params = {"%"+proName+"%"};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            conut = resultSet.getInt(1);
        }
        return conut;
    }

    @Override
    public SmbmsProvider queryProviderById(Long id) throws Exception {
        String sql = "select * from smbms_provider where id=? ";
        Object[] params = {id};
        ResultSet resultSet = this.executeQuery(sql,params);
        SmbmsProvider smbmsProvider = new SmbmsProvider();
        while(resultSet.next()){
            smbmsProvider.setId(resultSet.getLong("id"));
            smbmsProvider.setProCode(resultSet.getString("proCode"));
            smbmsProvider.setProName(resultSet.getString("proName"));
            smbmsProvider.setProContact(resultSet.getString("proContact"));
            smbmsProvider.setProPhone(resultSet.getString("proPhone"));
            smbmsProvider.setProFax(resultSet.getString("proFax"));
            smbmsProvider.setCreationDate(resultSet.getDate("creationDate"));
            smbmsProvider.setProDesc(resultSet.getString("proDesc"));
            smbmsProvider.setProAddress(resultSet.getString("proAddress"));
            smbmsProvider.setCreatedBy(resultSet.getLong("createdBy"));
            smbmsProvider.setModifyBy(resultSet.getLong("modifyBy"));
            smbmsProvider.setModifyDate(resultSet.getDate("modifyDate"));
        }
        return smbmsProvider;
    }

    @Override
    public int updateProvider(SmbmsProvider smbmsProvider) throws Exception {
        int count = 0;
        String sql = "update smbms_provider set proName=?,proCode=?,proContact=?,proPhone=?,proAddress=?,proFax=?,proDesc=?,modifyBy=?,modifyDate=? where id=?";
        Object[] params = {smbmsProvider.getProName(),smbmsProvider.getProCode(),smbmsProvider.getProContact(),smbmsProvider.getProPhone(),smbmsProvider.getProAddress(),smbmsProvider.getProFax(),smbmsProvider.getProDesc(),smbmsProvider.getModifyBy(),smbmsProvider.getModifyDate(),smbmsProvider.getId()};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public int addProvider(SmbmsProvider smbmsProvider) throws Exception {
        int count = 0;
        String sql = "insert into smbms_provider(proCode, proName, proDesc, proContact, proPhone, proAddress, proFax, createdBy, creationDate) value (?,?,?,?,?,?,?,?,?)";
        Object[] params = {smbmsProvider.getProCode(),smbmsProvider.getProName(),smbmsProvider.getProDesc(),smbmsProvider.getProContact(),smbmsProvider.getProPhone(),smbmsProvider.getProAddress(),smbmsProvider.getProFax(),smbmsProvider.getCreatedBy(),smbmsProvider.getCreationDate()};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public int deleteProviderById(Long id) throws Exception {
        int count = 0;
        String sql = "delete from smbms_provider where id=?";
        Object[] params = {id};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public List<SmbmsProvider> queryName() throws Exception {
        List<SmbmsProvider> list = new ArrayList<SmbmsProvider>();
        String sql = "select * from smbms_provider";
        Object[] params = {};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            SmbmsProvider smbmsProvider = new SmbmsProvider();
            smbmsProvider.setId(resultSet.getLong("id"));
            smbmsProvider.setProName(resultSet.getString("proName"));
            smbmsProvider.setProCode(resultSet.getString("proCode"));
            list.add(smbmsProvider);
        }
        return list;
    }
}
