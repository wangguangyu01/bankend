var prefix = "/sys/menu"
$(function() {
	validateRule();
  getAppList('appId',null);
	//打开图标列表
    $("#ico-btn").click(function(){
        layer.open({
            type: 2,
			title:'图标列表',
            content: '/FontIcoList.html',
            area: ['480px', '90%'],
            success: function(layero, index){
                //var body = layer.getChildFrame('.ico-list', index);
                //console.log(layero, index);
            }
        });
    });
});
$.validator.setDefaults({
	submitHandler : function() {
		submit01();
	}
});
function submit01() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/save",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
			laryer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("保存成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				layer.alert(data.msg)
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
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			type : {
				required : icon + "请选择资源类型"
			}
		}
	})
}
function getAppList(objId,selected){
  $.ajax({
    url : "/sys/appInfo/all",
    type : "post",
    data : "{\"status\":\"enable\"}",
    contentType:"application/json",
    success : function(result) {
      if(result.code===0){
        var list=result.data;
        if(list!= null && list!=undefined && list.length>0){
          $("#"+objId).empty();
          $("#"+objId).append('<option value="" >--请选择--</option>');
          $.each(list,function(i,item){
            if(selected!=null && selected==item.value){
              $("#"+objId).append('<option id="'+item.id+'" value="'+item.id+'" selected="selected">'+item.name+'</option>');
            }else{
              $("#"+objId).append('<option id="'+item.id+'" value="'+item.id+'">'+item.name+'</option>');
            }
          });
        }
      }else {
        console.log("msg=="+result.msg);
      }
    }
  });
}