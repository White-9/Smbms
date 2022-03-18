package org.qf.service.Impl;

import org.qf.dao.Impl.SmbmsProviderDaoImpl;
import org.qf.dao.SmbmsProviderDao;
import org.qf.pojo.SmbmsProvider;
import org.qf.service.SmbmsProviderService;

import java.util.List;

public class SmbmsProviderServiceImpl implements SmbmsProviderService {
    //注入dao
    SmbmsProviderDao smbmsProviderDao = new SmbmsProviderDaoImpl();

    @Override
    public SmbmsProvider queryProviderByproCode(String proCode) throws Exception {
        return smbmsProviderDao.queryProviderByproCode(proCode);
    }

    @Override
    public List<SmbmsProvider> queryProviderByproName(String proName, int currentPageNo, int pageSize) throws Exception {
        return smbmsProviderDao.queryProviderByproName(proName,currentPageNo,pageSize);
    }

    @Override
    public int getSmbmsProviderCount(String proName) throws Exception {
        return smbmsProviderDao.getSmbmsProviderCount(proName);
    }

    @Override
    public SmbmsProvider queryProviderById(Long id) throws Exception {
        return smbmsProviderDao.queryProviderById(id);
    }

    @Override
    public int updateProvider(SmbmsProvider smbmsProvider) throws Exception {
        return smbmsProviderDao.updateProvider(smbmsProvider);
    }

    @Override
    public int addProvider(SmbmsProvider smbmsProvider) throws Exception {
        return smbmsProviderDao.addProvider(smbmsProvider);
    }

    @Override
    public int deleteProviderById(Long id) throws Exception {
        return smbmsProviderDao.deleteProviderById(id);
    }

    @Override
    public List<SmbmsProvider> queryName() throws Exception {
        return smbmsProviderDao.queryName();
    }
}
