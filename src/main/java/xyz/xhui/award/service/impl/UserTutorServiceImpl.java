package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.sysenum.RoleEnum;
import xyz.xhui.award.dao.DeptDao;
import xyz.xhui.award.dao.GradeDao;
import xyz.xhui.award.dao.UserDao;
import xyz.xhui.award.dao.UserTutorDao;
import xyz.xhui.award.model.SysDept;
import xyz.xhui.award.model.SysGrade;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.service.UserService;
import xyz.xhui.award.service.UserTutorService;

import java.util.List;
import java.util.Optional;

@Service
public class UserTutorServiceImpl implements UserTutorService {
    @Autowired
    private UserTutorDao userTutorDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUserTutor> findAll() {
        return userTutorDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserTutor save(SysUserTutor userTutor) throws EntityFieldException {
        userTutor.getUser().setRole(RoleEnum.ROLE_TUTOR);
        userService.save(userTutor.getUser());
        userTutor.setId(null);
        SysDept sysDept = deptDao.findById(userTutor.getDept().getId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userTutor.getDept().getId() + "不存在")
        );
        userTutor.setDept(sysDept);
        SysGrade sysGrade = gradeDao.findById(userTutor.getGrade().getId()).orElseThrow(
                () -> new EntityFieldException("年级id:" + userTutor.getDept().getId() + "不存在")
        );
        userTutor.setGrade(sysGrade);
        return userTutorDao.save(userTutor);
    }

    @Override
    public Optional<SysUserTutor> findById(Integer id) {
        return userTutorDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(Integer id) {
        Optional<SysUserTutor> retUserTutor = this.findById(id);
        retUserTutor.ifPresent(userTutor -> {
            userTutorDao.delete(userTutor);
            userDao.delete(userTutor.getUser());
        });
        return retUserTutor.isPresent();
    }
}
