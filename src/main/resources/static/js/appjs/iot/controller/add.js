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
		url : "/iot/controller/save",
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
			ip : {
				required : true,
				maxlength : 16
			},
			port : {
				required : true,
				digits : true,
				range : [1,9999999999]
			},
			name : {
				required : true,
				maxlength : 100
			},
			status : {
				required : true,
				range : [0,2]
			},
			deptName : {
				required : true,
				maxlength : 100
			}
		},
		messages : {
			ip : {
				required : icon + "请输入IP"
			},
			port : {
				required : icon + "请输入端口"
			},
			name : {
				required : icon + "请输入姓名"
			},
			status : {
				required : icon + "请选择状态"
			},
			deptName : {
				required : icon + "消防救援机构不能为空"
			}
		}
	})
}