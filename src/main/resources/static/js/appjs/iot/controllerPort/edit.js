$().ready(function() {
	validateRule();
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
		url : "/iot/controllerPort/update",
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
			channelNumber : {
				required : true,
				maxlength : 100
			},
			address : {
				required : true,
				maxlength : 100
			},
			portAddress : {
				required : true,
				maxlength : 100
			},
			controllerId : {
				required : true,
				maxlength : 32
			},
			restorationTime : {
				required : true,
				range : [1,99999999999999999999]
			},
			status : {
				required : true,
				range : [0,2]
			}
		},
		messages : {
			channelNumber : {
				required : icon + "请输入通道号"
			},
			address : {
				required : icon + "请输入寄存器地址"
			},
			portAddress : {
				required : icon + "请输入端口地址"
			},
			controllerId : {
				required : icon + "请选择中控器"
			},
			restorationTime : {
				required : icon + "请输入复位时间"
			},
			status : {
				required : icon + "请选择状态"
			}
		}
	})
}