var inpId = "";

$().ready(function() {
	validateRule();
    getSelectByType("CYZJLXDM","cyzjlxdm",null);  //常用证件类型
    getSelectByType("XBDM","xbdm",null);  //性别代码
    getSelectByType("MZDM","mzdm",null);  //民族代码
    getSelectByType("ZZMMDM","zzmmdm",null);  //政治面貌代码
    getSelectByType("XZQHDM","province",null);  //区划代码（省）级联
    getSelectAll("XLDM","XLDM-DIV","xldm","xldm-title");  //学历代码
    getSelectAll("XWDM","XWDM-DIV","xwdm","xwdm-title");  //学位代码
    getSelectAll("XFZJLYLBDM","XFZJLYLBDM-DIV","xfzjlylbdm","xfzjlylbdm-title");  //消防专家领域类别
    getSelectByType("HYZKDM","hyzkdm",null);  //婚姻状态
    getSelectAll("XFJYRYLBDM","XFJYRYLBDM-DIV","xfjyrylbdm","xfjyrylbdm-title");  //消防救援人员类别
    getSelectByType("XFJYRYZTDM","xfjyryztdm",null);  //消防救援人员状态代码
    getSelectByType("XFJYRYZWQKDM","xfjyryzwqkdm",null);  //消防救援人员在位情况代码
    getSelectAll("XFGWFLYDM","XFGWFLYDM-DIV","xfgwflydm","xfgwflydm-title");  //消防岗位分类与代码
    getSelectAll("ZYJSZWLBDM","ZYJSZWLBDM-DIV","zyjszwlbdm","zyjszwlbdm-title");  //专业技术职务类别
    getSelectAll("XFJYXJBDM","XFJYXJBDM-DIV","xfjyxjbdm","xfjyxjbdm-title");  //消防救援衔级别
    getSelectByType("rygwlb_jh","rylbJh",null);  //人员岗位简类
    initFileInput("input-id");


    $("input[name='isCreateUser']").click(function(){
        if("1" == $('input:radio[name="isCreateUser"]:checked').val()){
            $('#accountDiv').show();
            $('#roleDiv').show();
            $('#username').rules('add',{
                required: true,
                remote:{
                    url : "/sys/user/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        username : function() {
                            return $("#username").val();
                        }
                    }
                }
            });
        }else{
            $('#accountDiv').hide();
            $('#roleDiv').hide();
            $('#username').rules('remove','remote');
        }
    })

});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/jczy/xfjyry/save",
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
            name : {
                required : true
            },
            zjhm : {
                isIdentity : true,
                maxlength:30
            },
            bgLxdh : {
                mobile : true,
                maxlength:18
            },
            ydLxdh : {
                required : true,
                mobile : true,
                maxlength:18
            },
            nwDzxx : {
                email:true
            },
            hlwDzxx : {
                email:true
            },
            isCreateUser : {
                required : true
            },
            txdz : {
                required : false,
                maxlength:100
            },
            bz : {
                required : false,
                maxlength:500
            }
        },
        messages : {
            name : {
                required : icon + "请输入名字"
            },
            ydLxdh : {
                required : icon + "请输入移动_联系电话",
                remote : icon + "移动_联系电话已经存在"
            },
            nwDzxx : {
                email: "请输入正确的邮箱格式"
            },
            hlwDzxx : {
                email: "请输入正确的邮箱格式"
            },
            isCreateUser : {
                required : icon + "请选择是否创建用户"
            },
            username:{
                required: icon + "请输入登录名",
                remote:"登录名已存在"
            }
        }
    })
}

//校验身份证
jQuery.validator.addMethod("isIdentity",function(value,element){
    if($("#cyzjlxdm").val()=="111"){
        var id= /^(\d{15}$|^\d{18}$|^\d{17}(\d|X))$/;
        if(id.test(value)){
            return true;
        }else{
            return false;
        }
    }else{
        return true
    }
},"请输入正确身份证号");


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

var openDept = function(objId){
    inpId = objId;
    layer.open({
        type:2,
        title:"选择消防救援机构",
        area : [ '300px', '450px' ],
        content:"/system/sysDept/treeView"
    })
}


function loadDept( deptId,deptName,xfjyjgTywysbm){
    //$("#deptId").val(deptId);
    $("#"+inpId).val(xfjyjgTywysbm);
    $("#"+inpId+"Name").val(deptName);
}

function getXzqhdm(obj,objId){
    var id = $(obj).find("option:selected").attr("id");
    console.log(id);
    getSelectById(id,objId);
}

function initFileInput(ctrlName) {
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
        overwriteInitial: false,
        initialPreviewConfig: [
            {caption: "Desert.jpg", size: 827000, width: "120px", url: "/file-upload-batch/2", key: 1},
            {caption: "Lighthouse.jpg", size: 549000, width: "120px", url: "/file-upload-batch/2", key: 2},
        ],
    })

}
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
};

function checkPhone(str) {
    if (str.length > 20) {
        return false;
    }
    var patternStr = "(0123456789-)";
    var strlength = str.length;
    for (var i = 0; i < strlength; i++) {
        var tempchar = str.substring(i, i + 1);
        if (patternStr.indexOf(tempchar) < 0) {
            return false;
        }
    }
    return true;
}
