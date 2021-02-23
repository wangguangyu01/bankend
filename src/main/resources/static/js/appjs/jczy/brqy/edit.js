$().ready(function() {
	validateRule();
    getSelectByType("ghljbrqylb","type",$("#type_val").val());  //常用证件类型
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
		url : "/jczy/brqy/update",
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

var pointsArr = "";
//保存辖区范围
function saveRangeData(points) {
    pointsArr = "";
    for(var i in points){
        var lat = points[i].lat;
        var lng = points[i].lng;
        pointsArr+=lat+","+lng+";";
    }
    pointsArr = pointsArr.substring(0,pointsArr.length-1);
    $("#coordinatesBaidu").val(pointsArr);

}


//绘制范围地图
var openMap3 = function(){
    pointsArr = $("#coordinatesBaidu").val();
    var brqyId = $("#id").val();
    layer.open({
        type:2,
        title:"选择坐标点",
        area : [ '800px', '600px' ],
        content:"/common/map3?pointsArr="+pointsArr+"&brqyId="+brqyId
    })
}