var prefix = "/back/xfzl";
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
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
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                        gjc: $('#searchName').val(),
                        state: $('#searchDate').val()
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
                    {
                        field: 'xfzlId',
                        title: '主键id',
                        visible: false
                    },
                    /* {
                         field: 'number',
                         title: '序号',
                         formatter: function (value, row, index) {
                             //获取每页显示的数量
                             var pageSize = $('#exampleTable').bootstrapTable('getOptions').pageSize;
                             //获取当前是第几页
                             var pageNumber = $('#exampleTable').bootstrapTable('getOptions').pageNumber;
                             //返回序号，注意index是从0开始的，所以要加上1
                             return pageSize * (pageNumber - 1) + index + 1;
                         }
                     },*/{
                        field: 'orderNum',
                        title: '排序',
                        formatter: function (value, row, index) {
                            var html = `<select class="form-control"  onchange="changeOrderNum('${row.xfzlId}',this)">`;
                            for (var i = 0; i < 11; i++) {
                                if (i == row.orderNum) {
                                    html += `<option value="`+ i + `" selected>`+ i +  `</option>`
                                } else {
                                    html += `<option value="`+ i + `">`+ i +  `</option>`
                                }
                            }
                            html += `</select>`;
                            return html;
                        }
                    },
                    {
                        field: 'bt',
                        title: '案例标题'
                    },
                    {
                        field: 'lx',
                        title: '类型',
                        formatter: function (value, row, index) {
                            if (row.lx == '1') {
                                return "视频";
                            } else if (row.lx == '2') {
                                return "图片";
                            } else if (row.lx == '3') {
                                return "文本";
                            } else if (row.lx == '4') {
                                return "音频";
                            }
                        }
                    },
                    {
                        field: 'zt',
                        title: '状态 ',
                        formatter: function (value, row, index) {
                            var html = ``;
                            html += `<select class="form-control" ` + s_changeZt + ` onchange="changeZt('${row.xfzlId}',this)">`;
                            if (value == '0') {
                                html += `<option value='0' selected="true">显示</option>`;
                                html += `<option value='1'>隐藏</option>`;
                            } else {
                                html += `<option value='0'>显示</option>`;
                                html += `<option value='1' selected="true">隐藏</option>`;
                            }
                            html += `</select>`;
                            return html;
                        }
                    },
                    {
                        field: 'llcs',
                        title: '浏览次数'
                    },
                    {
                        field: 'dzcs',
                        title: '点赞次数'
                    },

                    {
                        field: 'cpersonUserName',
                        title: '创建人'
                    },
                    {
                        field: 'fbsj',
                        title: '发布时间'
                    },
                    {
                        field: 'cdate',
                        title: '创建时间'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.xfzlId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.xfzlId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
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
                'xfzlId': id
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
            ids[i] = row['xfzlId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove?t=' + Math.random(),
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


function changeZt(id, thiz) {
    $.ajax({
        cache: false,
        type: "POST",
        url: "/back/xfzl/updateShowZt?t=" + Math.random(),
        data: {
            "xfzlId": id,
            "zt": thiz.value
        },
        async: false,
        error: function (request) {
            layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();

            } else {
                layer.alert(data.msg)
            }

        }
    });
}


function changeOrderNum(id, thiz) {
    $.ajax({
        cache: false,
        type: "POST",
        url: "/back/xfzl/updateShowOrderNum?t=" + Math.random(),
        data: {
            "xfzlId": id,
            "orderNum": thiz.value
        },
        async: false,
        error: function (request) {
            layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();

            } else {
                layer.alert(data.msg)
            }

        }
    });
}
