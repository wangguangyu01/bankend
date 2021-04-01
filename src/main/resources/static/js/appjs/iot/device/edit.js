$().ready(function() {
	validateRule();
	changeControl();
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
		url : "/webapi/device/update",
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

function changeControl(){
	var controllerId = $('#controller option:selected').val();
	var controllerPortId = document.getElementById("controllerPortId").value;
	console.log(controllerPortId);
	$.ajax({
		type:'GET',
		cache : true,
		url:'/iot/controllerPort/listByControllerId',
		data: {
			"controllerId":controllerId
		},
		async : false,
		success:function (res){
			if (res.code == 0) {
				var data = res.data;
				var controllerPort = $('#controllerPort');
				controllerPort.find("option:selected").text("");
				controllerPort.empty();
				var options = [];
				for(var i = 0;i < data.length; i++ ){
					console.log(controllerPortId);
					console.log(data[i].channelNumber + ":" + (data[i].id == controllerPortId));
					if (data[i].id == controllerPortId) {
						options.push('<option ' + 'value="'+data[i].id + '"' + ' selected="selected"' + '>',data[i].channelNumber,'</option>');
					}else {
						options.push('<option ' + 'value="'+data[i].id + '"' + '>',data[i].channelNumber,'</option>');
					}
				}
				controllerPort.append(options.join(''))
				parent.reLoad();
			}else {
				layer.msg(res.msg);
			}

		}
	})
}