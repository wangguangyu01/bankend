$().ready(function() {
	validateRule();
    validateRule();
    getSelectAll("XFZBLXDM","XFZBLXDM-DIV","cllx","cllx-title");  //消防装备类型
    getSelectByType("s_f","sfzs",$("#sfzs_val").val());  //是否展示
    getSelectByType("s_f","sfcyjs",$("#sfcyjs_val").val());  //是否参与运算
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
		url : "/jczy/xfclSx/update",
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