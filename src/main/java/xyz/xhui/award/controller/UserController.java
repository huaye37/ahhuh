package xyz.xhui.award.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.model.SysUser;
import xyz.xhui.award.model.SysUserStu;
import xyz.xhui.award.service.UserService;
import xyz.xhui.award.service.UserStuService;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理接口")
//@Secured("ROLE_ADMIN")
//@PreAuthorize("hasRole('STU')")
@RolesAllowed({"ADMIN"})
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

//    @PostMapping(value = "")
//    @ApiOperation("添加用户")
//    public Result save(@RequestBody SysUser sysUser) {
//        logger.info(sysUser.toString());
//        SysUser retUser = null;
//        try {
//            retUser = userService.save(sysUser);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(null, e.getMessage());
//        }
//        PasswordUtils.hiddenPassword(retUser);
//        return ResultFactory.buildSuccessResult(retUser, "添加成功");
//    }

    @GetMapping(value = "")
    @ApiOperation("分页查询所有用户")
    public Result findAll(@RequestParam("pagenum") Integer pagenum, @RequestParam("pagesize") Integer pagesize) {
        Page<SysUser> page = userService.findAll(pagenum, pagesize);
        List<SysUser> content = page.getContent();
        for (SysUser user : content) {
            PasswordUtils.hiddenPassword(user);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotalElements());
        map.put("pagenum", page.getNumber());
        map.put("users", page.getContent());
        return ResultFactory.buildSuccessResult(map, "查询成功");
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("根据id查询用户")
    public Result findOne(@PathVariable Integer id) {
        Optional<SysUser> retUser = userService.findById(id);
        retUser.ifPresent(PasswordUtils::hiddenPassword);
        return ResultFactory.buildSuccessResult(retUser.orElse(null), "查询成功");
    }
}
