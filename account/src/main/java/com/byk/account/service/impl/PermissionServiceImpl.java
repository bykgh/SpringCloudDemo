package com.byk.account.service.impl;

import com.byk.account.dao.PermissionDao;
import com.byk.account.entity.Permission;
import com.byk.account.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAllPermissionList() {
        return permissionDao.findAll();
    }

    @Override
    public Permission save(Permission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public Permission findById(Long id) {
        Permission permission = null;
        try{
            Optional<Permission> optionalPermission = permissionDao.findById(id);
            permission =optionalPermission.get();
        }catch (IllegalArgumentException e){
            //logger.error("find permission by id IllegalArgumentException ："+e,e);
            //没查到就会报这个错，什么都不用做
        }
        return permission;
    }
}
