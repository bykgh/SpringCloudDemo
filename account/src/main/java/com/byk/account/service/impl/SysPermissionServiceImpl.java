package com.byk.account.service.impl;

import com.byk.account.dao.SysPermissionDao;
import com.byk.account.entity.SysPermission;
import com.byk.account.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author yikai.bi
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private SysPermissionDao permissionDao;

    @Override
    public List<SysPermission> findAllPermissionList() {
        return permissionDao.findAll();
    }

    @Override
    public SysPermission save(SysPermission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public SysPermission update(SysPermission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public SysPermission findById(Long id) {
        SysPermission permission = null;
        try{
            Optional<SysPermission> optionalPermission = permissionDao.findById(id);
            permission =optionalPermission.get();
        }catch (IllegalArgumentException e){
            //logger.error("find permission by id IllegalArgumentException ："+e,e);
            //没查到就会报这个错，什么都不用做
        }
        return permission;
    }
}
