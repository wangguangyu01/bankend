$().ready(function() {
	validateRule();
    getSelectAll("XFZBLXDM","XFZBLXDM-DIV","xfzblxdm","xfzblxdm-title");
    getSelectByType("JLDLDWDM","jldldwdm",$("#jldldwdmVal").val());
    getSelectByType("XFZBZTLBDM","xfzbztlbdm",$("#xfzbztlbdmVal").val());
    initFileInput("input-id");
});
$("body").delegate("label.error", "click", function(){
    return false;
})
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function initFileInput(ctrlName) {

    var imgArry=[];
    var removeArry = [];
    var attachmentDOList = JSON.parse($("#attachmentDOList").val());
    debugger;
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
function update() {
    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/jczy/xfzb/update",
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

	// $.ajax({
	// 	type : "POST",
	// 	url : "/jczy/xfzb/update",
	// 	data : $('#signupForm').serialize(),// 你的formid
	// 	async : false,
	// 	error : function(request) {
	// 		parent.layer.alert("Connection error");
	// 	},
	// 	success : function(data) {
	// 		if (data.code == 0) {
	// 			parent.layer.msg("操作成功");
	// 			parent.reLoad();
	// 			var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	// 			parent.layer.close(index);
    //
	// 		} else {
	// 			parent.layer.alert(data.msg)
	// 		}
    //
	// 	}
	// });

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