package com.byk.account.service.impl;

import com.byk.account.dao.RoleDao;
import com.byk.account.entity.Role;
import com.byk.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yikai.bi
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询角色列表
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        return roleDao.findAll();
    }

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @Override
    public Role update(Role role) {
        return roleDao.save(role);
    }
}
