package xyz.xhui.award.service;

import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.model.SysUserAdmin;

import java.util.List;
import java.util.Optional;

public interface UserAdminService {
    List<SysUserAdmin> findAll();

    Optional<SysUserAdmin> findById(Integer id);

    SysUserAdmin save(SysUserAdmin userAdmin) throws EntityFieldException;

    Boolean deleteById(Integer id);
}
