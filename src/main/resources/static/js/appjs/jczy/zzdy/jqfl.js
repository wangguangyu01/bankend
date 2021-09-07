
var prefix = "/webapi/zzdy"
$(function() {
	load();
	init();
});

function load() {
	$('#exampleTable')
		.bootstrapTreeTable(
			{
				id : 'id',
				code : 'id',
                parentCode : 'parentId',
				type : "GET", // 请求数据的ajax类型
				url : prefix + '/getJqflList', // 请求数据的ajax的url
                ajaxParams:{
					dwmc : $('#searchName').val()

                }, // 请求数据的ajax的data属性
				expandColumn : '1', // 在哪一列上面显示展开按钮
				striped : true, // 是否各行渐变色
				bordered : true, // 是否显示边框
				expandAll : true, // 是否全部展开
				expandFirst: true,
				// toolbar : '#exampleToolbar',
				columns : [
                    {
						//必须叫selectItem 源码内要求
                        field: 'selectItem',
                        checkbox : false,
                    },
					{
						field : 'name',
						title : '警情分类',
                        valign : 'center',
						witth :20,
						formatter: function(item, index) {
							return '<span style="cursor: pointer;" onclick="selectFl(\''+item.name+'\',\''+item.value+'\')">'+item.name+'</span>';
						}
					}]
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

function selectFl(name,value) {
	if(value==''){
		return
	}
	if($("#selectValues").val().indexOf(value)!=-1){
		layer.msg('已选择此分类！');
		return
	}
	var onclick = "del('"+value+"')";
	$("#selectDiv").append("<span class=\"label label-info\" id='"+value+"' onclick="+onclick+">"+name+"</span>&nbsp;")
	$("#selectValues").val($("#selectValues").val()+value+",");
}
function qdjqfl() {
	$('#jqflydm', window.parent.document).val($("#selectValues").val());
	//$('#jqflydmnames', window.parent.document).html($("#selectDiv").html());

	$('#jqflydmnames', window.parent.document).empty();
	$(".label").each(function(i){
		if (i != 0 && (i + 1) % 4 == 0) {
			$('#jqflydmnames', window.parent.document).append("<span style='line-height:30px;' class='label label-info'>"+$(this).text()+"</span>&nbsp;</br>")
		}else{
			$('#jqflydmnames', window.parent.document).append("<span class='label label-info'>"+$(this).text()+"</span>&nbsp;")
		}

	});
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
function del(value) {
	var selectValues = $("#selectValues").val();
	selectValues = selectValues.replaceAll(value+",","")
	$("#selectValues").val(selectValues);
	$("#"+value).remove();
}

function init() {
	$.each(dictDOList, function(i,item){
		var onclick = "del('"+item.value+"')";
		$("#selectDiv").append("<span class=\"label label-info\" id='"+item.value+"' onclick="+onclick+">"+item.name+"</span>&nbsp;")
		$("#selectValues").val($("#selectValues").val()+item.value+",");
	});
}
