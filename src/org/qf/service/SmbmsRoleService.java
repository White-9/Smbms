package org.qf.service;

import org.qf.pojo.SmbmsRole;

import java.util.List;

public interface SmbmsRoleService {
    /**
     * 查询角色列表
     * @return 角色列表
     * @throws Exception
     */
    List<SmbmsRole> queryRole()throws Exception;
}
