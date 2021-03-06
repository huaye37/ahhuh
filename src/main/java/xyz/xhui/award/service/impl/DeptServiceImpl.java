package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.xhui.award.dao.DeptDao;
import xyz.xhui.award.model.SysDept;
import xyz.xhui.award.service.DeptService;

import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptDao deptDao;

    @Override
    public SysDept save(SysDept dept) {
        return deptDao.save(dept);
    }

    @Override
    public List<SysDept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public Optional<SysDept> findById(Integer id) {
        return deptDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try{
            deptDao.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
