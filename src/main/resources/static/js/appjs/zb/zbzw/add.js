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
		url : "/zb/zbzw/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
                parent.parent.$("#exampleTable").bootstrapTable('destroy');
                parent.parent.getColumnsAndZwlx();
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
            zwmc : {
				required : true,
                maxlength:100
			},
            zwms : {
                required : true,
                maxlength:300
            },
            pxh : {
                required : true,
                digits:true,
                maxlength:11
            }
		},
		messages : {
            zwmc : {
				required : icon + "请输入职务名称"
			},
            zwms : {
                required : icon + "请输入职务描述"
            },
            pxh : {
                required : icon + "请输入排序号",
                digits : "请输入正整数格式"
            }
		}
	})
}