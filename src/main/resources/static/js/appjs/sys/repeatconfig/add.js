$().ready(function() {
	validateRule();
	$('#thresholdType').val("1");
	$(".areaRange").hide();

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
		url : "/system/config/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
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
			paramKey : {
				required : true
			},
			paramName : {
				required : true
			},
			paramValue : {
				required : true
			}
		},
		messages : {
			paramKey : {
				required : icon + "参数key必填",
			},
			paramName : {
				required : icon + "参数paramName必填",
			},
			paramValue : {
				required : icon + "参数paramValue必填",
			}
		}
	})
}



