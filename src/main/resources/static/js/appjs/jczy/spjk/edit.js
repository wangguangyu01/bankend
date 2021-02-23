$().ready(function() {
	validateRule();
    getSelectByType("SPJKLXDM","spjklxdm",$("#spjklxdmVal").val());  //视频监控类型
    getSelectByType("SPQXDLXDM","splxdm",$("#splxdmVal").val());  //视频清晰度类型
    getSelectByType("SPJRFSDM","spjrfsdm",$("#spjrfsdmVal").val());  //视频接入方式
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
		url : "/jczy/spjk/update",
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

var openMap = function(){
    var lng = $("#dqjd").val();
    var lat = $("#dqwd").val();
    layer.open({
        type:2,
        title:"选择坐标点",
        area : [ '800px', '600px' ],
        content:"/common/map?lng="+lng+"&lat="+lat
    })
}

function saveMarker(lng,lat){
    console.log(lng,lat)
    $("#dqjd").val(lng);
    $("#dqwd").val(lat);
}