package com.byk.account.service.impl;

import com.byk.account.dao.SysUserDao;
import com.byk.account.entity.SysUser;
import com.byk.account.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yikai.bi
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Override
    public SysUser findByUserCode(String userCode) {
        return userDao.findByUserCode(userCode);
    }

    @Override
    public SysUser save(SysUser user) {
        return userDao.save(user);
    }

    @Override
    public SysUser update(SysUser user) {
        return userDao.save(user);
    }


}
