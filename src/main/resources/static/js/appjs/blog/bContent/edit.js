$().ready(function () {
    $('.summernote').summernote({
        height : '220px',
        lang : 'zh-CN',
        toolbar: [
            [ 'style', [ 'style' ] ],
            [ 'font', [ 'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear'] ],
            [ 'fontname', [ 'fontname' ] ],
            [ 'fontsize', [ 'fontsize' ] ],
            [ 'color', [ 'color' ] ],
            [ 'para', [ 'ol', 'ul', 'paragraph', 'height' ] ],
            [ 'table', [ 'table' ] ],
            [ 'insert', [ 'link', 'video', 'picture'] ],
            [ 'view', [ 'undo', 'redo', 'fullscreen', 'codeview', 'help' ]]
        ],
        callbacks: {
            onImageUpload: function (files, editor, $editable) {
                var uuid = $("#uuid").val();
                console.log(uuid);
                sendFileNew(files, uuid, editor, $editable);
            }
        }
    });
    var content = $("#content").val();

    $('#content_sn').summernote('code', content);
    validateRule();
    initFileInput(fileList);
    initFileInputQRCode(moneyQRCode);
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save(status) {
    $("#status").val(status);

    var content_sn = $("#content_sn").summernote('code');
    $("#content").val(content_sn);
    $("#signupForm").ajaxSubmit({
        cache: true,
        type: "POST",
        url: "/blog/bContent/save",
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
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
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入姓名"
            }
        }
    })
}

function returnList() {
    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}


function initFileInput(attachmentDOList) {
    console.log(attachmentDOList);

    // 当前的索引
    var index = 0;
    if (attachmentDOList !== null && attachmentDOList.length !== 0) {
        index = showFile(attachmentDOList, index);
    }
    var count = 0;
    if (attachmentDOList !== null) {
        count = 4 - attachmentDOList.length;
    } else {
        count = 4;
    }
    if (count != 0) {
        for (var n = index; n < 4; n++) {
            var imgArry = [];
            var removeArry = [];
            var control;
            if (n == 0) {
                control = $('#input-id');
            } else {
                control = $('#input-id' + n);
            }

            control.fileinput({
                language: 'zh', //设置语言
                allowedFileExtensions: ['jpg', 'gif', 'png', 'mp4', '3gp'],//接收的文件后缀
                //uploadExtraData:{"id": 1, "fileName":'123.jpg'},
                uploadAsync: true, //默认异步上传
                showUpload: false, //是否显示上传按钮
                showRemove: true, //显示移除按钮
                showPreview: true, //是否显示预览
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
                validateInitialCount: true,
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
                layoutTemplates: {
                    //actionDelete:'', //去除上传预览的缩略图中的删除图标
                    //actionUpload:'',//去除上传预览缩略图中的上传图片；
                    //actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
                },
                initialPreview: imgArry,
                //initialPreviewAsData: true,
                initialPreviewConfig: removeArry,
                overwriteInitial: false
            });
        }
    }
}


function showFile(attachmentDOList, index) {
    $.each(attachmentDOList, function (i, item) {
        var imgArry = [];
        var removeArry = [];
        console.log(item);
        imgArry.push('<img src="' + item.url + '" alt="附件" class="file-preview-image" title="附件" style="width:100%">');
        var obj = {"caption": "附件", "url": "/common/sysFile/fileDelete?fileId=" + item.fileId};
        removeArry.push(obj);
        var control
        if (i == 0) {
            control = $('#input-id');
        } else {
            control = $('#input-id' + i);
        }
        control.fileinput({
            language: 'zh', //设置语言
            allowedFileExtensions: ['jpg', 'gif', 'png', 'mp4', '3gp'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.jpg'},
            uploadAsync: true, //默认异步上传
            showUpload: false, //是否显示上传按钮
            showRemove: true, //显示移除按钮
            showPreview: true, //是否显示预览
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
            validateInitialCount: false,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            layoutTemplates: {
                //actionDelete:'', //去除上传预览的缩略图中的删除图标
                //actionUpload:'',//去除上传预览缩略图中的上传图片；
                //actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
            },
            initialPreview: imgArry,
            //initialPreviewAsData: true,
            initialPreviewConfig: removeArry,
            overwriteInitial: false
        });
        index = i + 1;
    });
    return index;
}


function initFileInputQRCode(moneyQRCode) {
    var imgArry = [];
    var removeArry = [];
    console.log(moneyQRCode);
    imgArry.push('<img src="' + moneyQRCode.url + '" alt="二维码" class="file-preview-image" title="二维码" style="width:100%">');
    var obj = {"caption": "二维码", "url": "/common/sysFile/fileDelete?fileId=" + moneyQRCode.fileId};
    removeArry.push(obj);
    var control = $('#moneyQRCode');
    control.fileinput({
        language: 'zh', //设置语言
        allowedFileExtensions: ['jpg', 'gif', 'png', 'mp4', '3gp'],//接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.jpg'},
        uploadAsync: true, //默认异步上传
        showUpload: false, //是否显示上传按钮
        showRemove: true, //显示移除按钮
        showPreview: true, //是否显示预览
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
        validateInitialCount: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        layoutTemplates: {
            //actionDelete:'', //去除上传预览的缩略图中的删除图标
            //actionUpload:'',//去除上传预览缩略图中的上传图片；
            //actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        initialPreview: imgArry,
        //initialPreviewAsData: true,
        initialPreviewConfig: removeArry,
        overwriteInitial: false
    });
}
