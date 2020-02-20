package xyz.xhui.award.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.model.SysUserStu;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.service.UserStuService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/stu")
@Api(tags = "学生管理接口")
@RolesAllowed({"ADMIN"})
public class UserStuController {

    @Autowired
    private UserStuService userStuService;

    @GetMapping(value = "")
    @ApiOperation("查询所有学生详细信息")
    public Result findStuAll() {
        List<SysUserStu> userStus = userStuService.findAll();
        for (SysUserStu userStu : userStus) {
            PasswordUtils.hiddenPassword(userStu);
        }
        return ResultFactory.buildSuccessResult(userStus, "查询成功");
    }

    @GetMapping(value = "{id}")
    @ApiOperation("根据id查询")
    public Result findById(@PathVariable Integer id) {
        Optional<SysUserStu> retUserStu = userStuService.findById(id);
        retUserStu.ifPresent(PasswordUtils::hiddenPassword);
        return ResultFactory.buildSuccessResult(retUserStu.orElse(null), "查询成功");
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据id删除学生")
    public Result deleteById(@PathVariable Integer id) {
        if(userStuService.deleteById(id)) {
            return ResultFactory.buildSuccessResult(null, "删除成功");
        } else {
            return ResultFactory.buildFailResult(null, "删除失败");
        }
    }
}
