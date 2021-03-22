package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.MD5Utils;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.jczy.service.XfjyryService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.domain.RoleDO;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.DeptService;
import com.smart119.system.service.RoleService;
import com.smart119.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 消防救援人员
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-15 10:53:18
 */

@Controller
@RequestMapping("/jczy/xfjyry")
public class XfjyryController extends BaseController {
    @Autowired
    private XfjyryService xfjyryService;
    @Autowired
    private DictService dictService;
    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping()
    @RequiresPermissions("jczy:xfjyry:xfjyry")
    String Xfjyry() {
        return "jczy/xfjyry/xfjyry";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jczy:xfjyry:xfjyry")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DeptDO> deptList = new ArrayList<>();
        if (params.get("deptId") != null && !params.get("deptId").equals("")) {
            deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
        } else {
            deptList = deptService.listChildren(getUser().getDeptId());
        }
        query.put("deptList", deptList);
        List<XfjyryDO> xfjyryList = xfjyryService.list(query);
        int total = xfjyryService.count(query);
        PageUtils pageUtils = new PageUtils(xfjyryList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("jczy:xfjyry:add")
    String add(Model model) {
        //查询现有角色
        List<RoleDO> roles = roleService.list();
        model.addAttribute("roles", roles);
        return "jczy/xfjyry/add";
    }

    @GetMapping("/edit/{xfjyryTywysbm}")
    @RequiresPermissions("jczy:xfjyry:edit")
    String edit(@PathVariable("xfjyryTywysbm") String xfjyryTywysbm, Model model) {
        XfjyryDO xfjyry = xfjyryService.get(xfjyryTywysbm);
        Map<String, Object> map = new HashMap<>();
        map.put("value", xfjyry.getXldm());
        map.put("description", "学历代码");
        model.addAttribute("xldm_name", dictService.findDictName(map)); //学历代码

        map.put("value", xfjyry.getXwdm());
        map.put("description", "学位代码");
        model.addAttribute("xwdm_name", dictService.findDictName(map)); //学位代码

        map.put("value", xfjyry.getXfzjlylbdm());
        map.put("description", "消防专家领域类别代码");
        model.addAttribute("xfzjlylbdm_name", dictService.findDictName(map)); //消防专家领域类别代码

        map.put("value", xfjyry.getXfjyrylbdm());
        map.put("description", "消防救援人员类别代码");
        model.addAttribute("xfjyrylbdm_name", dictService.findDictName(map)); //消防救援人员类别代码

        map.put("value", xfjyry.getXfgwflydm());
        map.put("description", "消防岗位分类与代码");
        model.addAttribute("xfgwflydm_name", dictService.findDictName(map)); //消防岗位分类与代码

        map.put("value", xfjyry.getZyjszwlbdm());
        map.put("description", "专业技术职务类别代码");
        model.addAttribute("zyjszwlbdm_name", dictService.findDictName(map)); //专业技术职务类别代码

        map.put("value", xfjyry.getXfjyxjbdm());
        map.put("description", "消防救援衔级别代码");
        model.addAttribute("xfjyxjbdm_name", dictService.findDictName(map)); //消防救援衔级别代码

        model.addAttribute("bzszjgTywysbm_name", deptService.findNameByTYWYSBM(xfjyry.getBzszjgTywysbm()));

        model.addAttribute("sjszjgTywysbm_name", deptService.findNameByTYWYSBM(xfjyry.getSjszjgTywysbm()));

        String city = dictService.findParentValue(xfjyry.getJgdm());
        String province = dictService.findParentValue(city);

        model.addAttribute("province", province);  //籍贯代码-省
        model.addAttribute("city", city);  //籍贯代码-市
        Map m = new HashMap();
        m.put("fid",xfjyry.getXfjyryTywysbm());
        m.put("fType","xfjyry");
        List<AttachmentDO> attachmentDOList = attachmentService.list(m);
        model.addAttribute("attachmentDOList", attachmentDOList);
        model.addAttribute("xfjyry", xfjyry);

        return "jczy/xfjyry/edit";
    }

    /**
     * @Description: APP
     * @Param: [xfjyryTywysbm, model]
     * @return: java.lang.String
     * @Author: yanyu
     * @Date: 2021/2/7
     */
    @GetMapping("/appryedit/{xfjyryTywysbm}")
    @RequiresPermissions("jczy:xfjyry:edit")
    @ResponseBody
    R appryedit(@PathVariable("xfjyryTywysbm") String xfjyryTywysbm) {
        XfjyryDO xfjyry = xfjyryService.get(xfjyryTywysbm);
        Map<String, Object> map = new HashMap<>();
        map.put("xuelivalue", xfjyry.getXldm());
        map.put("xuelidescription", "学历代码");
        map.put("xldm_name", dictService.findDictName(map)); //学历代码

        map.put("xwdm_value", xfjyry.getXwdm());
        map.put("xwdm_description", "学位代码");
        map.put("xwdm_name", dictService.findDictName(map)); //学位代码

        map.put("xfzjlylbdm_value", xfjyry.getXfzjlylbdm());
        map.put("xfzjlylbdm_description", "消防专家领域类别代码");
        map.put("xfzjlylbdm_name", dictService.findDictName(map)); //消防专家领域类别代码

        map.put("xfjyrylbdm_value", xfjyry.getXfjyrylbdm());
        map.put("xfjyrylbdm_description", "消防救援人员类别代码");
        map.put("xfjyrylbdm_name", dictService.findDictName(map)); //消防救援人员类别代码

        map.put("xfgwflydm_value", xfjyry.getXfgwflydm());
        map.put("xfgwflydm_description", "消防岗位分类与代码");
        map.put("xfgwflydm_name", dictService.findDictName(map)); //消防岗位分类与代码

        map.put("zyjszwlbdm_value", xfjyry.getZyjszwlbdm());
        map.put("zyjszwlbdm_description", "专业技术职务类别代码");
        map.put("zyjszwlbdm_name", dictService.findDictName(map)); //专业技术职务类别代码

        map.put("xfjyxjbdm_value", xfjyry.getXfjyxjbdm());
        map.put("xfjyxjbdm_description", "消防救援衔级别代码");
        map.put("xfjyxjbdm_name", dictService.findDictName(map)); //消防救援衔级别代码

        map.put("bzszjgTywysbm_name", deptService.findNameByTYWYSBM(xfjyry.getBzszjgTywysbm()));

        map.put("sjszjgTywysbm_name", deptService.findNameByTYWYSBM(xfjyry.getSjszjgTywysbm()));

        String city = dictService.findParentValue(xfjyry.getJgdm());
        String province = dictService.findParentValue(city);

        map.put("province", province);  //籍贯代码-省
        map.put("city", city);  //籍贯代码-市

        map.put("xfjyry", xfjyry);
        return R.ok(map);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/zdsave")
    @RequiresPermissions("jczy:xfjyry:add")
    public R zdsave(@RequestBody XfjyryDO xfjyry) {

        xfjyry.setCdate(new Date());
        xfjyry.setCperson(getUser().getUserId().toString());
        xfjyry.setStatus("0");
        if (xfjyryService.save(xfjyry) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/saveForm")
    @RequiresPermissions("jczy:xfjyry:add")
    public R saveForm(XfjyryDO xfjyry) {

        xfjyry.setCdate(new Date());
        xfjyry.setCperson(getUser().getUserId().toString());
        xfjyry.setStatus("0");
        if (xfjyryService.save(xfjyry) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("jczy:xfjyry:edit")
    public String update(@RequestPart(value = "file", required = false) MultipartFile[] files, XfjyryDO xfjyry) {
        if(files!=null && files.length>0) {
            attachmentService.ftpUpload(files, xfjyry.getXfjyryTywysbm(), "xfjyry");
        }
        xfjyryService.update(xfjyry);
        return xfjyry.getXfjyryTywysbm();
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("jczy:xfjyry:add")
    public String save(@RequestPart(value = "file", required = false) MultipartFile[] files, XfjyryDO xfjyry){
        String id = UUID.randomUUID().toString().replace("-", "");
        xfjyry.setXfjyryTywysbm(id);
        xfjyry.setCdate(new Date());
        xfjyry.setCperson(getUser().getUserId().toString());
        xfjyry.setStatus("0");
        if (files != null && files.length > 0) {
            attachmentService.ftpUpload(files, id, "xfjyry");
        }
        if (xfjyryService.save(xfjyry) > 0) {
            return id;
        }
        return "";
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/updateForm")
    @RequiresPermissions("jczy:xfjyry:edit")
    public R updateForm(XfjyryDO xfjyry) {
        if (xfjyryService.update(xfjyry) > 0) {
            //获取姓名
            String xm = xfjyry.getXm();
            //获取实际所在部门
            String sjszjgTywysbm = xfjyry.getSjszjgTywysbm();
            //获取移动联系电话
            String ydLxdh = xfjyry.getYdLxdh();
            //获取人员ID
            XfjyryDO xfjyryDO = xfjyryService.get(xfjyry.getXfjyryTywysbm());
            String userid = xfjyryDO.getUserid();
            //根据消防救援人员ID查出对应的系统用户
            UserDO userDO = userService.get(Long.parseLong(userid));
            userDO.setUsername(ydLxdh);
            userDO.setPassword(MD5Utils.encrypt(ydLxdh, ydLxdh));
            userDO.setName(xm);
            DeptDO deptDO = deptService.getDeptId(sjszjgTywysbm);
            userDO.setDeptId(deptDO.getDeptId());
            userDO.setXfjyjgTywysbm(sjszjgTywysbm);
            userDO.setMobile(ydLxdh);
            userService.update(userDO);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("jczy:xfjyry:remove")
    public R remove(String xfjyryTywysbm) {
        if (xfjyryService.remove(xfjyryTywysbm) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("jczy:xfjyry:batchRemove")
    public R remove(@RequestParam("ids[]") String[] xfjyryTywysbms) {
        xfjyryService.batchRemove(xfjyryTywysbms);
        return R.ok();
    }

    @GetMapping("/selectXfjyry")
    String selectXfjyry(String xfjyjgTywysbm, Model model) {
        model.addAttribute("xfjyjgTywysbm",xfjyjgTywysbm);
        return "jczy/xfjyry/selectXfjyry";
    }

    @ResponseBody
    @GetMapping("/selectXfjyryList")
    public PageUtils selectXfjyryList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DeptDO> deptList = new ArrayList<>();
        if (params.get("xfjyjgTywysbm") != null && !params.get("xfjyjgTywysbm").equals("")) {
            params.put("XFJYJG_TYWYSBM",params.get("xfjyjgTywysbm"));
            deptList = deptService.listChildren(deptService.list(params).get(0).getDeptId());
            query.put("deptList", deptList);
        }
        List<XfjyryDO> xfjyryList = xfjyryService.list(query);
        int total = xfjyryService.count(query);
        PageUtils pageUtils = new PageUtils(xfjyryList, total);
        return pageUtils;
    }

}
