package com.byk.account.service.impl;

import com.byk.account.dao.SysRoleDao;
import com.byk.account.entity.SysRole;
import com.byk.account.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author yikai.bi
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;

    /**
     * 查询角色列表
     * @return
     */
    @Override
    public List<SysRole> findAllRole() {
        return roleDao.findAll();
    }

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    @Override
    public SysRole save(SysRole role) {
        return roleDao.save(role);
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @Override
    public SysRole update(SysRole role) {
        return roleDao.save(role);
    }

    @Override
    public SysRole findById(Long id) {
        SysRole role = null;
        try{
            Optional<SysRole> optionalRole = roleDao.findById(id);
            role = optionalRole.get();
        }catch (IllegalArgumentException e){
            //没查到什么都不用做
        }
        return role;
    }
}
