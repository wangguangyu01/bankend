$().ready(function() {
	validateRule();
	if($("#type").val()!=null && $("#type").val()!=""){
        $("#type").attr("readonly","readonly").css("background-color","#eee").css("opacity","1");
	}
    if($("#description").val()!=null && $("#description").val()!=""){
        $("#description").attr("readonly","readonly").css("background-color","#eee").css("opacity","1");
    }
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	if($("#parentId").val()==null || $("#parentId").val()==undefined || $("#parentId").val()==''){
        $("#parentId").val("0");
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/common/dict/save",
		data : $('#signupForm').serialize(), // 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("网络超时");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad111();
				var index = parent.layer.getFrameIndex(window.name);
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