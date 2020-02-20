package xyz.xhui.award.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;
import xyz.xhui.award.model.SysGrade;
import xyz.xhui.award.service.GradeService;

@RestController
@RequestMapping("/grade")
@Api(tags = "年级管理接口")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping(name = "")
    @ApiOperation("添加年级")
    public Result save(@RequestParam Integer grade) {
        SysGrade sysGrade = new SysGrade();
        sysGrade.setName(grade);
        return ResultFactory.buildSuccessResult(gradeService.save(sysGrade), "保存成功");
    }

    @GetMapping(name = "")
    @ApiOperation("查询所有年级")
    public Result findAll() {
        return ResultFactory.buildSuccessResult(gradeService.findAll(), "查询成功");
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("根据id查询年级")
    public Result findOne(@PathVariable Integer id) {
        return ResultFactory.buildSuccessResult(gradeService.findById(id).orElse(null), "查询成功");
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation("根据id删除年级")
    public Result deleteById(@PathVariable Integer id) {
        if(gradeService.deleteById(id)) {
            return ResultFactory.buildSuccessResult(null, "删除成功");
        }
        else {
            return ResultFactory.buildFailResult(null, "删除失败");
        }
    }
}
