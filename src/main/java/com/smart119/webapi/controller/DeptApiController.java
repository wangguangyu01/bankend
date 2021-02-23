package com.smart119.webapi.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.Tree;
import com.smart119.common.utils.R;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : DeptApiController
 * @Description : 组织机构api
 * @Author : Liangsl
 * @Date: 2021-01-30 15:52
 */
@RestController
@Api(value = "（所有端）组织机构树形结构API", description = "（所有端）组织机构树形结构API")
@RequestMapping("/webapi/dept")
public class DeptApiController extends BaseController{
    @Autowired
    private DeptService sysDeptService;

    @ApiOperation("查询组织机构树形结构")
    @GetMapping("/tree")
    public Tree<DeptDO> tree() {
        Tree<DeptDO> tree = new Tree<DeptDO>();
        tree = sysDeptService.getTree(getUser().getDeptId());
        return tree;
    }

    @ApiOperation("查询支队和大队")
    @GetMapping("/getXfjyjgZdAndDd")
    public R getXfjyjgZdAndDd() {
        List<DeptDO> list = sysDeptService.getXfjyjgZdAndDd();
        return R.ok(list);
    }

}
