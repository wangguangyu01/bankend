$().ready(function() {
	validateRule();
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
		url : "/jczy/wxxfz/update",
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
            mc : {
				required : true
			},
            rs : {
                required : true
            },
            dzmc : {
                required : true
            },

            fzrXm : {
                required : true
            },
            fzrLxdh : {
                required : true,
                mobile : true,
                maxlength:18
            }
		},
		messages : {
            mc : {
				required :   "请输入名称"
			},
            rs : {
                required :   "请输入人数"
            },
            dzmc : {
                required :   "请输入地址名称"
            },

            fzrXm : {
                required :   "请输入负责人姓名"
            },
            fzrLxdh : {
                required :   "请输入负责人联系电话"
            }
		}
	})
}

jQuery.validator.addMethod("mobile", function(value, element) {
    if(/[0-9-()（）]{7,18}/.test(value) || /^1[34578][0-9]\d{8}$/.test(value)){
        return true;
    }
    return false;
}, "联系电话格式错误");

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

function getXzqhdm(obj,objId){
    var id = $(obj).find("option:selected").attr("id");
    console.log(id);
    $("#xzqhdm").empty();
    $("#xzqhdm").append('<option value="" >--请选择--</option>');
    getSelectById(id,objId);
}