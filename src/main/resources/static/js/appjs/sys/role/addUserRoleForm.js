$(function() {
	var deptId = '';
	getTreeData();
	load(deptId);
});

function load(deptId) {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/sys/user/list", // 服务器数据的加载地址
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
						name : $('#searchName').val(),
						deptId : deptId
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
						field : 'name',
						title : '姓名',
						formatter : function(value, row, index) {
							return '<span style="cursor:pointer;" onclick="add(' + row.userId + ',\'' + row.name + '\');">' + row.name + '</span>';
						}
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
					}]
			});
}

function add(userId,userName){
	if($('#userId_'+userId).length > 0){
		return;
	}
	var str = "<label class='labelName' onclick='remove(this);'  id='userId_"+userId+"'>"+userName+"</label>";
	$('#selectedDiv').append(str);
}

function remove(obj){
	obj.remove();
}

function removeAll(){
	$(".labelName").each(function(){
		if($(this).attr("history") == 'true'){
			return;
		}
		$(this).remove();
	});
}

function addUserRole(){
	var roleId = $('#roleId').val();
	var userIds = "";
	$(".labelName").each(function(){
		if($(this).attr("history") == 'true'){
			return;
		}
		if(userIds.length > 0){
			userIds += ",";
		}
		userIds += $(this).attr("id").split("_")[1];
	});

	if(userIds.length == 0){
		alert("请选择用户后再进行添加");
		return;
	}

	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/role/assign/addUserRole",
		data : {
			'roleId' : roleId,
			'userIds' : userIds
		},
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}
		}
	});

}

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});