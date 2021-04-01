$().ready(function() {
	validateRule();
	changeControl();
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
		url : "/iot/device/save",
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

function changeControl(){
	var controllerId = $('#controller option:selected').val()
	$.ajax({
		type:'GET',
		cache : true,
		url:'/iot/controllerPort/listByControllerId',
		data: {
			"controllerId":controllerId
		},
		async : false,
		success:function (res){
			if (res.code == 0){
				var data = res.data;
				console.log(data);
				var controllerPort = $('#controllerPort');
				controllerPort.find("option:selected").text("");
				controllerPort.empty();
				var options = [];
				for(var i = 0;i < data.length; i++ ){
					options.push('<option value="'+data[i].id+'">',data[i].channelNumber,'</option>');
				}
				controllerPort.append(options.join(''))
				parent.reLoad();
			}else {
				layer.msg(res.msg);
			}

		}
	})
}