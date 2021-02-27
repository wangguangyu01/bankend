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
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/jczy/xfjyry/save",
        success : function(data) {
            if (data.code != "") {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert("操作失败，请联系管理人员！")
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
				required : icon + "请输入姓名"
			}
		}
	})
}

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