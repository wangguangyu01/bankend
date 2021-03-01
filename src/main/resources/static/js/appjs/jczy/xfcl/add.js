$().ready(function() {
	validateRule();
    getSelectAll("XFZBLXDM","XFZBLXDM-DIV","xfzblxdm","xfzblxdm-title");
    getSelectByType("XFCLZZGNDM","xfclzzgndm",null);
    getSelectByType("XFCLDJDM","xfcldjdm",null);
    getSelectByType("JDCCSYSLBDM","jdccsyslbdm",null);

    getSelectAll("CLQWZTLBDM","CLQWZTLBDM-DIV","clqwztlbdm","clqwztlbdm-title");
    initFileInput("input-id");
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/jczy/xfcl/save",
        success : function(data) {
            if (data.code != "") {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
                saveCllxSX(data);
            } else {
                parent.layer.alert("操作失败，请联系管理人员！")
            }
        }
    })
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
				required : icon + "请输入姓名"
			}
		}
	})
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
function initFileInput(ctrlName) {
    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        //uploadUrl: "upload/insert", //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png','exe'],//接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.jpg'},
        uploadAsync: true, //默认异步上传
        showUpload: false, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        //dropZoneEnabled: true,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        //maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        layoutTemplates :{
            //actionDelete:'', //去除上传预览的缩略图中的删除图标
            //actionUpload:'',//去除上传预览缩略图中的上传图片；
            //actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        overwriteInitial: false,
        initialPreviewConfig: [
            {caption: "Desert.jpg", size: 827000, width: "120px", url: "/file-upload-batch/2", key: 1},
            {caption: "Lighthouse.jpg", size: 549000, width: "120px", url: "/file-upload-batch/2", key: 2},
        ],
    })

}

