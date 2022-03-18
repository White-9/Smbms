package org.qf.service;

import org.qf.pojo.SmbmsBill;

import java.util.List;

public interface SmbmsBillService {
    /**
     * 通过订单编号来查询订单
     * @param billCode 订单编号
     * @return 订单
     * @throws Exception
     */
    SmbmsBill queryBillBybillCode(String billCode)throws Exception;
    /**
     * 按照商品名进行模糊查询，并支持按照供应商进行查询，并支持按是否付款进行查询订单，还支持分页功能
     * @param productName 商品名
     * @param providerId 供应商id
     * @param isPayment 是否付款
     * @param currentPageNo 当前页面
     * @param pageSize  页面容量
     * @return 订单集合
     * @throws Exception
     */
    List<SmbmsBill> queryBillByproductName(String productName, Long providerId, int isPayment, int currentPageNo, int pageSize)throws Exception;

    /**
     * 返回按照商品名进行模糊查询，并支持按照供应商进行查询，并支持按是否付款进行查询的总记录数
     * @param productName 商品名
     * @param providerId 供应商id
     * @param isPayment 是否付款
     * @return  总记录数
     * @throws Exception
     */
    int getSmbmsBillCount(String productName,Long providerId,int isPayment)throws Exception;
    /**
     * 通过订单id来查询订单
     * @param id 订单id
     * @return  订单
     * @throws Exception
     */
    SmbmsBill queryBillById(Long id)throws Exception;
    /**
     *  更新订单
     * @param smbmsBill 新的订单
     * @return  影响的行数
     * @throws Exception
     */
    int updateBill(SmbmsBill smbmsBill)throws Exception;
    /**
     *  添加订单
     * @param smbmsBill 新的订单
     * @return  影响的行数
     * @throws Exception
     */
    int addBill(SmbmsBill smbmsBill)throws Exception;

    /**
     *  通过订单id删除订单
     * @param id 订单id
     * @return  影响的行数
     * @throws Exception
     */
    int deleteBillById(Long id)throws Exception;
}
