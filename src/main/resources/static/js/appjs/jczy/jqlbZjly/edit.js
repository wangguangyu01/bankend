$().ready(function() {
	validateRule();

    getSelectByZjly("zjlylbA",$("#zjlylbA_val").val());
    getSelectByZjly("zjlylbB",$("#zjlylbB_val").val());
    getSelectByZjly("zjlylbC",$("#zjlylbC_val").val());
    getSelectByZjly("zjlylbD",$("#zjlylbD_val").val());
    getSelectByZjly("zjlylbE",$("#zjlylbE_val").val());
    getSelectByZjly("zjlylbF",$("#zjlylbF_val").val());
    getSelectByZjly("zjlylbG",$("#zjlylbG_val").val());
    getSelectByZjly("zjlylbH",$("#zjlylbH_val").val());
    getSelectByZjly("zjlylbI",$("#zjlylbI_val").val());
    getSelectByZjly("zjlylbJ",$("#zjlylbJ_val").val());
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
		url : "/jczy/jqlbZjly/update",
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

function getSelectByZjly(objId,selected){
    $.ajax({
        url : "/jczy/jqlbZjly/getZjlyType",
        type : "get",
        data : $('#signupForm').serialize(),
        success : function(list) {

            $("#"+objId).empty();
            $("#"+objId).append('<option value="" >--请选择--</option>');
            if(list!=null && list!=undefined && list.data.length>0){
                $.each(list.data,function(i,item){

                    if(selected!=null && selected==item.value){
                        $("#"+objId).append('<option id="'+item.value+'" value="'+item.value+'" selected="selected">'+item.name+'</option>');
                    }else{
                        $("#"+objId).append('<option id="'+item.value+'" value="'+item.value+'">'+item.name+'</option>');
                    }
                });
            }
        }
    });
}