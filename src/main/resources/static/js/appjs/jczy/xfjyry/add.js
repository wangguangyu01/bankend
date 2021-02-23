var inpId = "";

$().ready(function() {
	validateRule();
    getSelectByType("CYZJLXDM","cyzjlxdm",null);  //常用证件类型
    getSelectByType("XBDM","xbdm",null);  //性别代码
    getSelectByType("MZDM","mzdm",null);  //民族代码
    getSelectByType("ZZMMDM","zzmmdm",null);  //政治面貌代码
    getSelectByType("XZQHDM","province",null);  //区划代码（省）级联
    getSelectAll("XLDM","XLDM-DIV","xldm","xldm-title");  //学历代码
    getSelectAll("XWDM","XWDM-DIV","xwdm","xwdm-title");  //学位代码
    getSelectAll("XFZJLYLBDM","XFZJLYLBDM-DIV","xfzjlylbdm","xfzjlylbdm-title");  //消防专家领域类别
    getSelectByType("HYZKDM","hyzkdm",null);  //婚姻状态
    getSelectAll("XFJYRYLBDM","XFJYRYLBDM-DIV","xfjyrylbdm","xfjyrylbdm-title");  //消防救援人员类别
    getSelectByType("XFJYRYZTDM","xfjyryztdm",null);  //消防救援人员状态代码
    getSelectByType("XFJYRYZWQKDM","xfjyryzwqkdm",null);  //消防救援人员在位情况代码
    getSelectAll("XFGWFLYDM","XFGWFLYDM-DIV","xfgwflydm","xfgwflydm-title");  //消防岗位分类与代码
    getSelectAll("ZYJSZWLBDM","ZYJSZWLBDM-DIV","zyjszwlbdm","zyjszwlbdm-title");  //专业技术职务类别
    getSelectAll("XFJYXJBDM","XFJYXJBDM-DIV","xfjyxjbdm","xfjyxjbdm-title");  //消防救援衔级别
    getSelectByType("rygwlb_jh","rylbJh",null);  //人员岗位简类

});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/jczy/xfjyry/saveForm",
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
				required : icon + "请输入姓名"
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

function getXzqhdm(obj,objId){
    var id = $(obj).find("option:selected").attr("id");
    console.log(id);
    getSelectById(id,objId);
}


$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
};