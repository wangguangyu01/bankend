var prefix = "/jczy/txlxfjyry"
var zid;
var exportList;
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
                        deptId: zid,
                        limit: params.limit,
                        offset: params.offset,
                        xm: $('#searchName').val(),
                        sjszjg: $('#sjszjg').val(),
                        xfjyxjbdmmc:$('#xfjyxjbdmmc').val()
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
                        field: 'xh',
                        title: '序号',
                        formatter: function (value, row, index) {
                            //获取每页显示的数量
                            var pageSize = $('#exampleTable').bootstrapTable('getOptions').pageSize;
                            //获取当前是第几页
                            var pageNumber = $('#exampleTable').bootstrapTable('getOptions').pageNumber;
                            //返回序号，注意index是从0开始的，所以要加上1
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {
                        field: 'xm',
                        title: '姓名'
                    },

                    {
                        field: 'ydLxdh',
                        title: '移动电话'
                    },

                    {
                        field: 'bgLxdh',
                        title: '固定电话'
                    },

                    {
                        field: 'xfjyxjb',
                        title: '消防救援衔级别'
                    },
                    {
                        field: 'sjszjg',
                        title: '单位'
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
        area: ['90%', '90%'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['90%', '90%'],
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
                'xfjyryTywysbm': id
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
            ids[i] = row['xfjyryTywysbm'];
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
    zid = data.selected[0],
    load();
});

function Excel() {

    $.ajax({
        url: prefix + "/txlXfjyryExcel", // 服务器数据的加载地址
        type: "get",
        data: {
            deptId: zid,
            'xm': $("#searchName").val(),
            'sjszjg' : $("#sjszjg").val(),
            'xfjyxjbdm' : $("#xfjyxjbdm").val()
        },
        success: function (r) {
            exportList = r.data
            // 列标题，逗号隔开，每一个逗号就是隔开一个单元格
            let str = `姓名,移动电话,办公电话,消防救援级别,单位\n`;
            // 增加\t为了不让表格显示科学计数法或者其他格式
            for(let i = 0 ; i < exportList.length ; i++ ){
                for(const key in exportList[i]){
                    str+=`${exportList[i][key] + '\t'},`;
                }
                str+='\n';
            }
            // encodeURIComponent解决中文乱码
            const uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
            // 通过创建a标签实现
            const link = document.createElement("a");
            link.href = uri;
            // 对下载的文件命名
            link.download =  "通讯录数据表.csv";
            link.click();
        }

    });
}

