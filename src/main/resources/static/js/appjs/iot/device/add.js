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
	var str = {"controllerId":controllerId};
	$.ajax({
		type:'POST',
		cache : true,
		url:'/iot/controllerPort/list',
		dataType:'json',
		data:JSON.stringify(str),
		headers:{
			'Content-Type':'application/json'
		},
		async : false,
		success:function (data){
			console.log(data,'=====res')
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