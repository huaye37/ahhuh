package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.sysenum.RoleEnum;
import xyz.xhui.award.dao.DeptDao;
import xyz.xhui.award.dao.UserDao;
import xyz.xhui.award.dao.UserUnionDao;
import xyz.xhui.award.model.SysDept;
import xyz.xhui.award.model.SysGrade;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.model.SysUserUnion;
import xyz.xhui.award.service.DeptService;
import xyz.xhui.award.service.UserService;
import xyz.xhui.award.service.UserUnionService;

import java.util.List;
import java.util.Optional;

@Service
public class UserUnionServiceImpl implements UserUnionService {
    @Autowired
    private UserUnionDao userUnionDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUserUnion> findAll() {
        return userUnionDao.findAll();
    }

    @Override
    public SysUserUnion save(SysUserUnion userUnion) throws EntityFieldException {
        userUnion.getUser().setRole(RoleEnum.ROLE_UNION);
        userService.save(userUnion.getUser());
        userUnion.setId(null);
        SysDept sysDept = deptDao.findById(userUnion.getDept().getId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userUnion.getDept().getId() + "不存在")
        );
        userUnion.setDept(sysDept);
        return userUnionDao.save(userUnion);
    }

    @Override
    public Optional<SysUserUnion> findById(Integer id) {
        return userUnionDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<SysUserUnion> retUserUnion = this.findById(id);
        retUserUnion.ifPresent(sysUserUnion -> {
            userUnionDao.delete(sysUserUnion);
            userDao.delete(sysUserUnion.getUser());
        });
        return retUserUnion.isPresent();
    }
}
