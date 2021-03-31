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
	var controllerId = $('#controller option:selected').val()
	var str = {"controllerId":controllerId};
	$.ajax({
		type:'POST',
		cache : true,
		url:'/iot/controllerPort/list',
		dataType:'json',
		// contentType:'application/json;charset=UTF-8',
		data:JSON.stringify(str),
		headers:{
			'Content-Type':'application/json'
		},
		async : false,
		success:function (data){
			var controllerPort = $('#controllerPort');
			var options  = [];
			options.push('<option value="">','--请选择--','</option>')
			for(var i = 0;i < data.rows.length; i++ ){
				console.log(data.rows[i] + "============port")
				options.push('<option value="'+data.rows[i].id+'">',data.rows[i].channelNumber,'</option>');
			}
			controllerPort.append(options.join(''))
			parent.reLoad();
		}
	})
}