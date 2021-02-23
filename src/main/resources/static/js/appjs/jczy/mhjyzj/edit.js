$().ready(function() {
	validateRule();
    getSelectByType("XBDM","xbdm",$("#xbdmVal").val());  //性别代码
    getSelectByType("s_f","sfdwnbzjPdbz",$("#sfdwnbzjPdbzVal").val());  //是否队伍内部专家
    getSelectAll("XLDM","XLDM-DIV","xldm","xldm-title");  //学历代码
    getSelectAll("XFGWFLYDM","XFGWFLYDM-DIV","xfgwlbdm","xfgwlbdm-title");  //消防岗位类别
    getSelectAll("XFZJLYLBDM","XFZJLYLBDM-DIV","xfzjlylbdm","xfzjlylbdm-title");  //消防专家领域类别

    getSelectByType("XZQHDM","jg_province",$("#jg_provinceVal").val());  //籍贯（省）级联
    getSelectByValue($("#jg_provinceVal").val(),"jg_city",$("#jg_cityVal").val());  //籍贯（市）级联
    getSelectByValue($("#jg_cityVal").val(),"jgdm",$("#jgdmVal").val());  //籍贯（区/县）级联

    getSelectByType("XZQHDM","qh_province",$("#qh_provinceVal").val());  //区划代码（省）级联
    getSelectByValue($("#qh_provinceVal").val(),"qh_city",$("#qh_cityVal").val());  //区划代码（市）级联
    getSelectByValue($("#qh_cityVal").val(),"xzqhdm",$("#xzqhdmVal").val());  //区划代码（区/县）级联

    initFileInput("input-id");

});

// $.validator.setDefaults({
// 	submitHandler : function() {
// 		update();
// 	}
// });
// function update() {
// 	$.ajax({
// 		cache : true,
// 		type : "POST",
// 		url : "/jczy/mhjyzj/update",
// 		data : $('#signupForm').serialize(),// 你的formid
// 		async : false,
// 		error : function(request) {
// 			parent.layer.alert("Connection error");
// 		},
// 		success : function(data) {
// 			if (data.code == 0) {
// 				parent.layer.msg("操作成功");
// 				parent.reLoad();
// 				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
// 				parent.layer.close(index);
//
// 			} else {
// 				parent.layer.alert(data.msg)
// 			}
//
// 		}
// 	});
//
// }

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {

    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/jczy/mhjyzj/update",
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
    })
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

function initFileInput(ctrlName) {

    var imgArry=[];
    var removeArry = [];
    $.each(attachmentDOList, function(i,item){
        imgArry.push('<img src="/attach/ftpDownload?id='+item.attachmentId+'" alt="'+item.name+'" class="file-preview-image" title="'+item.name+'" style="width:100%">')
        var obj = {"caption":item.name,"url":"/attach/ftpDelete?id="+item.attachmentId};
        removeArry.push(obj);
    });


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
        initialPreview: imgArry,
        //initialPreviewAsData: true,
        initialPreviewConfig: removeArry,
        overwriteInitial: false
    })

}