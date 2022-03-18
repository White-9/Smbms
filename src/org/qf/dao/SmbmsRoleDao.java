package org.qf.dao;

import org.qf.pojo.SmbmsRole;

import java.util.List;

public interface SmbmsRoleDao {
    /**
     * 按照用户角色查询
     * @return  用户集合
     * @throws Exception
     */
    List<SmbmsRole> queryRole()throws Exception;

}
