$().ready(function() {

    getSelectByType("lddwlb","lddwlbdm",$("#lddwlbdm_val").val());  //性别代码
	validateRule();
    getSelectByType("XZQHDM","xzqhdm",$("#xzqhdmVal").val());  //区划代码（省）级联
    getSelectByType("XZQHDM","province",$("#provinceVal").val());  //区划代码（省）级联
    getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());  //区划代码（市）级联
    getSelectByValue($("#cityVal").val(),"xzqhdm",$("#xzqhdmVal").val());  //区划代码（区/县）级联
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
		url : "/jczy/shlddw/update",
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

var openDept = function(objId){
    inpId = objId;
    layer.open({
        type:2,
        title:"选择消防救援机构",
        area : [ '300px', '450px' ],
        content:"/system/sysDept/treeView"
    })
}


function loadDept( deptId,deptName,xfjyjgTywysbm){
    //$("#deptId").val(deptId);
    $("#"+inpId).val(xfjyjgTywysbm);
    $("#"+inpId+"Name").val(deptName);
}