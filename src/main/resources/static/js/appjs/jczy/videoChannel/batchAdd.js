$().ready(function() {
	validateRule();
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
		url : "/jczy/videoChannel/save",
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
function selectDevices() {
	var xfjyjgTywysbm = $("#xfjyjgTywysbm").val();
		layer.open({
			type : 2,
			title : '选择设备',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '90%', '94%' ],
			content : '/jczy/videoChannel/selectDevices?xfjyjgTywysbm='+xfjyjgTywysbm
		});
}

