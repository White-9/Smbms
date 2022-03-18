package org.qf.dao.Impl;

import org.qf.dao.SmbmsRoleDao;
import org.qf.pojo.SmbmsRole;
import org.qf.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SmbmsRoleDaoImpl extends BaseDao implements SmbmsRoleDao {
    @Override
    public List<SmbmsRole> queryRole() throws Exception {
        List<SmbmsRole> list = new ArrayList<SmbmsRole>();
        String sql = "select * from smbms_role";
        Object[] params = {};
        ResultSet resultSet = this.executeQuery(sql,params);
        while(resultSet.next()){
            SmbmsRole smbmsRole = new SmbmsRole();
            smbmsRole.setId(resultSet.getLong("id"));
            smbmsRole.setRoleCode(resultSet.getString("roleCode"));
            smbmsRole.setRoleName(resultSet.getString("roleName"));
            list.add(smbmsRole);
        }
        return list;
    }
}
