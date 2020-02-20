package xyz.xhui.award.service;

import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.model.SysUserStu;
import xyz.xhui.award.model.SysUserTutor;

import java.util.List;
import java.util.Optional;

public interface UserTutorService {
    /**
     * 查询所有辅导员详细信息
     * @return
     */
    List<SysUserTutor> findAll();

    /**
     * 添加辅导员
     * @param userTutor
     * @return
     */
    SysUserTutor save(SysUserTutor userTutor) throws EntityFieldException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysUserTutor> findById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);
}
