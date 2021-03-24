package com.smart119.jqxx.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class JqcjdpDzVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /***************************调派队站表信息*********************************/

    /**
     * 调派队站唯一识别码
     */
    private String dpdzTywysbm;

    /**
     * 出动单号
     */
    private String cddh;

    /**
     * 出动时间
     */
    private Date cdsj;


    /**
     * 出动状态（0未出动 1已出动）
     */
    private String cdzt;

    /**
     * 调派队站 - 队站名称
     */
    private String dpdzName;

    /**********************调派信息表************************/

    /**
     *调派类型 - 代码
     */
    private String jqcjdpDplx;

    /**
     *调派类型 - 标签
     */
    private String jqcjdpDplxLabel;

    /**********************警情基本信息表信息*******************/


    /**
     * 地点名称
     */
    private String ddmc;

    /**
     * 警情等级 - 代码
     */
    private String jqdjdm;

    /**
     * 警情等级 - 标签
     */
    private String jqdjLabel;

    /**
     * 警情状态类别 - 代码
     */
    private String jqztLbdm;
    /**
     * 警情状态类别 - 标签
     */
    private String jqztLabel;

    /**
     * 警情编号
     */
    private String jqbh;

    /**
     * 警情标识 - 编码
     */
    private String jqbslbdm;

    /**
     * 警情标识 - 标签
     */
    private String jqbsLabel;

    /**
     * 报警方式 - 编码
     */
    private String bjfslbdm;

    /**
     * 报警方式 - 标签
     */
    private String bjfsLabel;

    //TODO 命令到达时间

    /**
     * 报警电话
     */
    private String bjdh;

    /**
     * 报警时间
     */
    private Date bjsj;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 警情分类与代码
     */
    private String jqflydm;

    /**
     * 警情分类 - 标签
     */
    private String jqflLabel;

    /**
     * 燃烧物代码
     */
    private String rswdm;

    /**
     * 燃烧物 - 标签
     */
    private String rswLabel;


    /**
     * 灾害场所 - 代码
     */
    private String zhcsdm;

    /**
     * 灾害场所 - 标签
     */
    private String zhcsLabel;

    /**
     * 警情对象 - 名称
     */
    private String jqdxMc;

    /**
     * 就近队站（消防救援机构_通用唯一识别码）
     */
    private String jjdz;
    /**
     * 就近队站 名称
     */
    private String jjdzName;

    /**
     * 辖区队站 名称
     */
    private String xqdzName;

    /**
     * 警情简要情况
     */
    private String jqJyqk;


    /**********************接警记录基本信息*******************/

    /**
     * 接警员 - 通用唯一识别码
     */
    private String jieJingYuanTywysbm;

    /**
     * 接警员 - 姓名
     */
    private String jieJingYuanName;





    /*********工具集合************************/
    private List<Object> dataList;
    private List<String> titleList;
    public List<Object> getDataList(){
        if(!ObjectUtils.isEmpty(dataList)){
            return dataList;
        }

        initToolList();

        return dataList;

    }

    public List<String> getTitleList(){
        if(!ObjectUtils.isEmpty(titleList)){
            return titleList;
        }

        initToolList();

        return titleList;
    }

    private void initToolList(){

        titleList = new ArrayList<>();
        dataList = new ArrayList<>();

        titleList.add("调派队站");
        dataList.add(dpdzName);

        titleList.add("出动单号");
        dataList.add(cddh);

        titleList.add("出动状态");
        dataList.add(StringUtils.equals(cdzt,"0")?"未出动":StringUtils.equals(cdzt,"1")?"已出动":"");

        titleList.add("出动时间");
        dataList.add(cdsj!=null?DateFormatUtils.format(cdsj,"yyyy-MM-dd HH:mm:ss"):"");

        titleList.add("调派类型");
        dataList.add(jqcjdpDplxLabel);

        titleList.add("地点名称");
        dataList.add(ddmc);

        titleList.add("警情等级");
        dataList.add(jqdjLabel);

        titleList.add("警情状态类别");
        dataList.add(jqztLabel);

        titleList.add("警情编号");
        dataList.add(jqbh);

        titleList.add("警情标识");
        dataList.add(jqbsLabel);

        titleList.add("报警方式");
        dataList.add(bjfsLabel);

        titleList.add("报警电话");
        dataList.add(bjdh);

        titleList.add("报警时间");
        dataList.add(bjsj!=null?DateFormatUtils.format(bjsj,"yyyy-MM-dd HH:mm:ss"):"");

        titleList.add("联系电话");
        dataList.add(lxdh);

        titleList.add("警情分类");
        dataList.add(jqflLabel);

        titleList.add("燃烧物");
        dataList.add(rswLabel);

        titleList.add("灾害场所");
        dataList.add(zhcsLabel);

        titleList.add("警情对象");
        dataList.add(jqdxMc);

        titleList.add("就近队站");
        dataList.add(jjdzName);

        titleList.add("辖区队站");
        dataList.add(xqdzName);

        titleList.add("警情简要情况");
        dataList.add(jqJyqk);

        titleList.add("接警员");
        dataList.add(jieJingYuanName);


    }

}
