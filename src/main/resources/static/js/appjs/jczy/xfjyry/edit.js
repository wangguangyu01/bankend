$().ready(function() {
	validateRule();
    getSelectByType("CYZJLXDM","cyzjlxdm",$("#cyzjlxdm_val").val());  //常用证件类型
    getSelectByType("XBDM","xbdm",$("#xbdm_val").val());  //性别代码
    getSelectByType("MZDM","mzdm",$("#mzdm_val").val());  //民族代码
    getSelectByType("ZZMMDM","zzmmdm",$("#zzmmdm_val").val());  //政治面貌代码

    getSelectByType("HYZKDM","hyzkdm",$("#hyzkdm_val").val());  //婚姻状态
    getSelectByType("XFJYRYZTDM","xfjyryztdm",$("#xfjyryztdm_val").val());  //消防救援人员状态代码
    getSelectByType("XFJYRYZWQKDM","xfjyryzwqkdm",$("#xfjyryzwqkdm_val").val());  //消防救援人员在位情况代码

    getSelectAll("XLDM","XLDM-DIV","xldm","xldm-title");  //学历代码
    getSelectAll("XWDM","XWDM-DIV","xwdm","xwdm-title");  //学位代码
    getSelectAll("XFZJLYLBDM","XFZJLYLBDM-DIV","xfzjlylbdm","xfzjlylbdm-title");  //消防专家领域类别
    getSelectAll("XFJYRYLBDM","XFJYRYLBDM-DIV","xfjyrylbdm","xfjyrylbdm-title");  //消防救援人员类别
    getSelectAll("XFGWFLYDM","XFGWFLYDM-DIV","xfgwflydm","xfgwflydm-title");  //消防岗位分类与代码
    getSelectAll("ZYJSZWLBDM","ZYJSZWLBDM-DIV","zyjszwlbdm","zyjszwlbdm-title");  //专业技术职务类别
    getSelectAll("XFJYXJBDM","XFJYXJBDM-DIV","xfjyxjbdm","xfjyxjbdm-title");  //消防救援衔级别

    getSelectByType("XZQHDM","province",$("#provinceVal").val());  //区划代码（省）级联
    getSelectByValue($("#provinceVal").val(),"city",$("#cityVal").val());  //区划代码（市）级联
    getSelectByValue($("#cityVal").val(),"jgdm",$("#jgdmVal").val());  //区划代码（区/县）级联

    getSelectByType("rygwlb_jh","rylbJh",$("#rylbJh_val").val());  //人员岗位简类

    $("#sfzjPdbz option[value='"+$("#sfzjPdbz_val").val()+"']").attr("selected","selected");  //是否专家
    initFileInput("input-id");

    var userid = $('#userid').val();
    if(userid != ""){
        //用户不为空，验证ydLxdh是否存在其他用户重名
        $('#ydLxdh').rules('add',{
            remote:{
                url : "/sys/user/exit", // 后台处理程序
                type : "post", // 数据发送方式
                dataType : "json", // 接受数据格式
                data : { // 要传递的数据
                    username : function() {
                        return $("#ydLxdh").val();
                    },
                    notThisUserId : userid
                }
            }
        });
    }

    $("input[name='isCreateUser']").click(function(){
        if("1" == $('input:radio[name="isCreateUser"]:checked').val()){
            $('#roleDiv').show();
            $('#ydLxdh').rules('add',{
                remote:{
                    url : "/sys/user/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        username : function() {
                            return $("#ydLxdh").val();
                        }
                    }
                }
            });
        }else{
            $('#roleDiv').hide();
            $('#ydLxdh').rules('remove','remote');
        }
    })
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    $("#signupForm").ajaxSubmit({
        type : "POST",
        url : "/jczy/xfjyry/update",
        success : function(data) {
            if (data.code != "") {
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
			},
            ydLxdh : {
                required : true
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
            isCreateUser : {
                required : icon + "请选择是否创建用户"
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