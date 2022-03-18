package org.qf.service.Impl;

import org.qf.dao.Impl.SmbmsRoleDaoImpl;
import org.qf.dao.SmbmsRoleDao;
import org.qf.pojo.SmbmsRole;
import org.qf.service.SmbmsRoleService;
import org.qf.service.SmbmsUserService;

import java.util.List;

public class SmbmsRoleServiceImpl implements SmbmsRoleService {
    //注入dao
    private final SmbmsRoleDao smbmsRoleDao = new SmbmsRoleDaoImpl();
    @Override
    public List<SmbmsRole> queryRole() throws Exception {
        return smbmsRoleDao.queryRole();
    }
}
