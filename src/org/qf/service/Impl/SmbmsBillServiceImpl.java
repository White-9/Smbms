package org.qf.service.Impl;

import org.qf.dao.Impl.SmbmsBillDaoImpl;
import org.qf.dao.SmbmsBillDao;
import org.qf.pojo.SmbmsBill;
import org.qf.service.SmbmsBillService;

import java.util.List;

public class SmbmsBillServiceImpl implements SmbmsBillService {
    //注入dao
    SmbmsBillDao smbmsBillDao = new SmbmsBillDaoImpl();

    @Override
    public SmbmsBill queryBillBybillCode(String billCode) throws Exception {
        return smbmsBillDao.queryBillBybillCode(billCode);
    }

    @Override
    public List<SmbmsBill> queryBillByproductName(String productName, Long providerId, int isPayment, int currentPageNo, int pageSize) throws Exception {
        return smbmsBillDao.queryBillByproductName(productName, providerId, isPayment, currentPageNo, pageSize);
    }

    @Override
    public int getSmbmsBillCount(String productName, Long providerId, int isPayment) throws Exception {
        return smbmsBillDao.getSmbmsBillCount(productName,providerId,isPayment);
    }

    @Override
    public SmbmsBill queryBillById(Long id) throws Exception {
        return smbmsBillDao.queryBillById(id);
    }

    @Override
    public int updateBill(SmbmsBill smbmsBill) throws Exception {
        return smbmsBillDao.updateBill(smbmsBill);
    }

    @Override
    public int addBill(SmbmsBill smbmsBill) throws Exception {
        return smbmsBillDao.addBill(smbmsBill);
    }

    @Override
    public int deleteBillById(Long id) throws Exception {
        return smbmsBillDao.deleteBillById(id);
    }
}
