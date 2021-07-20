$().ready(function() {
	validateRule();
    getSelectByType("JJYYLX",  "type",   $("#type_val").val());
	getSelectByType("JQFLYDM","jqfldm", $("#jqfldm_val").val());
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
		url : "/jczy/jjyy/update",
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
                required : true,
                maxlength : 200
            },
            remarks : {
                required : true,
                maxlength : 300
            },
		},
		messages : {
            name : {
                required :  "请输入警情用语名称"
            },
            remarks : {
                required :  "请输入备注"
            },
		}
	})
}
