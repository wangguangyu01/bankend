$().ready(function() {
	validateRule();
	getSelectByType("XFJSGWXSLXDM","xfjsgwxslxdm",$("#xfjsgwxslxdmVal").val());
	getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());
	getSelectByValue($("#cityVal").val(),"xzqhdm",$("#xzqhdmVal").val());
	getSelectByType("JJSYZLXDM","jjsyzlxdm",$("#jjsyzlxdmVal").val());
	getSelectByType("DWHZWHXFLYDM","dwhzwhxflydm",$("#dwhzwhxflydmVal").val());
	getSelectByType("DWZRXZDM","dwzrxzdm",$("#dwzrxzdmVal").val());
	getSelectByType("HXPZTLBDM","wxhxphxpztlbdm",$("#wxhxphxpztlbdmVal").val());
	getSelectByType("WXHXPFLYDM","wxhxpwxhxpflydm",$("#wxhxpwxhxpflydmVal").val());
	getSelectByType("HXPWXXLBDM","wxhxphxpwxxlbdm",$("#wxhxphxpwxxlbdmVal").val());
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
		url : "/jczy/zddw/update",
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
            dwmc : {
                required : true,
                maxlength:100
            },
            dwpyjc : {
                required : true,
                maxlength:100
            },
            dzmc : {
                required : true,
                maxlength:100
            }
        },
        messages : {
            dwmc : {
                required :  "请输入重点单位名称"
            },
            dwpyjc : {
                required :  "请输入单位拼音简称"
            },
            dzmc : {
                required :  "请输入单位地址"
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

var openDept = function(){
	layer.open({
		type:2,
		title:"选择消防救援机构",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}


function loadDept( deptId,deptName,xfjyjgTywysbm){
	//$("#deptId").val(deptId);
	$("#xfjyjgTywysbm").val(xfjyjgTywysbm);
	$("#deptName").val(deptName);
}

function getXzqhdm(obj,objId){
	var id = $(obj).find("option:selected").attr("id");
	getSelectById(id,objId);
}
