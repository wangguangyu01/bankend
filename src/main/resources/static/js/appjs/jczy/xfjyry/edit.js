$().ready(function() {
	validateRule();
    getSelectByType("CYZJLXDM","cyzjlxdm",$("#cyzjlxdm_val").val());  //常用证件类型
    getSelectByType("XBDM","xbdm",$("#xbdm_val").val());  //性别代码
    getSelectByType("MZDM","mzdm",$("#mzdm_val").val());  //民族代码
    getSelectByType("ZZMMDM","zzmmdm",$("#zzmmdm_val").val());  //政治面貌代码

    getSelectByType("HYZKDM","hyzkdm",$("#hyzkdm_val").val());  //婚姻状态
    getSelectByType("XFJYRYZTDM","xfjyryztdm",$("#xfjyryztdm_val").val());  //消防救援人员状态代码
    getSelectByType("XFJYRYZWQKDM","xfjyryzwqkdm",$("#xfjyryzwqkdm_val").val());  //消防救援人员在位情况代码

    getSelectAll("XLDM","XLDM-DIV","xldm","xldm-title");  //学历代码
    getSelectAll("XWDM","XWDM-DIV","xwdm","xwdm-title");  //学位代码
    getSelectAll("XFZJLYLBDM","XFZJLYLBDM-DIV","xfzjlylbdm","xfzjlylbdm-title");  //消防专家领域类别
    getSelectAll("XFJYRYLBDM","XFJYRYLBDM-DIV","xfjyrylbdm","xfjyrylbdm-title");  //消防救援人员类别
    getSelectAll("XFGWFLYDM","XFGWFLYDM-DIV","xfgwflydm","xfgwflydm-title");  //消防岗位分类与代码
    getSelectAll("ZYJSZWLBDM","ZYJSZWLBDM-DIV","zyjszwlbdm","zyjszwlbdm-title");  //专业技术职务类别
    getSelectAll("XFJYXJBDM","XFJYXJBDM-DIV","xfjyxjbdm","xfjyxjbdm-title");  //消防救援衔级别

    getSelectByType("XZQHDM","province",$("#provinceVal").val());  //区划代码（省）级联
    getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());  //区划代码（市）级联
    getSelectByValue($("#cityVal").val(),"jgdm",$("#jgdmVal").val());  //区划代码（区/县）级联

    getSelectByType("rygwlb_jh","rylbJh",$("#rylbJh_val").val());  //人员岗位简类

    $("#sfzjPdbz option[value='"+$("#sfzjPdbz_val").val()+"']").attr("selected","selected");  //是否专家
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/jczy/xfjyry/updateForm",
		data : $('#signupForm').serialize(),// 你的formid
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}

var openDept = function(objId){
    inpId = objId;
    layer.open({
        type:2,
        title:"选择消防救援机构",
        area : [ '300px', '450px' ],
        content:"/system/sysDept/treeView"
    })
}


function loadDept( deptId,deptName,xfjyjgTywysbm){
    //$("#deptId").val(deptId);
    $("#"+inpId).val(xfjyjgTywysbm);
    $("#"+inpId+"Name").val(deptName);
}