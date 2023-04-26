$().ready(function() {
	validateRule();
	getSelectByType("BCBDLXDM","bcbdlxdm",null);
    getSelectByType("BCBDZQ","bcbdzq",null);
    getSelectByType("BCBDGNLXDM","bcbdgnlxdm",null);
	getZZDY("zzdy",null);
    localStorage.setItem("remembermeDataList","")
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	if($("#zzdyid").val()==''){
		parent.layer.alert('请添加作战单元！');
		return;
	}

	$("#signupForm").ajaxSubmit({
		type : "POST",
		url : "/jczy/bcbd/save",
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
	})
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

function getZZDY(objId,selected){
	$.ajax({
		url : "/jczy/bcbd/getZZDY",
		type : "get",
		data : {},
		success : function(list) {
			if(list!=null && list!=undefined && list.length>0){
				$.each(list,function(i,item){
					$("#"+objId).append('<option id="'+item.id+'" value="'+item.id+'" selected="selected">'+item.NAME+'</option>');
				});
			}
		}
	});
}
function add() {
	layer.open({
		type : 2,
		title : '选择作战单元',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '90%' ],
		content : '/jczy/bcbd/addzzdy' // iframe的url
	});
}