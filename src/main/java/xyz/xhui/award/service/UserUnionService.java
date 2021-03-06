package xyz.xhui.award.service;

import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.model.SysUserUnion;

import java.util.List;
import java.util.Optional;

public interface UserUnionService {
    /**
     * 查询所有辅导员详细信息
     * @return
     */
    List<SysUserUnion> findAll();

    /**
     * 添加辅导员
     * @param userUnion
     * @return
     */
    SysUserUnion save(SysUserUnion userUnion) throws EntityFieldException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysUserUnion> findById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);
}
