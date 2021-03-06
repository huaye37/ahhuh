package xyz.xhui.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.xhui.award.dao.GradeDao;
import xyz.xhui.award.model.SysGrade;
import xyz.xhui.award.service.GradeService;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeDao gradeDao;

    @Override
    public SysGrade save(SysGrade grade) {
        return gradeDao.save(grade);
    }

    @Override
    public List<SysGrade> findAll() {
        return gradeDao.findAll();
    }

    @Override
    public Optional<SysGrade> findById(Integer id) {
        return gradeDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try{
            gradeDao.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
