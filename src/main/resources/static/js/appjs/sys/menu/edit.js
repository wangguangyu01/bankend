var prefix = "/sys/menu"
$(function() {
	validateRule();
	var appId=$("#appIdValue").val();
  getAppList('appId',appId);
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
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/update",
		data : $('#signupForm').serialize(),// 你的formid
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
function validate() {
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
				required : icon + "请输入菜单名"
			},
			type : {
				required : icon + "请选择菜单类型"
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
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入菜单名"
			},
			type : {
				required : icon + "请选择菜单类型"
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
          $("#"+objId).append('<option value="" >--请选择应用--</option>');
          $.each(list,function(i,item){
          	console.log( item);
            if(selected!=null && selected==item.id){
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
