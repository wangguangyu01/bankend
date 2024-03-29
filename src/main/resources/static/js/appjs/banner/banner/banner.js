var prefix = "/wxBanner/banner"
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
                        bannerNanme:$.trim($('#searchName').val())
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
                    {
                        visible : false,
                        field : 'id',
                        title : ''
                    },
                    // {
                    //     field: 'px',
                    //     title: '排序',
                    //     formatter: function (value, row, index) {
                    //         var html = ``;
                    //         html += `<select class="form-control" onchange="changePx('${row.bt}', '${row.scBannerId}',this)">`;
                    //
                    //         for(var i=1;i<11;i++){
                    //             if(i==value){
                    //                 html += `<option value='${i}' selected="true">${i}</option>`
                    //             }else{
                    //                 html += `<option value='${i}'>${i}</option>`
                    //             }
                    //         }
                    //         html += `</select>`
                    //         return html;
                    //     }
                    // },
                    {
                        field: 'bannerNanme',
                        title: '标题'
                    },
                    {
                        field: 'bannerType',
                        title: '微信滚动广告类型',
                        formatter: function (value, row, index) {
                            console.log("row.value--->"+value);
                            if(value=='1'){
                                return '精彩时刻';
                            }else if (value=='2') {
                                return '最新活动';
                            }
                        }
                    },
                    {
                        field: 'createTime',
                        title: '发布时间'
                    }
                    // , {
                    //     title: '操作',
                    //     field: 'id',
                    //     align: 'center',
                    //     formatter: function (value, row, index) {
                    //         var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                    //             + row.id
                    //             + '\')"><i class="fa fa-edit"></i></a> ';
                    //         var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
                    //             + row.id
                    //             + '\')"><i class="fa fa-remove"></i></a> ';
                    //         return e + d;
                    //     }
                    // }
                    ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function addFl() {
    layer.open({
        type: 2,
        title: '添加分类',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '300px'],
        content: '/banner/bannerLx/add' // iframe的url
    });
}
function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['500px', '400px'],
        content: prefix + '/add' // iframe的url
    });
}
function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '750px'],
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
                'scBannerId': id
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
            ids[i] = row['scBannerId'];
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

function changePx(bt,id,thiz) {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/banner/banner/update",
        data : {
            "scBannerId":id,
            "px":thiz.value,
            "bt": bt
        },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();

            } else {
                layer.alert(data.msg)
            }

        }
    });

}

function changeZt(bt,id,thiz) {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/banner/banner/update",
        data : {
            "scBannerId":id,
            "zt":thiz.value,
            "bt":bt
        },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();

            } else {
                layer.alert(data.msg)
            }

        }
    });
}
