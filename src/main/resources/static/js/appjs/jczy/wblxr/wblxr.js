var prefix = "/jczy/wblxr"
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
                        xm: $('#searchName').val()
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
                        field: 'dh',
                        title: '电话'
                    },
                    {
                        field: 'dz',
                        title: '地址'
                    },
                    {
                        field: 'sr',
                        title: '生日'
                    },
                    {
                        field: 'yx',
                        title: '邮箱'
                    },
                    {
                        field: 'bz',
                        title: '备注'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.wblxrId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.wblxrId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.wblxrId
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
                'wblxrId': id
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
            ids[i] = row['wblxrId'];
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

function Excel() {

    $.ajax({
        url: prefix + "/wblxrExcel", // 服务器数据的加载地址
        type: "get",
        data: {

            'xm': $("#searchName").val()

        },
        success: function (r) {
            exportList = r.data
            // 列标题，逗号隔开，每一个逗号就是隔开一个单元格
            let str = `姓名,电话,地址,生日,邮箱,备注\n`;
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
            link.download =  "外部通讯录数据表.csv";
            link.click();
        }

    });
}

//下载excel
function downExcel(){
    window.location.href = "/jczy/wblxr/downExcel";//在后台代码里
}
/**
 * 点击预览，导入excel
 */
function setImg(obj){//用于进行excel上传，返回地址
    var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        return false;
    }
    if(!/\.(?:xls|xlsx)$/.test(f))
    {
        alertLayel("类型必须是excel表格(.xls|xlsx)格式");
        $(obj).val('');
        return false;
    }
    var data = new FormData();
    $.each($(obj)[0].files,function(i,file){
        data.append('file', file);
    });
    $.ajax({
        type: "POST",
        url:  prefix + "/uploadImg",
        data: data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType:"json",
        success: function(suc) {
            if(suc.code==0){
                $("#excelUrl").val(suc.message);//将地址存储好
                $("#excelUrlShow").val(suc.message);//显示excel
            }else{
                alertLayel("上传失败");
                $("#url").val("");
                $(obj).val('');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alertLayel("上传失败，请检查网络后重试");
            $("#url").val("");
            $(obj).val('');
        }
    });
}

/**
 * 导入excel数据
 */
function insertExcel() {
    var url = $("#excelUrlShow").val();
    if (url == null || url == '') {
        alertLayel("请选择Excel文件");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/jczy/wblxr/insertUserExcel?url="+encodeURI(url),
        dataType:"json",
        success: function(obj) {
            if (obj.code == 3) {
                alert("Excel中没有数据");//无数据
            }else{
                alert("导入成功");//导入成功
                reLoad()
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            reLoad()
        }
    });
}
