$().ready(function() {
	//文本域获取富文本编辑器内容
	var fzjcnr = $("#fzjcnr");
	var E = window.wangEditor;
	var editor = new E('#editor');
	// 自定义菜单配置
	/***
	 * 不需要的可以删除
	 */
	editor.config.menus = [
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
	editor.config.uploadImgMaxSize = 3 * 1024 * 1024;
	editor.config.uploadImgAccept = ['jpg', 'jpeg', 'png', 'gif'];
	editor.config.uploadImgMaxLength = 1;
	editor.config.uploadFileName = "file";
	editor.config.uploadImgServer = '/common/sysFile/ftpUpload';
	editor.config.uploadImgParams = {
		f_type: 'fzjc',
	};
	editor.config.uploadVideoServer = '/common/sysFile/ftpUpload';
	editor.config.uploadVideoParams = {
		f_type: 'fzjc',
	};
	// mp4正常，mov能上传，但是不能在谷歌浏览器不能正常播放，需要安装插件
	editor.config.uploadVideoAccept = ['mp4', 'mov', 'rmvb', 'rm', 'wmv', 'avi', 'flv', '3gp'];
	editor.config.uploadVideoName = "file";
	editor.config.onchange = function (newHtml) {
		fzjcnr.val(newHtml);

	};
	editor.config.uploadImgHooks = {
		// 上传图片之前
		before: function(xhr) {
			console.log(xhr);
		},
		// 图片上传并返回了结果，图片插入已成功
		success: function(xhr) {
			console.log('success', xhr);
		},
		// 图片上传并返回了结果，但图片插入时出错了
		/*fail: function(xhr, editor, resData) {
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
			// insertImgFn 可把图片插入到编辑器，传入图片 src ，执行函数即可
            var url = result.fileName.replace("##serverAddr##",'')
			insertImgFn(url);
		}
	};
	editor.config.uploadVideoHooks = {
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
	editor.create();
	fzjcnr.val(editor.txt.html());// 初始化 textarea 的值
	validateRule();
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
		url : "/jczy/fzjc/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		beforeSend: function() {
			var fzjcnrHtml = $("#fzjcnr").val();
			console.log( "vewvew---》"+fzjcnrHtml);
			if (fzjcnrHtml === '') {
				$('#editor').append('<label id="fzjcnr-error" class="error" for="fzjcnr" style="display: inline-block;>'
					+ '<i class="fa fa-times-circle"></i>决策内容不能为空</label>');
				$('#fzjcnr').attr("class", "form-control error");
				$('#fzjcnr').attr("aria-invalid", "true");
				return false;
			} else {
				$('#fzjcnr').attr("class", "form-control valid");
				$('#fzjcnr').attr("aria-invalid", "false");
				$('#editor').append('<label id="fzjcnr-error" class="error" for="fzjcnr" style="display: none;>'
					+ '<i class="fa fa-times-circle"></i></label>');
			}
		},
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
			bt : {
				required : true
			},
			fzjclxdm : {
				required : true
			},
			cdate: {
				required : true
			},
			cperson: {
				required : true
			},
			status: {
				required : true
			}
		},
		messages : {
			bt : {
				required : icon + "请输入标题"
			},
			fzjclxdm : {
				required : icon + "决策类型必选"
			},
			cdate : {
				required : icon + "选择时间"
			},
			cperson : {
				required : icon + "填写创建人名"
			},
			status: {
				required : icon + "选择是否删除"
			}
		}
	})
}
