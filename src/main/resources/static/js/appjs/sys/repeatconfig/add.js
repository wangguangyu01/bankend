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
		url : "/system/thresholdConfig/save",
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
			thresholdType : {
				required : true
			}
		},
		messages : {
			thresholdType : {
				required : icon + "请选择阈值类型",
			}
		}
	})
}


function changeRepeatType() {
	var repeatType = $('#thresholdType').val();
	if (repeatType === '1') {
		$(".areaRange").hide();
	} else {
		$(".areaRange").show();
	}
}
