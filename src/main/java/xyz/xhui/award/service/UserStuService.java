package xyz.xhui.award.service;

import xyz.xhui.award.model.SysUserStu;

import java.util.List;
import java.util.Optional;

public interface UserStuService {
    /**
     * 查询所有学生详细信息
     * @return
     */
    List<SysUserStu> findAll();

    /**
     * 根据id删除学生
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);

    Optional<SysUserStu> findById(Integer id);
}
