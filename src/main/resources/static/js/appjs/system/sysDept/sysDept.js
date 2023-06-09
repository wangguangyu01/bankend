
var prefix = "/system/sysDept"
$(function() {
	load();

});

function load() {
	$('#exampleTable')
		.bootstrapTreeTable(
			{
				id : 'deptId',
				code : 'deptId',
                parentCode : 'parentId',
				type : "GET", // 请求数据的ajax类型
				url : prefix + '/list', // 请求数据的ajax的url
                ajaxParams:{
					dwmc : $('#searchName').val()

                }, // 请求数据的ajax的data属性
				expandColumn : '1', // 在哪一列上面显示展开按钮
				striped : true, // 是否各行渐变色
				bordered : true, // 是否显示边框
				expandAll : true, // 是否全部展开
				// toolbar : '#exampleToolbar',
				columns : [
					// {
					// 	title : '消防机构识别码',
					// 	field : 'xfjyjgTywysbm',
					// 	visible : false,
					// 	align : 'center',
					// 	valign : 'center',
					// 	width : '50px',
					// 	checkbox : true
					// },
                    {
						//必须叫selectItem 源码内要求
                        field: 'selectItem',
                        checkbox : true,
                    },
					{
						field : 'dwmc',
						title : '单位名称',
                        valign : 'center',
						witth :20
					},
					{
						field : 'lxrXm',
						title : '联系人姓名',
                        align : 'center',
                        valign : 'center',
					},
					{
                        field : 'lxdh',
                        title : '联系电话',
                        align : 'center',
                        valign : 'center',
                    },
					{
						field : 'delFlag',
						title : '是否删除',
						align : 'center',
						valign : 'center',
						formatter : function(item, index) {
							if (item.delFlag === '-1' || item.delFlag === -1) {
								return  '已删除';
							}
							if (item.delFlag !== '-1' && item.delFlag !== -1) {
								return  '未删除';
							}
						}
					},
					// {
					// 	field : 'delFlag',
					// 	title : '状态',
					// 	align : 'center',
                    //     valign : 'center',
					// 	formatter : function(item, index) {
					// 		if (item.delFlag == '0') {
					// 			return '<span class="label label-danger">禁用</span>';
					// 		} else if (item.delFlag == '1') {
					// 			return '<span class="label label-primary">正常</span>';
					// 		}
					// 	}
					// },
					{
						title : '操作',
						field : 'id',
						align : 'center',
                        valign : 'center',
						formatter : function(item, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ item.deptId
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="增加下級"  mce_href="#" onclick="add(\''
								+ item.deptId
								+ '\')"><i class="fa fa-plus"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeone(\''
								+ item.deptId
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-info btn-sm ' + s_move_h + '" href="#" title="上移"  mce_href="#" onclick="move('
								+ item.deptId
								+ ',0)"><i class="fa fa-arrow-up"></i></a> ';
                            var h = '<a class="btn btn-info btn-sm ' + s_move_h + '" href="#" title="下移"  mce_href="#" onclick="move('
                                + item.deptId
                                + ',1)"><i class="fa fa-arrow-down"></i></a> ';
							return e + a + d + f + h;
						}
					} ]
			});
}
function reLoad() {
	load();
}
function add(pId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefix + '/add/' + pId
	});
}
function edit(deptId) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefix + '/edit/' + deptId // iframe的url
	});
}
function removeone(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'deptId' : id
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

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTreeTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
			ids[i] = row['id'];
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
	}, function() {});
}

function move(id, type) {

    layer.confirm('确定要'+ (type == 0 ? '上移': '下移')+ '选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/move/" + id + "/" + type,
            type : "get",
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
