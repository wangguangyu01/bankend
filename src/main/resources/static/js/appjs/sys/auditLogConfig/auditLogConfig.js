var prefix = "/sys/tauditlogconfig"
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
									field : 'url',
									title : '权限标识'
								},
								{
									field : 'sysName',
									title : '所属系统'
								},
								{
									field : 'isReplace',
									title : '是否输出替换',
                                    align : 'center',
                                    	formatter : function(value, row, index) {
                                    		if (value == 1) {
                                    			return '<span class="label label-success">是</span>';
                                    		} else if (value == 0) {
                                    			return '<span class="label label-primary">否</span>';
                                    		}
                                    	}
								},
								{
									field : 'isWriteResultDetails',
									title : '是否记录结果',
                                    align : 'center',
                                    formatter : function(value, row, index) {
                                        if (value == 1) {
                                            return '<span class="label label-success">是</span>';
                                        } else if (value == 0) {
                                            return '<span class="label label-primary">否</span>';
                                        }
                                    }
								},
                                {
                                    field : 'isOpen',
                                    title : '是否启用',
                                    align : 'center',
                                    formatter : function(value, row, index) {
                                        if (value == 1) {
                                            return '<span class="label label-success">是</span>';
                                        } else if (value == 0) {
                                            return '<span class="label label-primary">否</span>';
                                        }
                                    }
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
									title : '菜单信息'
								},
								{
									field : 'tag',
									title : '描述'
								},
								{
									field : 'createTime',
									title : '创建时间'
								},
                                {
                                    title: '操作',
                                    field: 'id',
                                    align: 'center',
                                    formatter: function (value, row, index) {
                                        var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                            + row.id
                                            + '\')"><i class="fa fa-edit"></i></a> ';
                                        return e;
                                    }
                                }
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
		title : '增加配置',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '600px' ],
		content : prefix + '/add'
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '修改配置',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '600px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}