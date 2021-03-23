var prefix = "/sys/tauditlog"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						// showRefresh : true,
						// showToggle : true,
						// showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						// search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"
						queryParams : function(params) {
							return {
								// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit : params.limit,
								offset : params.offset,
                                keyword : $('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
                                {
                                    field : 'number',
                                    title : '序号',
                                    formatter: function (value, row, index) {
                                        //获取每页显示的数量
                                        var pageSize=$('#exampleTable').bootstrapTable('getOptions').pageSize;
                                        //获取当前是第几页
                                        var pageNumber=$('#exampleTable').bootstrapTable('getOptions').pageNumber;
                                        //返回序号，注意index是从0开始的，所以要加上1
                                        return pageSize * (pageNumber - 1) + index + 1;
                                    }
                                },
								{
									field : 'ip',
									title : '访问IP'
								},
								{
									field : 'param',
									title : '参数'
								},
								{
									field : 'mode',
									title : '访问方式'
								},
								{
									field : 'uri',
									title : '访问资源'
								},
								{
									field : 'methodName',
									title : '资源路径'
								},
								{
									field : 'result',
									title : '操作结果'
								},
								{
									field : 'userName',
									title : '操作用户',
								},
								{
									field : 'createTime',
									title : '访问时间'
								},
								{
									field : 'note',
									title : '摘要'
								},
								{
									field : 'resultDetails',
									title : '返回结果'
								},

								{
									field : 'logType',
									title : '日志类型'
								},
								{
									field : 'operationType',
									title : '操作类型'
								},
								{
									field : 'eventType',
									title : '事件类型'
								},
								{
									field : 'eventLevel',
									title : '事件级别'
								},
								{
									field : 'bussiness',
									title : '业务分类'
								},
								{
									field : 'realUrl',
									title : '资源地址'
								},
								// {
								// 	field : 'status',
								// 	title : '状态',
								// 	align : 'center',
								// 	formatter : function(value, row, index) {
								// 		if (value == 'on_line') {
								// 			return '<span class="label label-success">在线</span>';
								// 		} else if (value == 'off_line') {
								// 			return '<span class="label label-primary">离线</span>';
								// 		}
								// 	}
								// },
								]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '增加用户',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add'
	});
}
function forceLogout(id) {
	layer.confirm('确定要强制选中用户下线吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/forceLogout/" + id,
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
function edit(id) {
	layer.open({
		type : 2,
		title : '用户修改',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function resetPwd(id) {
	layer.open({
		type : 2,
		title : '重置密码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '400px', '260px' ],
		content : prefix + '/resetPwd/' + id // iframe的url
	});
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {
	});
}
function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				deptId : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				deptId : data.selected[0],
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	}

});