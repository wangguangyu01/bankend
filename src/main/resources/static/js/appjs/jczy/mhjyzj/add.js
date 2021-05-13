$().ready(function() {
	validateRule();
    getSelectByType("XBDM","xbdm",null);  //性别代码
    getSelectByType("XZQHDM","province",null);  //籍贯（省）级联
    getSelectByType("XZQHDM","province2",null);  //区划代码（省）级联
    getSelectByType("s_f","sfdwnbzjPdbz",null);  //是否为队伍专家

    getSelectAll("XLDM","XLDM-DIV","xldm","xldm-title");  //学历代码
    getSelectAll("XFGWFLYDM","XFGWFLYDM-DIV","xfgwlbdm","xfgwlbdm-title");  //消防岗位分类与代码
    getSelectAll("XFZJLYLBDM","XFZJLYLBDM-DIV","xfzjlylbdm","xfzjlylbdm-title");  //消防专家领域类别
    initFileInput("input-id");
});

// $.validator.setDefaults({
// 	submitHandler : function() {
// 		save();
// 	}
// });
// function save() {
// 	$.ajax({
// 		cache : true,
// 		type : "POST",
// 		url : "/jczy/mhjyzj/save",
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
        url : "/jczy/mhjyzj/save",
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
            xm : {
                required : true,
                maxlength:50
            },
            gmsfhm : {
                isIdentity : true
            },
            txdz : {
                required : true,
                maxlength:100
            },
            yzbm : {
                isZipCode : true
            },
            yddh : {
                required : true,
                mobile : true,
                maxlength:18
            },
            jtLxdh : {
                mobile : true,
                maxlength:18
            },
            bgLxdh : {
                mobile : true,
                maxlength:18
            },
            dwmc : {
                required : true,
                maxlength:100
            }
        },
        messages : {
            xm : {
                required : "请输入姓名"
            },
            txdz : {
                required : "请输入通信地址"
            },
            yddh : {
                required :  "请输入联系电话",
                digits : "请输入数字格式"
            },
            dwmc : {
                required : "请输入单位名称"
            }
        }
    })
}

//校验身份证
jQuery.validator.addMethod("isIdentity",function(value,element){
    var id= /^(\d{15}$|^\d{18}$|^\d{17}(\d|X))$/;
    return this.optional(element) || (id.test(value));
},"请输入正确身份证号");

jQuery.validator.addMethod("isZipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的邮政编码");

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