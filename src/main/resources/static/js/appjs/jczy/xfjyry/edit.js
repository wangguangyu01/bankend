$().ready(function() {
    validateRule();
    initFileInput(attachmentDOList,"input-id");
    initFileInput(identityCard,"identityCard");
    initFileInput(salary,"salary");
    initFileInput(academicCertificate,"academicCertificate");
    initFileInput(vehicleLicense,"vehicleLicense");
    initFileInput(premisesPermit,"premisesPermit");
    initFileInput(credit,"credit");

});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});


function update() {
    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/wxUser/updateWxUser",
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
    })
}





function initFileInput(fileList,ctrlName) {

    var imgArry=[];
    var removeArry = [];
    $.each(fileList, function(i,item){
        imgArry.push('<img src="'+ item.url +'" alt="个人照片" class="file-preview-image" title="个人照片" style="width:100%">')
        var obj = {"caption":"个人照片","url":item.url};
        removeArry.push(obj);
    });


    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        //uploadUrl: "upload/insert", //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg'],//接收的文件后缀
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
    $('button[title="删除文件"]').hide();
    $('#input-id').attr('disabled', 'disabled');


}

