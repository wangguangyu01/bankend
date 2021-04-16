$().ready(function() {
	appendOption('orderNum', orderNum);
	initFileInput("input-id-edit",attachmentDOList);
	var fzjcnr = $("#fzjcnr");
	//文本域获取富文本编辑器内容
	var E = window.wangEditor;
	var editorEdit = new E('#editorEdit');
	editorEdit.config.menus = [
		'head', // 标题
		'bold', // 粗体
		'fontSize', // 字号
		'fontName', // 字体
		'italic', // 斜体
		'underline', // 下划线
		'strikeThrough', // 删除线
		'foreColor', // 文字颜色
		'backColor', // 背景颜色
		'link', // 插入链接
		'list', // 列表
		'justify', // 对齐方式
		'quote', // 引用
		'emoticon', // 表情
		'image', // 插入图片
		'table', // 表格
		'video', // 插入视频
		'location',
		'code', // 插入代码
		'undo', // 撤销
		'redo' // 重复
	];
	editorEdit.config.uploadImgMaxSize = 3 * 1024 * 1024;
	editorEdit.config.uploadImgAccept = ['jpg', 'jpeg', 'png', 'gif'];
	editorEdit.config.uploadImgMaxLength = 1;
	editorEdit.config.uploadFileName = "file";
	editorEdit.config.uploadImgServer = '/common/sysFile/ftpUpload';
	editorEdit.config.uploadImgParams = {
		f_type: 'fzjc'
	};
	editorEdit.config.uploadVideoServer = '/common/sysFile/ftpUpload';
	editorEdit.config.uploadVideoParams = {
		f_type: 'fzjc'
	};
	editorEdit.config.uploadVideoMaxSize = 1 * 1024 * 1024 * 1024;
	// mp4正常，mov能上传，但是不能在谷歌浏览器不能正常播放，需要安装插件
	editorEdit.config.uploadVideoAccept = ['mp4', 'mov', 'rmvb', 'rm', 'wmv', 'avi', 'flv', '3gp'];
	editorEdit.config.uploadVideoName = "file";
	editorEdit.config.onchange = function (newHtml) {
		fzjcnr.val(newHtml);
	};
	editorEdit.config.uploadImgHooks = {
		// 上传图片之前
		before: function(xhr) {
			console.log(xhr);
		},
		// 图片上传并返回了结果，图片插入已成功
		success: function(xhr) {
			console.log('success', xhr);
		},
		// 图片上传并返回了结果，但图片插入时出错了
		/*	fail: function(xhr, editor, resData) {
                console.log('fail', resData);
            },*/
		// 上传图片出错，一般为 http 请求的错误
		error: function(xhr, editor, resData) {
			console.log('error', xhr, resData);
		},
		// 上传图片超时
		timeout: function(xhr) {
			console.log('timeout');
		},
		// 图片上传并返回了结果，想要自己把图片插入到编辑器中
		// 例如服务器端返回的不是 { errno: 0, data: [...] } 这种格式，可使用 customInsert
		customInsert: function(insertImgFn, result) {
			// result 即服务端返回的接口
			console.log('customInsert', result);
			console.log('result.fileName', result.fileName);
			var url = result.fileName.replace("##serverAddr##",'')
			// insertImgFn 可把图片插入到编辑器，传入图片 src ，执行函数即可
			insertImgFn(url);
		}
	};
	editorEdit.config.uploadVideoHooks = {
		// 上传视频之前
		before: function(xhr) {
			console.log(xhr);

			// 可阻止视频上传
			/*return {
				prevent: true,
				msg: '需要提示给用户的错误信息'
			}*/
		},
		// 视频上传并返回了结果，视频插入已成功
		success: function(xhr) {
			console.log('success', xhr)
		},
		// 视频上传并返回了结果，但视频插入时出错了
		/*fail: function(xhr, editor, resData) {
			console.log('fail', resData)
		},*/
		// 上传视频出错，一般为 http 请求的错误
		error: function(xhr, editor, resData) {
			console.log('error', xhr, resData)
		},
		// 上传视频超时
		timeout: function(xhr) {
			console.log('timeout')
		},
		// 视频上传并返回了结果，想要自己把视频插入到编辑器中
		// 例如服务器端返回的不是 { errno: 0, data: { url : '.....'} } 这种格式，可使用 customInsert
		customInsert: function(insertVideoFn, result) {
			// result 即服务端返回的接口
			console.log('customInsert', result);

			// insertVideoFn 可把视频插入到编辑器，传入视频 src ，执行函数即可
			insertVideoFn(result.fileName)
		}
	};
	editorEdit.create();
	fzjcnr.val(editorEdit.txt.html());// 初始化 textarea 的值
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$("#signupForm").ajaxSubmit({
		type : "POST",
		url : "/back/xfzl/update",
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
		rules: {
			bt: {
				required: true,
				rangelength: [1, 100]
			},
			lx: {
				required: true
			},
			jqflydm: {
				required: true
			}
		},
		messages: {
			bt: {
				required: icon + "请输入标题",
				rangelength: icon + "标题的长度是1到100字符"
			},
			lx: {
				required: icon + "类型必选"
			},
			jqflydm: {
				required: icon + "警情分类必选"
			}
		}
	})
}


function initFileInput(ctrlName,attachmentDOList) {

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
		maxFileCount: 1, //表示允许同时上传的最大文件个数
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


function appendOption(selectId, value) {
	var select = $('#' + selectId);
	if (select != null && typeof select != 'undefined') {
		select.empty();
		for (var i = 0; i < 11; i++) {
			if (value && value == i) {
				select.append("<option value= '"+ i + "' selected>" + i + "</option>")
			} else {
				select.append("<option value= '"+ i + "'>" + i + "</option>")
			}

		}
	}
}
