var prefix = "/sys/role";
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/assign/" + $('#roleId').val() + "/userList", // 服务器数据的加载地址
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"
						// queryParams : queryParams,
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{ // 列配置项
									// 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
									checkbox : true
								// 列表中显示复选框
								},
								{
									field : 'userId', // 列字段名
									title : '序号' // 列标题
								},
								{
									field : 'username',
									title : '用户名'
								},
								{
									field : 'name',
									title : '用户姓名'
								},
								{
									field : 'deptName',
									title : '所在消防机构'
								},
								{
									field : 'mobile',
									title : '手机号'
								},
								{
									field : 'email',
									title : '邮箱'
								},
								{
									field : 'status',
									title : '状态',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">禁用</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">正常</span>';
										}
									}
								},
								{
									title : '操作',
									field : 'userId',
									align : 'center',
									formatter : function(value, row, index) {
										var d = '<a class="btn btn-warning btn-sm " href="#" title="删除用户权限"  mce_href="#" onclick="removeUserRole(\''
												+ row.userId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return d;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加角色',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function removeUserRole(userId) {
	var roleName = $('#roleName').val();
	var roleId = $('#roleId').val();
	layer.confirm('确定要删除该用户的【'+roleName+'】权限吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/removeUserRole",
			type : "post",
			data : {
				'roleId' : roleId,
				'userId' : userId
			},
			success : function(r) {
				if (r.code === 0) {
					layer.msg("删除成功");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}


function assign(){
	var roleId = $('#roleId').val();
	layer.open({
		type : 2,
		title : '角色分配',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefix + '/assign/' + roleId + '/addUserRoleForm' // iframe的url
	});
}
