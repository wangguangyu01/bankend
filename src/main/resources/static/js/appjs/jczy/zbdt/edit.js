$().ready(function() {
	validateRule();

	// 获取行政区划
	getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());
	getSelectByValue($("#cityVal").val(),"xzqhdm",$("#xzqhdmVal").val());
	// 消防值班角色类别
	getSelectByType("XFZBJSLBDM","zbryXfzbjslbdm",zbryXfzbjslbdm);
	// 消防岗位分类
	getSelectAll("XFGWFLYDM","ZBRYXFGWFLYDM-DIV","zbryXfgwflydm","zbryXfgwflydm-title");
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
		url : "/jczy/zbdt/update",
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

//获取行政区划代码
function getXzqhdm(obj,objId){
	var id = $(obj).find("option:selected").attr("id");
	console.log(id);
	getSelectById(id,objId);
}

var openDept = function(){
	layer.open({
		type:2,
		title:"选择消防救援机构",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}

function loadDept( deptId,deptName,xfjyjgTywysbm){
	//$("#deptId").val(deptId);
	$("#xfjyjgTywysbm").val(xfjyjgTywysbm);
	$("#deptName").val(deptName);
}