var prefix = "/iot/controllerPort"
var xfjyjgTywysbm;
$(function () {
    getTreeData();
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        xfjyjgTywysbm: xfjyjgTywysbm,
                        name:$('#searchName').val()
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    /*{
                        field: 'id',
                        title: '主键'
                    },*/
                    {
                        field: 'channelNumber',
                        title: '通道号'
                    },
                    {
                        field: 'deptName',
                        title: '消防救援机构'
                    },
                    {
                        field: 'controllerName',
                        title: '控制器名称'
                    },
                    {
                        field: 'address',
                        title: '寄存器地址'
                    },
                    {
                        field: 'portAddress',
                        title: '端口地址'
                    },
                    {
                        field: 'restorationTime',
                        title: '复位时间'
                    },
                    {
                        field: 'createTime',
                        title: '创建时间'
                    },
                    {
                        field: 'updateTime',
                        title: '更新时间'
                    },
                    {
                        field: 'statusName',
                        title: '状态'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    var deptId = $("#jstree li[aria-selected='true']").attr("id");
    if (deptId != null && deptId != null && deptId != "") {
        //var deptId = $("#jstree li [aria-selected='true']").attr("id");
        judge_add(deptId);
    } else {
        parent.layer.alert("请选择“消防救援站”后再操作！");
    }
    return;
}

//判断当前选中的组织机构是否为消防救援站
function judge_add(deptId) {
    $.ajax({
        type: "POST",
        url: "/system/sysDept/isXfjyz",
        data: {
            deptId: deptId
        },// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("选择的消防救援机构不是“消防救援站“");
        },
        success: function (data) {
            if (data.code == "0") {
                layer.open({
                    type: 2,
                    title: '增加',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['90%', '90%'],
                    content: prefix + '/add?deptId=' + deptId // iframe的url
                });
            } else {
                parent.layer.alert("选择的消防救援机构不是“消防救援站“");
            }

        }
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
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
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}

function getTreeData() {
    $.ajax({
        type: "GET",
        url: "/system/sysDept/tree",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "plugins": ["search"]
    });
    $('#jstree').jstree().open_all();
}

$('#jstree').on("changed.jstree", function (e, data) {
    $("#exampleTable").bootstrapTable('destroy');  // 销毁原表格
    xfjyjgTywysbm = data.node.original.attributes.xfjyjgTywysbm;
    load();

});