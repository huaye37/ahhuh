package xyz.xhui.award.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;
import xyz.xhui.award.model.SysDept;
import xyz.xhui.award.service.DeptService;

@RestController
@RequestMapping("/dept")
@Api(tags = "系部管理接口")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping(name = "")
    @ApiOperation("添加系部")
    public Result save(@RequestParam String name) {
        SysDept dept = new SysDept();
        dept.setName(name);
        return ResultFactory.buildSuccessResult(deptService.save(dept), "保存成功");
    }

    @GetMapping(name = "")
    @ApiOperation("查询所有系部")
    public Result findAll() {
        return ResultFactory.buildSuccessResult(deptService.findAll(), "查询成功");
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("根据id查询系部")
    public Result findOne(@PathVariable Integer id) {
        return ResultFactory.buildSuccessResult(deptService.findById(id).orElse(null), "查询成功");
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation("根据id删除系部")
    public Result deleteById(@PathVariable Integer id) {
        if(deptService.deleteById(id)) {
            return ResultFactory.buildSuccessResult(null, "删除成功");
        }
        else {
            return ResultFactory.buildFailResult(null, "删除失败");
        }
    }
}
