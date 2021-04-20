$().ready(function() {
	validateRule();
	getSelectById(5108,"city",null);
	getSelectByType("JJSYZLXDM","jjsyzlxdm",null);
	getSelectByType("DWHZWHXFLYDM","dwhzwhxflydm",null);
	getSelectByType("DWZRXZDM","dwzrxzdm",null);
	getSelectByType("HXPZTLBDM","wxhxphxpztlbdm",null); //危险化学品状态类别
	getSelectByType("WXHXPFLYDM","wxhxpwxhxpflydm",null); //危险化学品分类
	getSelectByType("HXPWXXLBDM","wxhxphxpwxxlbdm",null); //化学品危险性分类


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
		url : "/jczy/zddw/save",
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
            dwmc : {
				required : true,
                maxlength:100
			},
            dwpyjc : {
                required : true,
                maxlength:100
            },
            dzmc : {
                required : true,
                maxlength:100
            }
		},
		messages : {
            dwmc : {
				required :  "请输入重点单位名称"
			},
            dwpyjc : {
                required :  "请输入单位拼音简称"
            },
            dzmc : {
                required :  "请输入单位地址"
            }
		}
	})
}

var openMap = function(){
	var lng = $("#dqjd").val();
	var lat = $("#dqwd").val();
	layer.open({
		type:2,
		title:"选择坐标点",
		area : [ '800px', '600px' ],
		content:"/common/map?lng="+lng+"&lat="+lat
	})
}

function saveMarker(lng,lat){
	$("#dqjd").val(lng);
	$("#dqwd").val(lat);
}

function getXzqhdm(obj,objId){
	var id = $(obj).find("option:selected").attr("id");
	getSelectById(id,objId);
}

function loadDept( deptId,deptName,xfjyjgTywysbm){
	$("#xfjyjgTywysbm").val(xfjyjgTywysbm);
	$("#deptName").val(deptName);
}

var openDept = function(){
	layer.open({
		type:2,
		title:"选择消防救援机构",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}