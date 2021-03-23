package com.smart119.common.controller;

import com.smart119.jczy.dao.BrqyDao;
import com.smart119.jczy.domain.BrqyDO;
import com.smart119.jczy.service.BrqyService;
import com.smart119.system.domain.DeptDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.smart119.system.service.DeptService;
@Controller
public class MapController {
    @Autowired
    private  DeptService deptService;

    @Autowired
    private BrqyService brqyService;

    //坐标点拾取地图
    @GetMapping("/common/map")
    String map(HttpServletRequest request, Model model) {
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        model.addAttribute("lat",lat);
        model.addAttribute("lng",lng);
        return "common/map/map";
    }


    //消防救援机构管辖范围维护地图
    @GetMapping("/common/map2")
    String map2(HttpServletRequest request, Model model) {
        String pointsArr = request.getParameter("pointsArr");
        String deptId = request.getParameter("deptId");
        model.addAttribute("points",pointsArr);
        Map<String,Object> params  = new HashMap<>();
        params.put("status","0");
        List<DeptDO> deptDOS = deptService.list(params);
        if(deptId!=null && !deptId.equals("")){
            deptDOS = deptDOS.parallelStream().filter(t->t.getXfjyjgxzdm()!=null && t.getXfjyjgxzdm().startsWith("90") && t.getZbfw()!=null && t.getDeptId() != Long.parseLong(deptId) ).collect(Collectors.toList());
        }else{
            deptDOS = deptDOS.parallelStream().filter(t->t.getXfjyjgxzdm()!=null && t.getXfjyjgxzdm().startsWith("90") && t.getZbfw()!=null).collect(Collectors.toList());
        }
        model.addAttribute("deptList",deptDOS);
        return "common/map/map2";
    }


    //避让区域维护地图
    @GetMapping("/common/map3")
    String map3(HttpServletRequest request, Model model) {
        String pointsArr = request.getParameter("pointsArr");   //编辑的时候选择编辑的当前避让区域
        String brqyId = request.getParameter("brqyId");
        model.addAttribute("points",pointsArr);    //编辑时 本避让区域
        Map<String,Object> params  = new HashMap<>();
        params.put("status","0");
        List<BrqyDO> brqyDOS = brqyService.list(params);  //获得所有的避让区域
        if(brqyId!=null && !brqyId.equals("")){
            brqyDOS = brqyDOS.parallelStream().filter(t->t.getCoordinatesGaode()!=null && !brqyId.equals(t.getId())  ).collect(Collectors.toList());
        }else{
            brqyDOS = brqyDOS.parallelStream().filter(t->t.getCoordinatesGaode()!=null).collect(Collectors.toList());
        }
        model.addAttribute("brqyList",brqyDOS);
        return "common/map/map3";
    }
}
