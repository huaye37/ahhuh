package xyz.xhui.award.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.model.SysUserAdmin;
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.service.UserAdminService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/admin")
@Api(tags = "管理员管理接口")
@RolesAllowed({"ADMIN"})
public class UserAdminController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserAdminService userAdminService;

    @PostMapping(value = "")
    @ApiOperation("添加管理员")
    public Result save(@RequestBody SysUserAdmin userAdmin) {
        logger.info(userAdmin.toString());
        SysUserAdmin retUserAdmin = null;
        try {
            retUserAdmin = userAdminService.save(userAdmin);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(null, e.getMessage());
        }
        PasswordUtils.hiddenPassword(retUserAdmin);
        return ResultFactory.buildSuccessResult(retUserAdmin, "添加成功");
    }

    @GetMapping(value = "")
    @ApiOperation("查询所有详细信息")
    public Result findTutorAll() {
        List<SysUserAdmin> admins = userAdminService.findAll();
        for (SysUserAdmin admin : admins) {
            PasswordUtils.hiddenPassword(admin);
        }
        return ResultFactory.buildSuccessResult(admins, "查询成功");
    }

    @GetMapping(value = "{id}")
    @ApiOperation("根据id查询")
    public Result findById(@PathVariable Integer id) {
        Optional<SysUserAdmin> retUserAdmin = userAdminService.findById(id);
        retUserAdmin.ifPresent(PasswordUtils::hiddenPassword);
        return ResultFactory.buildSuccessResult(retUserAdmin.orElse(null), "查询成功");
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据id删除管理员")
    public Result deleteById(@PathVariable Integer id) {
        if(userAdminService.deleteById(id)) {
            return ResultFactory.buildSuccessResult(null, "删除成功");
        } else {
            return ResultFactory.buildFailResult(null, "删除失败");
        }
    }
}
