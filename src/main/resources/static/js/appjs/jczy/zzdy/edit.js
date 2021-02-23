$().ready(function() {
	/*getSelectByType("ZZDYLXDM","zzdylxdm",zzdylxdm);*/
	  getSelectAll("ZZDYLXDM","ZZDYLXDM-DIV","zzdylxdm","zzdylxdm-title");
	  getzzdyxfcl();
	validateRule();
	localStorage.setItem("remembermeDataList",JSON.stringify(xfclnameList))
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
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
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/webapi/zzdy/update",
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
			//content : '/webapi/zzdy/addxfcl/' + zzdyTywybs // iframe的url
			content : '/webapi/zzdy/addxfcl?zzdyTywybs='+zzdyTywybs+'&xfjyjgTywysbm='+zzdy.xfjyjgTywysbm
		});
	}
}
function getzzdyxfcl(){
	$.each(xfclnameList, function(i, row) {
		if (i != 0 && (i + 1) % 4 == 0) {
			$('#addxfcl').append("<span  style='line-height:30px;' id='zzdy"+i+"' name='zzdy"+i+"' class='label label-info'>"+row['xfclname']+"</span>&nbsp;</br>")
		}else{
			$('#addxfcl').append("<span style='line-height:30px;' id='zzdy"+i+"' name='zzdy"+i+"' class='label label-info'>"+row['xfclname']+"</span>&nbsp;")
		}
	});
}