var prefix = "/jqxx/taskManage"
$(function () {
    getTreeData();
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        xm: $('#searchName').val(),
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
                        field: 'dpdzName',
                        title: '出动单类型'
                    },
                    {
                        field: 'jqbh',
                        title: '警情编号'
                    },
                    {
                        field: 'cdzt',
                        title: '出动状态',
                        formatter: function (value, row, index) {
                            if (value == '0') {
                                return '未出动';
                            } else if (value == '1') {
                                return '已出动';
                            }
                        }
                    },
                    {
                        field: 'jieJingYuanName',
                        title: '接警员'
                    },
                    {
                        field: 'jqflLabel',
                        title: '警情类型'
                    },
                    {
                        field: 'ddmc',
                        title: '地址'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="查看" onclick="view(\''
                                + row.dpdzTywysbm
                                + '\')"><i class="fa fa-eye"></i></a> ';
                            return e;
                        }
                    }]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function view(id) {
    layer.open({
        type: 2,
        title: '查看',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['90%', '90%'],
        content: prefix + '/view/' + id // iframe的url
    });
}

function exportData(){
    layer.open({
        type: 2,
        title: '导出',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['50%', '50%'],
        content: prefix + '/exportDataForm' // iframe的url
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
    //data.node.original.attributes.xfjyjgTywysbm
    if (data.selected == -1) {
        var opt = {
            query: {
                deptId: '',
            }
        }
        $('#exampleTable').bootstrapTable('refresh', opt);
    } else {
        var opt = {
            query: {
                deptId: data.selected[0],
            }
        }
        $('#exampleTable').bootstrapTable('refresh', opt);
    }

});