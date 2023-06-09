var index = 0;
var zzdyindex = 0;
$().ready(function() {
    validateRule();
    getSelectAll("JQFLYDM","JQLB-DIV","jqlb","jqlb-title");
    getSelectAll("XFZBLXDM","CLLXDM-DIV","cllxdm","cllxdm-title");
    getSelectByType("JQDJDM","JQDJDM",levelPlan.planLevel);
    getSelectByType("ZHCS","ZHCS",levelPlan.zhcs);
    for(var i in levelPlan.policeTypeLevelList){
        initCar(levelPlan.policeTypeLevelList[i])
	}
    for(var i in levelPlan.zzdyTypeLevelList){
        initZzdy(levelPlan.zzdyTypeLevelList[i])
    }
    if(levelPlan.yalx=='0'){
        $("#carDiv").show();
        $("#zzdyDiv").hide();
        $("#zzdylist").empty();
    }else{
        $("#zzdyDiv").show();
        $("#carDiv").hide();
        $("#carlist").empty();
    }
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
function removeCar(element) {
    $(element).parent().parent().parent().parent().remove();
}

function initCar(policeTypeLeve) {
    index++;
    var htmlStr = "";
    htmlStr+='<div class="row">'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+='<label class="col-sm-4 control-label">车辆类型：</label>'
    htmlStr+= '<div class="col-sm-8">'
    htmlStr+='<div class="CLLXDM-DIV'+index+' dropdown">'
    htmlStr+='<a  role="button" data-toggle="dropdown" class="btn btn-white"  style="width: 100%;text-align: left;">'
    htmlStr+='<span id="cllxdm-title'+index+'">'+policeTypeLeve.xfcltywysbmname+'</span>'
    htmlStr+='<span class="caret pull-right" style="position: relative;top: 10px;"></span>'
    htmlStr+='</a>'
    htmlStr+='</div>'
    htmlStr+='<input type="hidden" name="cllxdm" id="cllxdm'+index+'"  value="'+policeTypeLeve.xfcltywysbm+'"  title="请选择消防装备器材分类" required/>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+= '<label class="col-sm-4 control-label">数量：</label>'
    htmlStr+=' <div class="col-sm-8" style="position: relative">'
    htmlStr+=' <input  name="num" id="num'+index+'" value="'+policeTypeLeve.xfclnum+'" class="form-control" type="text">'
    htmlStr+='<button type="button" class="btn btn-danger" onclick="removeCar(this)" style="position: absolute;top:0;right: -80px;">删除</button>'
    htmlStr+= '</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    $("#carlist").append(htmlStr);
    getSelectAllCallBack("XFZBLXDM","CLLXDM-DIV"+index,"cllxdm"+index,"cllxdm-title"+index,function () {
    });
}

function initZzdy(zzdyTypeLeve) {
    zzdyindex++;
    var htmlStr = "";
    htmlStr+='<div class="row">'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+='<label class="col-sm-4 control-label">单元类型：</label>'
    htmlStr+= '<div class="col-sm-8">'
    htmlStr+='<div class="ZZDYLXDM-DIV'+zzdyindex+' dropdown">'
    htmlStr+='<a  role="button" data-toggle="dropdown" class="btn btn-white"  style="width: 100%;text-align: left;">'
    htmlStr+='<span id="zzdylxdm-title'+zzdyindex+'">'+zzdyTypeLeve.zzdylxdmName+'</span>'
    htmlStr+='<span class="caret pull-right" style="position: relative;top: 10px;"></span>'
    htmlStr+='</a>'
    htmlStr+='</div>'
    htmlStr+='<input type="hidden" name="zzdylxdm" id="zzdylxdm'+zzdyindex+'"  value="'+zzdyTypeLeve.zzdylxdm+'"  title="请选择作战单元类型" required/>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+= '<label class="col-sm-4 control-label">数量：</label>'
    htmlStr+=' <div class="col-sm-8" style="position: relative">'
    htmlStr+=' <input  name="zzdy_num" id="zzdy_num'+zzdyindex+'" value="'+zzdyTypeLeve.zzdyNum+'" class="form-control" type="text">'
    htmlStr+='<button type="button" class="btn btn-danger" onclick="removeCar(this)" style="position: absolute;top:0;right: -80px;">删除</button>'
    htmlStr+= '</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    $("#zzdylist").append(htmlStr);
    getSelectAllCallBack1("ZZDYLXDM","ZZDYLXDM-DIV"+zzdyindex,"zzdylxdm"+zzdyindex,"zzdylxdm-title"+zzdyindex,function () {
    });
}

function addCar() {
    index++;
    var htmlStr = "";
    htmlStr+='<div class="row">'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+='<label class="col-sm-4 control-label">车辆类型：</label>'
    htmlStr+= '<div class="col-sm-8">'
    htmlStr+='<div class="CLLXDM-DIV'+index+' dropdown">'
    htmlStr+='<a  role="button" data-toggle="dropdown" class="btn btn-white"  style="width: 100%;text-align: left;">'
    htmlStr+='<span id="cllxdm-title'+index+'">--请选择--</span>'
    htmlStr+='<span class="caret pull-right" style="position: relative;top: 10px;"></span>'
    htmlStr+='</a>'
    htmlStr+='</div>'
    htmlStr+='<input type="hidden" name="cllxdm" id="cllxdm'+index+'"  value=""  title="请选择消防装备器材分类" required/>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+= '<label class="col-sm-4 control-label">数量：</label>'
    htmlStr+=' <div class="col-sm-8" style="position: relative">'
    htmlStr+=' <input  name="num" id="num'+index+'" class="form-control" type="text">'
    htmlStr+='<button type="button" class="btn btn-danger" onclick="removeCar(this)" style="position: absolute;top:0;right: -80px;">删除</button>'
    htmlStr+= '</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    $("#carlist").append(htmlStr);
    getSelectAllCallBack("XFZBLXDM","CLLXDM-DIV"+index,"cllxdm"+index,"cllxdm-title"+index,function () {
    });
}
function save() {
    if($("#jqlb").val()==''){
        layer.msg("请选择警情分类");
        return;
    }
    var yalx = $("input[name='YALX']:checked").val();
    if (yalx == '0') {
        var len = $("[name='cllxdm']").length;
        if(len == 0){
            layer.msg("请添加车辆信息.");
            return;
        }
        var flag = true;
        $("[name='cllxdm']").each(function (i) {
            if($(this).val()==''){
                flag = false;
            }
        })
        $("[name='num']").each(function (i) {
            if($(this).val()==''){
                flag = false;
            }
        })
        if(!flag){
            layer.msg("请补全相关数据.");
            return;
        }
        var flag1=false;
        $("[name='cllxdm']").each(function (i) {
            var t1 = $(this).val();
            $("[name='cllxdm']").each(function (j) {
                var t2 = $(this).val();
                if(i!=j && t1==t2){
                    flag1=true;
                }
            })
        })
        if(flag1){
            layer.msg("车辆类型重复！");
            return;
        }
    }else{
        var len = $("[name='zzdylxdm']").length;
        if(len == 0){
            layer.msg("请添加作战单元信息.");
            return;
        }
        var flag = true;
        $("[name='zzdylxdm']").each(function (i) {
            if($(this).val()==''){
                flag = false;
            }
        })
        $("[name='zzdy_num']").each(function (i) {
            if($(this).val()==''){
                flag = false;
            }
        })
        if(!flag){
            layer.msg("请补全相关数据.");
            return;
        }

        var flag1=false;
        $("[name='zzdylxdm']").each(function (i) {
            var t1 = $(this).val();
            $("[name='zzdylxdm']").each(function (j) {
                var t2 = $(this).val();
                if(i!=j && t1==t2){
                    flag1=true;
                }
            })
        })
        if(flag1){
            layer.msg("单元类型重复！");
            return;
        }

    }

    var levelPlan = {};
    var policeTypeLevelList = [];
    var zzdyTypeLevelList = [];
    $("[name='cllxdm']").each(function (i) {
        policeTypeLevelList.push({
            pOLICESTAIONTYPETYWYSBM:$("#jqlb").val(),
            pOLICESTAIONLEVELTYWYSBM:$("#JQDJDM").val(),
            zhcs: $("#ZHCS").val(),
            xFCLTYWYSBM:$(this).val(),
            xFCLNUM: $($("[name='num']")[i]).val()
        })
    })
    $("[name='zzdylxdm']").each(function (i) {
        zzdyTypeLevelList.push({
            policestaionTypeTywysbm:$("#jqlb").val(),
            policestaionLevelTywysbm:$("#JQDJDM").val(),
            zzdylxdm:$(this).val(),
            zzdyNum: $($("[name='zzdy_num']")[i]).val(),
            zhcs: $("#ZHCS").val()
        })
    })
    levelPlan.levelPlanId = $("#levelPlanId").val();
    levelPlan.planName = $("#planName").val();
    levelPlan.planContent = $("#planContent").val();
    levelPlan.yalx = yalx;
    levelPlan.policeTypeLevelList = policeTypeLevelList;
    levelPlan.zzdyTypeLevelList = zzdyTypeLevelList;
    $.ajax({
        cache : true,
        type : "POST",
        url : "/system/levelPlan/save",
        headers:{'Content-Type':'application/json;charset=utf8'},
        data: JSON.stringify(levelPlan),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            planName : {
                required : true
            },
            JQDJDM : {
                required : true
            },

        },
        messages : {
            planName : {
                required : icon + "请输入预案名称"
            },
            JQDJDM : {
                required : icon + "请选择警情等级"
            },
        }
    })
}

function yalxCheck() {
    var yalx = $("input[name='YALX']:checked").val();
    if(yalx=='0'){
        $("#carDiv").show();
        $("#zzdyDiv").hide();
        $("#zzdylist").empty();
    }else{
        $("#zzdyDiv").show();
        $("#carDiv").hide();
        $("#carlist").empty();
    }
}


function addZzdy() {
    zzdyindex++;
    var htmlStr = "";
    htmlStr+='<div class="row">'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+='<label class="col-sm-4 control-label">单元类型：</label>'
    htmlStr+= '<div class="col-sm-8">'
    htmlStr+='<div class="ZZDYLXDM-DIV'+zzdyindex+' dropdown">'
    htmlStr+='<a  role="button" data-toggle="dropdown" class="btn btn-white"  style="width: 100%;text-align: left;">'
    htmlStr+='<span id="zzdylxdm-title'+zzdyindex+'">--请选择--</span>'
    htmlStr+='<span class="caret pull-right" style="position: relative;top: 10px;"></span>'
    htmlStr+='</a>'
    htmlStr+='</div>'
    htmlStr+='<input type="hidden" name="zzdylxdm" id="zzdylxdm'+zzdyindex+'"  value=""  title="请选择作战单元类型" required/>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='<div class="col-md-6">'
    htmlStr+='<div class="form-group">'
    htmlStr+= '<label class="col-sm-4 control-label">数量：</label>'
    htmlStr+=' <div class="col-sm-8" style="position: relative">'
    htmlStr+=' <input  name="zzdy_num" id="zzdy_num'+zzdyindex+'" class="form-control" type="text">'
    htmlStr+='<button type="button" class="btn btn-danger" onclick="removeCar(this)" style="position: absolute;top:0;right: -80px;">删除</button>'
    htmlStr+= '</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    htmlStr+='</div>'
    $("#zzdylist").append(htmlStr);
    getSelectAllCallBack1("ZZDYLXDM","ZZDYLXDM-DIV"+zzdyindex,"zzdylxdm"+zzdyindex,"zzdylxdm-title"+zzdyindex,function () {
    });
}