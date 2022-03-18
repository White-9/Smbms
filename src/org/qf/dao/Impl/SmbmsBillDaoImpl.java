package org.qf.dao.Impl;

import com.mysql.jdbc.StringUtils;
import org.qf.dao.SmbmsBillDao;
import org.qf.pojo.SmbmsBill;
import org.qf.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SmbmsBillDaoImpl extends BaseDao implements SmbmsBillDao {
    @Override
    public SmbmsBill queryBillBybillCode(String billCode) throws Exception {
        SmbmsBill smbmsBill = new SmbmsBill();
        String sql = "select b.*,p.proName as providerName from smbms_bill b inner join smbms_provider p on b.providerId=p.id where b.billCode=?";
        Object[] params = {billCode};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            smbmsBill.setId(resultSet.getLong("id"));
            smbmsBill.setBillCode(resultSet.getString("billCode"));
            smbmsBill.setProductName(resultSet.getString("productName"));
            smbmsBill.setProductDesc(resultSet.getString("productDesc"));
            smbmsBill.setProductUnit(resultSet.getString("productUnit"));
            smbmsBill.setProductCount(resultSet.getDouble("productCount"));
            smbmsBill.setTotalPrice(resultSet.getDouble("totalPrice"));
            smbmsBill.setIsPayment(resultSet.getInt("isPayment"));
            smbmsBill.setProviderId(resultSet.getLong("providerId"));
            smbmsBill.setProviderName(resultSet.getString("providerName"));
            smbmsBill.setCreationDate(resultSet.getDate("creationDate"));
            smbmsBill.setCreateBy(resultSet.getLong("createdBy"));
            smbmsBill.setModifyBy(resultSet.getLong("modifyBy"));
            smbmsBill.setModifyDate(resultSet.getDate("modifyDate"));
        }
        return smbmsBill;
    }

    @Override
    public List<SmbmsBill> queryBillByproductName(String productName, Long providerId, int isPayment, int currentPageNo, int pageSize) throws Exception {
        List<SmbmsBill> list = new ArrayList<SmbmsBill>();
        List<Object> obj = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select b.*,p.proName as providerName from smbms_bill b inner join smbms_provider p on b.providerId=p.id ");
        //
        if(!StringUtils.isNullOrEmpty(productName)){
            sql.append("and b.productName like ? ");
            obj.add("%"+productName+"%");
        }
        if(providerId>0){
            sql.append("and b.providerId=? ");
            obj.add(providerId);
        }
        if(isPayment>0){
            sql.append("and b.isPayment=? ");
            obj.add(isPayment);
        }
        sql.append("order by b.creationDate desc limit ?,? ");
        obj.add((currentPageNo-1)*pageSize);
        obj.add(pageSize);
        Object[] params = obj.toArray();
        ResultSet resultSet = this.executeQuery(sql.toString(),params);
        while (resultSet.next()){
            SmbmsBill smbmsBill = new SmbmsBill();
            smbmsBill.setId(resultSet.getLong("id"));
            smbmsBill.setBillCode(resultSet.getString("billCode"));
            smbmsBill.setProductName(resultSet.getString("productName"));
            smbmsBill.setProductDesc(resultSet.getString("productDesc"));
            smbmsBill.setProductUnit(resultSet.getString("productUnit"));
            smbmsBill.setProductCount(resultSet.getDouble("productCount"));
            smbmsBill.setTotalPrice(resultSet.getDouble("totalPrice"));
            smbmsBill.setIsPayment(resultSet.getInt("isPayment"));
            smbmsBill.setProviderId(resultSet.getLong("providerId"));
            smbmsBill.setProviderName(resultSet.getString("providerName"));
            smbmsBill.setCreationDate(resultSet.getDate("creationDate"));
            smbmsBill.setCreateBy(resultSet.getLong("createdBy"));
            smbmsBill.setModifyBy(resultSet.getLong("modifyBy"));
            smbmsBill.setModifyDate(resultSet.getDate("modifyDate"));
            list.add(smbmsBill);
        }
        return list;
    }

    @Override
    public int getSmbmsBillCount(String productName, Long providerId, int isPayment) throws Exception {
        int count = 0;
        List<Object> obj = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from smbms_bill b inner join smbms_provider p on b.providerId=p.id ");
        //
        if(!StringUtils.isNullOrEmpty(productName)){
            sql.append("and b.productName like ? ");
            obj.add("%"+productName+"%");
        }
        if(providerId>0){
            sql.append("and b.providerId=? ");
            obj.add(providerId);
        }
        if(isPayment>0){
            sql.append("and b.isPayment=? ");
            obj.add(isPayment);
        }
        Object[] params = obj.toArray();
        ResultSet resultSet = this.executeQuery(sql.toString(),params);
        while (resultSet.next()){
            count = resultSet.getInt(1);
        }
        return count;
    }

    @Override
    public SmbmsBill queryBillById(Long id) throws Exception {
        SmbmsBill smbmsBill = new SmbmsBill();
        String sql = "select b.*,p.proName as providerName from smbms_bill b inner join smbms_provider p on b.providerId=p.id where b.id=?";
        Object[] params = {id};
        ResultSet resultSet = this.executeQuery(sql,params);
        while (resultSet.next()){
            smbmsBill.setId(resultSet.getLong("id"));
            smbmsBill.setBillCode(resultSet.getString("billCode"));
            smbmsBill.setProductName(resultSet.getString("productName"));
            smbmsBill.setProductDesc(resultSet.getString("productDesc"));
            smbmsBill.setProductUnit(resultSet.getString("productUnit"));
            smbmsBill.setProductCount(resultSet.getDouble("productCount"));
            smbmsBill.setTotalPrice(resultSet.getDouble("totalPrice"));
            smbmsBill.setIsPayment(resultSet.getInt("isPayment"));
            smbmsBill.setProviderId(resultSet.getLong("providerId"));
            smbmsBill.setProviderName(resultSet.getString("providerName"));
            smbmsBill.setCreationDate(resultSet.getDate("creationDate"));
            smbmsBill.setCreateBy(resultSet.getLong("createdBy"));
            smbmsBill.setModifyBy(resultSet.getLong("modifyBy"));
            smbmsBill.setModifyDate(resultSet.getDate("modifyDate"));
        }
        return smbmsBill;
    }

    @Override
    public int updateBill(SmbmsBill smbmsBill) throws Exception {
        int count = 0;
        String sql = "update smbms_bill set billCode=?,productName=?,productUnit=?,productCount=?,totalPrice=?,providerId=?,isPayment=?,modifyBy=?,modifyDate=? where id=?";
        Object[] params = {smbmsBill.getBillCode(),smbmsBill.getProductName(),smbmsBill.getProductUnit(),smbmsBill.getProductCount(),smbmsBill.getTotalPrice(),smbmsBill.getProviderId(),smbmsBill.getIsPayment(),smbmsBill.getModifyBy(),smbmsBill.getModifyDate(),smbmsBill.getId()};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public int addBill(SmbmsBill smbmsBill) throws Exception {
        int count = 0;
        String sql = "insert into smbms_bill(billCode, productName, productDesc, productUnit, productCount, totalPrice, isPayment, createdBy, creationDate, providerId) value (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {smbmsBill.getBillCode(),smbmsBill.getProductName(),smbmsBill.getProductDesc(),smbmsBill.getProductUnit(),smbmsBill.getProductCount(),smbmsBill.getTotalPrice(),smbmsBill.getIsPayment(),smbmsBill.getCreateBy(),smbmsBill.getCreationDate(),smbmsBill.getProviderId()};
        count = this.executeUpdate(sql,params);
        return count;
    }

    @Override
    public int deleteBillById(Long id) throws Exception {
        int count = 0;
        String sql = "delete from smbms_bill where id=?";
        Object[] params = {id};
        count = this.executeUpdate(sql,params);
        return count;
    }
}
