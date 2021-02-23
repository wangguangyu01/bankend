
var prefix = "/common/dict"
$(function() {
    selectLoad();
	load();
});
function selectLoad() {
	var html = "";
	$.ajax({
		url : '/common/dict/type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						name : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

function reLoad111() {
    $("#exampleTable").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
    load();
}


function load() {
	console.log("load")
	//selectLoad();
	$('#exampleTable')
		.bootstrapTable(
			{
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                singleSelect : false, // 设置为true将禁止多选
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
                        name:$('#searchName').val()
					};
				},
				columns : [

					{
						field : 'id',
						title : '编号'
					},
					{
                        title : '标签名',
						 field : 'name'
                        , align : 'name',
                        formatter : function(value, row, index) {
                            var e = '<span class="treegrid-indent"></span>';
                            if(row.childNum>0){
                                e = '<span class="treegrid-expander glyphicon glyphicon-chevron-right" onclick="openChild('+row.id+',this)"></span>';  // glyphicon-chevron-down
                            }
                            return e+row.name
                        }

					},
					{
						field : 'value',
						title : '数据值',
						width : '100px'
					},
					{
						field : 'type',
						title : '类型'
					},
					{
						field : 'description',
						title : '描述'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_add_h + '" href="#" title="增加"  mce_href="#" onclick="addD(\''
								+ row.type +'\',\''+row.description+'\',\''+row.id
								+ '\')"><i class="fa fa-plus"></i></a> ';
							return e + d +f;
						}
					} ]
			});


}

function reLoad() {
	var opt = {
		query : {
            limit : 10,
            offset : 0,
            name:$('#searchName').val()
		}
	}
	$('#exampleTable').bootstrapTable('refresh',opt);
    // load();
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
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

function addD(type,description,id) {
	console.log(type+"-"+description+"-"+id);
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/'+type+'/'+description+'/'+id // iframe的url
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

function openChild(id,obj,pid){
	var className = $(obj).prop("className");
	if(className.indexOf("down")>0){ //已经打开子选项
		console.log("关闭子项");
        $("tr[class*='-"+id+"-']").remove();
        $(obj).removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-right");
        $("span[class*='-"+id+"-']").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-right");
	}else{  //未打开子选项

        if($("tr[class*='-"+id+"-']").length>1){
            $("tr[class*='-"+id+"-']").show();
            $(obj).removeClass("glyphicon-chevron-right").addClass("glyphicon-chevron-down");
		}else{
            addChild(id,obj,pid);
            $(obj).removeClass("glyphicon-chevron-right").addClass("glyphicon-chevron-down");
		}
	}
}

//treegrid-expander glyphicon glyphicon-chevron-down
//treegrid-expander glyphicon glyphicon-chevron-right


function addChild(id,obj,pid){
    console.log("查询子项");
    $.ajax({
        type : 'get',
        data : {
            "parentId" : id
        },
        url : prefix + '/getChild',
        success : function(rows) {
            $.each(rows, function(i, row) {
                //var html = '<tr data-index="" class="pid-'+row.id+' pid-'+row.parentId+'">';
                var html ='';
                var parentTrId = $(obj).parent().parent().prop("className");
				if(parentTrId!=null && parentTrId!=""){
                    html += '<tr data-index="" class="'+parentTrId+'-'+row.id+'">';
				}else{
                    html += '<tr data-index="" class="pid-'+row.parentId+'-'+row.id+'">';
				}
				html += '<td style="">'+row.id+'</td>';
				html += '<td style="">';
                html += '<span class="treegrid-expander" ></span>';
                if(parentTrId!=null && parentTrId!=""){
                    var num = parentTrId.split('-').length-1;
                    for(var i=0;i<num;i++){
                        html += '<span class="treegrid-expander" ></span>';
					}
				}
				if(row.childNum!=null && row.childNum>0){
                    html += '<span class="treegrid-expander glyphicon glyphicon-chevron-right" onclick="openChild('+row.id+',this,'+row.parentId+')"></span>';
				}else{
                    html += '<span class="treegrid-expander" ></span>';
				}
				html += row.name+'</td>';
				html += '<td style="width: 100px; ">'+row.value+'</td>';
				html += '<td style="">'+row.type+'</td>';
				html += '<td style="">'+row.description+'</td>';
				html += '<td style="text-align: center; ">';
				html += '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="编辑" onclick="edit(\''+row.id+'\')">';
				html += '<i class="fa fa-edit"></i></a> ';
				html += '<a class="btn btn-warning btn-sm " href="#" title="删除" mce_href="#" onclick="remove(\''+row.id+'\')">';
				html += '<i class="fa fa-remove"></i></a> ';
				html += '<a class="btn btn-success btn-sm " href="#" title="增加" mce_href="#" onclick="addD(\''+row.type+'\',\''+row.description+'\',\''+row.id+'\')">';
				html += '<i class="fa fa-plus"></i></a> </td></tr>';
				$(obj).parent().parent().after(html)

            });
        }
    });
}