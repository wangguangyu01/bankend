$().ready(function() {
	validateRule();
	$("input[name='type']").click(function(){
		if("0" == $('input:radio[name="type"]:checked').val()){
			$('#token').attr('disabled',"true");
			$('#token').rules('remove','required');
		}else{
			$('#token').removeAttr("disabled");
			$('#token').rules('add',{
				required : true
			});
		}
	})
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
		url : "/sys/appInfo/save",
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
			},
			type : {
				required : true
			},
			appKey : {
				required : true
			},
			appSecret : {
				required : true
			},
			status : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入应用名称"
			},
			type : {
				required : icon + "请选择应用类型"
			},
			appKey : {
				required : icon + "请输入应用key"
			},
			appSecret : {
				required : icon + "请输入用秘钥"
			},
			status : {
				required : icon + "请选择状态"
			}
		}
	})
}