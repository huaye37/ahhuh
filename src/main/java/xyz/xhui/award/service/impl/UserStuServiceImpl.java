package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.award.dao.UserDao;
import xyz.xhui.award.dao.UserStuDao;
import xyz.xhui.award.model.SysUserStu;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.service.UserStuService;

import java.util.List;
import java.util.Optional;

@Service
public class UserStuServiceImpl implements UserStuService {
    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUserStu> findAll() {
        return userStuDao.findAll();
    }

    @Override
    public Optional<SysUserStu> findById(Integer id) {
        return userStuDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(Integer id) {
        Optional<SysUserStu> retUserStu = this.findById(id);
        retUserStu.ifPresent(userStu -> {
            userStuDao.delete(userStu);
            userDao.delete(userStu.getUser());
        });
        return retUserStu.isPresent();
    }
}
