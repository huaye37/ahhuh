package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.security.JwtUser;
import xyz.xhui.award.config.sysenum.RoleEnum;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.dao.UserDao;
import xyz.xhui.award.model.SysUser;
import xyz.xhui.award.service.UserService;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Page<SysUser> findAll(Integer pagenum, Integer pagesize) {
        Pageable pageable = PageRequest.of(pagenum, pagesize);
        return userDao.findAll(pageable);
    }

    @Override
    public Optional<SysUser> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public SysUser findByUsernameEquals(String username) {
        List<SysUser> sysUsers = userDao.findByUsernameEquals(username);
        return sysUsers.size() > 0 ? sysUsers.get(0) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser save(SysUser sysUser) throws EntityFieldException {
        sysUser.setId(null);
        if(this.findByUsernameEquals(sysUser.getUsername()) != null) {
            throw new EntityFieldException("用户名已经存在");
        }
        sysUser.setPassword(PasswordUtils.encode(sysUser.getPassword()));
        return userDao.save(sysUser);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            userDao.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.findByUsernameEquals(username);
        if (sysUser == null)
            throw new UsernameNotFoundException(username);
        return new JwtUser(sysUser);
    }

}
