package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.sysenum.RoleEnum;
import xyz.xhui.award.dao.UserAdminDao;
import xyz.xhui.award.dao.UserDao;
import xyz.xhui.award.model.SysUserAdmin;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.service.UserAdminService;
import xyz.xhui.award.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminServiceImpl implements UserAdminService {
    @Autowired
    private UserAdminDao userAdminDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUserAdmin> findAll() {
        return userAdminDao.findAll();
    }

    @Override
    public Optional<SysUserAdmin> findById(Integer id) {
        return userAdminDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserAdmin save(SysUserAdmin userAdmin) throws EntityFieldException {
        userAdmin.getUser().setRole(RoleEnum.ROLE_ADMIN);
        userService.save(userAdmin.getUser());
        userAdmin.setId(null);
        return userAdminDao.save(userAdmin);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<SysUserAdmin> retUserAdmin = this.findById(id);
        retUserAdmin.ifPresent(userAdmin -> {
            userAdminDao.delete(userAdmin);
            userDao.delete(userAdmin.getUser());
        });
        return retUserAdmin.isPresent();
    }
}
