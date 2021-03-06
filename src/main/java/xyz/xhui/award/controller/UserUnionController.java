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
import xyz.xhui.award.model.SysUserTutor;
import xyz.xhui.award.model.SysUserUnion;
import xyz.xhui.award.service.UserUnionService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/union")
@Api(tags = "学生会管理接口")
@RolesAllowed({"ADMIN"})
public class UserUnionController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserUnionService userUnionService;

    @PostMapping(value = "")
    @ApiOperation("添加用户")
    public Result save(@RequestBody SysUserUnion userUnion) {
        logger.info(userUnion.toString());
        SysUserUnion retUserUnion = null;
        try {
            retUserUnion = userUnionService.save(userUnion);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(null, e.getMessage());
        }
        PasswordUtils.hiddenPassword(retUserUnion);
        return ResultFactory.buildSuccessResult(retUserUnion, "添加成功");
    }

    @GetMapping(value = "")
    @ApiOperation("查询所有详细信息")
    public Result findAll() {
        List<SysUserUnion> unions = userUnionService.findAll();
        for (SysUserUnion union : unions) {
            PasswordUtils.hiddenPassword(union);
        }
        return ResultFactory.buildSuccessResult(unions, "查询成功");
    }

    @GetMapping(value = "{id}")
    @ApiOperation("根据id查询")
    public Result findById(@PathVariable Integer id) {
        Optional<SysUserUnion> retUserUnion = userUnionService.findById(id);
        retUserUnion.ifPresent(PasswordUtils::hiddenPassword);
        return ResultFactory.buildSuccessResult(retUserUnion.orElse(null), "查询成功");
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据id删除")
    public Result deleteById(@PathVariable Integer id) {
        if (userUnionService.deleteById(id)) {
            return ResultFactory.buildSuccessResult(null, "删除成功");
        } else {
            return ResultFactory.buildFailResult(null, "删除失败");
        }
    }
}
