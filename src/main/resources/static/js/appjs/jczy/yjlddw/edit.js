$().ready(function() {
	validateRule();
    getSelectByType("YJLDDWLBDM","yjlddwlbdm",yjlddwlbdm);
    getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());
    getSelectByValue($("#cityVal").val(),"xzqhdm",$("#xzqhdmVal").val());
    initFileInput("input-id");

});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {

    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/jczy/yjlddw/update",
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
            dwmc : {
                required : true,
                maxlength : 50
            },
            dzmc : {
                required : true,
                maxlength : 100
            },
            czhm : {
                maxlength : 18
            },
            lxrXm : {
                required : true,
                maxlength:50
            },
            lxrLxdh : {
                required : true,
                digits:true,
                mobile : true,
                maxlength:18
            }
        },
        messages : {
            dwmc : {
                required :  "请输入单位名称"
            },
            dzmc : {
                required :  "请输入单位地址"
            },
            lxrXm : {
                required :  "请输入联系人"
            },
            lxrLxdh : {
                required :  "请输入联系电话",
                digits : "请输入数字格式"
            }
        }
    })
}

jQuery.validator.addMethod("mobile", function(value, element) {
    if(value){
        if(/[0-9-()（）]{7,18}/.test(value) || /^1[34578][0-9]\d{8}$/.test(value)){
            return true;
        }else{
            return false;
        }
    }
    return true;
}, "联系电话格式错误");

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
    getSelectById(id,objId);
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