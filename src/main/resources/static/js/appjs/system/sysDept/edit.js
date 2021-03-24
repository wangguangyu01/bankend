$().ready(function() {
	validateRule();
    getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());
    getSelectByValue($("#cityVal").val(),"xzqhdm",$("#xzqhdmVal").val());
    getSelectAll("XFJYJGXZDM","XFJYJGXZDM-DIV","xfjyjgxzdm","xfjyjgxzdm-title");
    pointsArr = $("#zbfw").val();
    var xfjyjgxzdm = $("#xfjyjgxzdm").val();
    /*if(xfjyjgxzdm.startsWith("90")){
        $("#zbfwDiv").show();
	}else{
        $("#zbfwDiv").hide();
	}*/
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
		url : "/system/sysDept/update",
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
        ignore: "",//开启对hidden元素的验证
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

function changeval() {
	var xfjyjgxzdm = $("#xfjyjgxzdm").val();
	if(xfjyjgxzdm.startsWith("90") || xfjyjgxzdm.startsWith("50")){
		$("#zbfwDiv").show()
	}else{
		$("#zbfwDiv").hide()
	}
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
//绘制范围地图
var openMap2 = function(){
    layer.open({
        type:2,
        title:"选择坐标点",
        area : [ '800px', '600px' ],
        content:"/common/map2?pointsArr="+pointsArr+"&deptId="+$("#deptId").val()
    })
}
function saveMarker(lng,lat){
    console.log(lng,lat)
    $("#dqjd").val(lng);
    $("#dqwd").val(lat);
}

var pointsArr = "";
//保存辖区范围
function saveRangeData(points) {
    pointsArr = "";
    for(var i in points){
        var lat = points[i].lat;
        var lng = points[i].lng;
        pointsArr+=lat+","+lng+":";
    }
    pointsArr = pointsArr.substring(0,pointsArr.length-1);
    $("#zbfw").val(pointsArr);
    console.log(pointsArr);
}
