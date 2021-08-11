$().ready(function() {
	validateRule();
	//getSelectByType("ZZDYLXDM","zzdylxdm",null);
	getSelectAll("ZZDYLXDM","ZZDYLXDM-DIV","zzdylxdm","zzdylxdm-title");  //消防岗位分类与代码
	localStorage.setItem("remembermeDataList","")
	localStorage.setItem("xfzbRemembermeDataList","")
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
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
function save() {
	var car=$('#xfclTywysbm').val();
	if(car.length==0){
		layer.msg("请选择需要添加的消防车辆数据");
		  return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/webapi/zzdy/save",
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
			zzdymc : {
				required : true,
                maxlength:50
			},
			zzdylxdm:{
				required : true
			},
            zzrw:{
                required : true,
                maxlength:300
            }
		},
		messages : {
			zzdymc : {
				required : "请输入作战单元名称"
			},
			zzdylxdm : {
				required : "请选择作战单元类型"
			},
            zzrw : {
                required : "请输入作战任务"
            }
		}
	})
}
function add() {
	var zzjg=$('#xfjyjgTywysbm').val();
	if(zzjg.length ==0){
		parent.layer.alert("请选择消防救援机构")
        return false;
	}else{
		layer.open({
			type : 2,
			title : '选择消防车辆',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '90%', '94%' ],
			content : '/webapi/zzdy/addxfcl?zzdyTywybs=111&xfjyjgTywysbm='+map.xfjyjgTywysbm
		});
	}
}

function addZbqc() {
	var zzjg=$('#xfjyjgTywysbm').val();
	if(zzjg.length ==0){
		parent.layer.alert("请选择消防救援机构")
		return false;
	}else{
		layer.open({
			type : 2,
			title : '选择装备器材',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '90%', '94%' ],
			content : '/webapi/zzdy/addzbqc?zzdyTywybs=111&xfjyjgTywysbm='+map.xfjyjgTywysbm
		});
	}
}