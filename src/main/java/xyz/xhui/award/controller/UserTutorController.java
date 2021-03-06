package xyz.xhui.award.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.model.SysUser;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.service.UserTutorService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/tutor")
@Api(tags = "辅导员管理接口")
@RolesAllowed({"ADMIN"})
public class UserTutorController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserTutorService userTutorService;

    @PostMapping(value = "")
    @ApiOperation("添加用户")
    public Result save(@RequestBody SysUserTutor userTutor) {
        logger.info(userTutor.toString());
        SysUserTutor retUserTutor = null;
        try {
            retUserTutor = userTutorService.save(userTutor);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(null, e.getMessage());
        }
        PasswordUtils.hiddenPassword(retUserTutor);
        return ResultFactory.buildSuccessResult(retUserTutor, "添加成功");
    }

    @GetMapping(value = "")
    @ApiOperation("查询所有详细信息")
    public Result findTutorAll() {
        List<SysUserTutor> tutors = userTutorService.findAll();
        for (SysUserTutor tutor : tutors) {
            PasswordUtils.hiddenPassword(tutor);
        }
        return ResultFactory.buildSuccessResult(tutors, "查询成功");
    }

    @GetMapping(value = "{id}")
    @ApiOperation("根据id查询")
    public Result findById(@PathVariable Integer id) {
        Optional<SysUserTutor> retUserTutor = userTutorService.findById(id);
        retUserTutor.ifPresent(PasswordUtils::hiddenPassword);
        return ResultFactory.buildSuccessResult(retUserTutor.orElse(null), "查询成功");
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据id删除辅导员")
    public Result deleteById(@PathVariable Integer id) {
        if (userTutorService.deleteById(id)) {
            return ResultFactory.buildSuccessResult(null, "删除成功");
        } else {
            return ResultFactory.buildFailResult(null, "删除失败");
        }
    }
}
