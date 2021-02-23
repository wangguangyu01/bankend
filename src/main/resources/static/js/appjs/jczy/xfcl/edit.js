$().ready(function() {
	validateRule();
    getSelectAll("XFZBLXDM","XFZBLXDM-DIV","xfzblxdm","xfzblxdm-title");
    getSelectByType("XFCLZZGNDM","xfclzzgndm",$("#xfclzzgndmVal").val());
    getSelectByType("XFCLDJDM","xfcldjdm",$("#xfcldjdmVal").val());
    getSelectByType("JDCCSYSLBDM","jdccsyslbdm",$("#jdccsyslbdmVal").val());
    getSelectAll("CLQWZTLBDM","CLQWZTLBDM-DIV","clqwztlbdm","clqwztlbdm-title");
    addChildAttrVal("xfzblxdm");
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
		url : "/jczy/xfcl/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data != "") {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
                saveCllxSX(data);
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

function addChildAttr(id){
    var input = $("#"+id);
    $.ajax({
        cache : true,
        type : "get",
        url : "/jczy/xfclSx/cllxAttr",
        data : {
            cllx:input.val()
        },// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            $(".cllxSX").remove();
            var html = "";
            $.each(data,function(n,obj) {
                if(n%3==0){
                    html += '<div class="form-group cllxSX" style="background-color:#eee">';
                }
                html += '<label class="col-sm-2 control-label">'+obj.clsx+'/'+obj.dw+'：</label>';
                html += '<div class="col-sm-2"><input id="'+obj.id+'" class="form-control cllx_attr_val" type="text" style="background:#eee;border:1px solid #333333;"></div>';
                if((n-2)%3==0){
                    html += '</div>';
                }
            });
            if(data.length%3!=0){
                html += '</div>';
            }

            input.parent().parent().after(html);

        }

    });
}


function addChildAttrVal(id){
    var input = $("#"+id);
    var clId = $("#xfclTywysbm").val();
    $.ajax({
        cache : true,
        type : "get",
        url : "/jczy/xfclSx/cllxAttrVal",
        data : {
            cllx:input.val(),
            clId:clId
        },// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            $(".cllxSX").remove();
            var html = "";
            $.each(data,function(n,obj) {
                if(n%3==0){
                    html += '<div class="form-group cllxSX" style="background-color:#eee">';
                }
                var sxz = "";
                if(obj.sxz!=null && obj.sxz!=undefined){
                    sxz=obj.sxz;
                }
                html += '<label class="col-sm-2 control-label">'+obj.clsx+'/'+obj.dw+'：</label>';
                html += '<div class="col-sm-2"><input id="'+obj.id+'" value="'+sxz+'" class="form-control cllx_attr_val" type="text" style="background:#eee;border:1px solid #333333;"></div>';
                if((n-2)%3==0){
                    html += '</div>';
                }
            });
            if(data.length%3!=0){
                html += '</div>';
            }

            input.parent().parent().after(html);

        }

    });
}



//保存车辆类型的属性值
function saveCllxSX(clId){

    var sxId = "";
    var valAll = "";
    if($(".cllx_attr_val").length>0){
        $(".cllx_attr_val").each(function(){
            var id = $(this).attr("id");
            var val = $("#"+id).val();
            valAll += ","+val;
            sxId += ","+id;
        });
        sxId = sxId.substring(1,sxId.length);
        valAll = valAll.substring(1,valAll.length);

        $.ajax({
            cache : true,
            type : "POST",
            url : "/jczy/xfclSx/saveCllxSXZ",
            data : {
                clId:clId,
                sxId:sxId,
                valAll:valAll
            },// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {

            }
        });
    }

}