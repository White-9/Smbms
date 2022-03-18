package org.qf.service;

import org.qf.pojo.SmbmsProvider;

import java.util.List;

public interface SmbmsProviderService {
    /**
     * 按照供应商编号查询供应商
     * @param proCode 供应商
     * @return  影响的行数
     * @throws Exception
     */
    SmbmsProvider queryProviderByproCode(String proCode)throws Exception;
    /**
     *  按照供应商名称进行模糊查询并支持分页功能
     * @param proName   供应商名称
     * @param currentPageNo 当前页面
     * @param pageSize  页面容量
     * @return  供应商集合
     */
    List<SmbmsProvider> queryProviderByproName(String proName, int currentPageNo, int pageSize)throws Exception;

    /**
     *  返回按照供应商名称进行模糊查询的总记录数
     * @param proName 供应商名称
     * @return  总记录数
     */
    int getSmbmsProviderCount(String proName)throws Exception;
    /**
     * 通过id查询供应商
     * @param id 供应商id
     * @return  供应商
     * @throws Exception
     */
    SmbmsProvider queryProviderById(Long id)throws Exception;

    /**
     * 更新供应商
     * @param smbmsProvider 供应商
     * @return 影响的行数
     * @throws Exception
     */
    int updateProvider(SmbmsProvider smbmsProvider)throws Exception;
    /**
     * 增加供应商
     * @param smbmsProvider 供应商
     * @return  影响的行数
     * @throws Exception
     */
    int addProvider(SmbmsProvider smbmsProvider)throws Exception;
    /**
     * 通过供应商id删除供应商
     * @param id 供应商id
     * @return  影响的行数
     * @throws Exception
     */
    int deleteProviderById(Long id)throws Exception;
    /**
     * 查询所有的供应商的名称
     * @return  供应商集合
     * @throws Exception
     */
    List<SmbmsProvider> queryName()throws Exception;
}
