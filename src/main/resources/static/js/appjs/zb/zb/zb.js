var prefix = "/zb/zb"
var zid; //deptid
var columns;
var zwlx; // 职位类型
var xfjyjgTywysbm; //消防救援机构通用唯一识别码
var selectZwId; //选择的职位id
var selectRq;//选择的日期
$(function () {
    getTreeData();
    //load();
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
                pagination: false, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                // pageSize: 10, // 如果设置了分页，每页数据条数
                // pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        // limit: params.limit,
                        // offset: params.offset,
                        deptId: zid,
                        zwlx:zwlx,
                        startDate:$('#startDate').val(),
                        endDate:$('#endDate').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                // columns: [
                //     {
                //         checkbox: true
                //     },
                //     {
                //         field: 'zbId',
                //         title: '值班id'
                //     },
                //     {
                //         field: 'xfjyjgTywysbm',
                //         title: '消防救援机构_通用唯一识别码'
                //     },
                //     {
                //         field: 'xfjyryTywysbm',
                //         title: '消防救援人员_通用唯一识别码'
                //     },
                //     {
                //         field: 'zbrq',
                //         title: '值班日期'
                //     },
                //     {
                //         field: 'zbzwId',
                //         title: '值班职务id'
                //     },
                //     {
                //         field: 'cdate',
                //         title: '创建时间'
                //     },
                //     {
                //         field: 'status',
                //         title: '状态'
                //     },
                //     {
                //         field: 'cperson',
                //         title: '创建人'
                //     },
                //     {
                //         title: '操作',
                //         field: 'id',
                //         align: 'center',
                //         formatter: function (value, row, index) {
                //             var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                //                 + row.zbId
                //                 + '\')"><i class="fa fa-edit"></i></a> ';
                //             var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                //                 + row.zbId
                //                 + '\')"><i class="fa fa-remove"></i></a> ';
                //             var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                //                 + row.zbId
                //                 + '\')"><i class="fa fa-key"></i></a> ';
                //             return e + d;
                //         }
                //     }]
                columns:eval(columns)
            });
}
function reLoad() {
    if($('#startDate').val()==""){
        layer.msg("请选择开始时间！");
        return false;
    }
    if($('#endDate').val()==""){
        layer.msg("请选择结束时间！");
        return false;
    }
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
function remove(rq) {
    var d = new Date(rq);
    var d1 = new Date(dateFormat("YYYY-mm-dd", new Date));
    if(d.getTime()<d1.getTime()){
        layer.msg("不允许编辑过去日期的值班！");
        return false;
    }

    layer.confirm('此操作将永久删除'+rq+'的值班信息, 是否继续？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/batchRemove",
            type: "post",
            data: {
                'rq': rq,
                "xfjyjgTywysbm":xfjyjgTywysbm
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

    selectNode();
}

function selectNode() {
    $("#jstree").bind('ready.jstree',function (obj, e) {
        $("#jstree").jstree('select_node',userDeptId)
    })
}

$('#jstree').on("changed.jstree", function (e, data) {
    $("#exampleTable").bootstrapTable('destroy');  // 销毁原表格
    zid = data.selected[0];
    xfjyjgTywysbm = data.node.original.attributes.xfjyjgTywysbm;
    console.log(xfjyjgTywysbm);
    getColumnsAndZwlx();
    //load();
    $("#userTable").bootstrapTable('destroy');  // 销毁原表格
    userLoad();
});

function getColumnsAndZwlx() {
    $.ajax({
        type : "GET",
        url : prefix+"/getColumnsAndZwlx/"+zid,
        success : function(r) {
                columns=r.columns;
                zwlx = r.zwlx;
                load();
        }
    });
}



function zwadd() {
    layer.open({
        type: 2,
        title: '职务管理',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content: '/zb/zbzw/'+zid, // iframe的url
        cancel: function(){
            // 右上角关闭事件的逻辑
            // $("#exampleTable").bootstrapTable('destroy');
            // getColumnsAndZwlx();
        }
    });
}

function zwFormatter(value, row, index, field) {
    var html = '<div id="' + value.zbzwId + '_' + row.rq + '" class="tagClass" onclick=\'test(this,"' + index + '","' + value.zbzwId + '","' + value + '","' + row.rq + '")\'>';
    if(value.zbUserList){
        for (var i in value.zbUserList) {
            html += '<span class="label label-info">' + value.zbUserList[i].xm + '<i class="fa fa-times tagClose" onclick=\'removeUser("' + value.zbUserList[i].zbId + '","' + value.zbUserList[i].xm + '","' + row.rq + '")\'></i></span><br>';
        }
    }
    html += "</div>"
    return html;
}

function test(el,index,zbzwId,value,rq){
    var d = new Date(rq);
    var d1 = new Date(dateFormat("YYYY-mm-dd", new Date));
    if(d.getTime()<d1.getTime()){
        layer.msg("不允许编辑过去日期的值班！");
        return false;
    }

    $(".tagClass").each(function(){
        $(this).parent().removeClass("active");
    })
    $(el).parent().addClass("active");

    selectZwId = zbzwId; //选择的职位id
    selectRq = rq; //选择的日期

    var opt = {
        url: "/jczy/xfjyry//list"
    };
    $("#userTable").bootstrapTable('refresh', opt);

}
function removeUser(id,name,rq) {
    event.stopPropagation();
    var d = new Date(rq);
    var d1 = new Date(dateFormat("YYYY-mm-dd", new Date));
    if(d.getTime()<d1.getTime()){
        layer.msg("不允许编辑过去日期的值班！");
        return false;
    }
    layer.confirm('确定要删除'+name+'当前职务的值班数据吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'zbId': id
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



function userLoad() {
    $('#userTable')
        .bootstrapTable(
            {
                 method: 'get', // 服务器数据的请求方式 get or post
                // url: "/jczy/xfjyry/list", // 服务器数据的加载地址
                 iconSize: 'outline',
                 toolbar: '#exampleToolbar',
                 striped: true, // 设置为true会有隔行变色效果
                 dataType: "json", // 服务器返回的数据类型
                 pagination: true, // 设置为true会在底部显示分页条
                 singleSelect: true, // 设置为true将禁止多选
                 pageSize: 10, // 如果设置了分页，每页数据条数
                 pageNumber: 1, // 如果设置了分布，首页页码
                // showColumns: false, // 是否显示内容下拉框（选择显示的列）
                 sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                rowStyle:function(row,index){
                    return {css: {"cursor": "pointer"}}
                },
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        xm: $('#xm').val(),
                        sjszjgTywysbm:xfjyjgTywysbm
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
                        field: 'xh',
                        title: '序号',
                        formatter: function (value, row, index) {
                            //获取每页显示的数量
                            var pageSize = $('#userTable').bootstrapTable('getOptions').pageSize;
                            //获取当前是第几页
                            var pageNumber = $('#userTable').bootstrapTable('getOptions').pageNumber;
                            //返回序号，注意index是从0开始的，所以要加上1
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {
                        field: 'xm',
                        title: '姓名'
                    },
                    {
                        field: 'xfjyxjb',
                        title: '消防救援衔级别'
                    }]
            });
}
function userReLoad(){
    var opt = {
        url: "/jczy/xfjyry//list"
    };
    $("#userTable").bootstrapTable('refresh', opt);
}

$("#userTable").on("click-row.bs.table", function (e, row, ele) {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/zb/zb/save",
        data : {
            "zbzwId" :selectZwId,
            "zbrq" :selectRq,
            "xfjyryTywysbm" :row.xfjyryTywysbm,
            "xfjyjgTywysbm" :xfjyjgTywysbm
        },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                $('#exampleTable').bootstrapTable('refresh');
                $('#exampleTable').on("load-success.bs.table",function () {
                    var id = selectZwId + "_" + selectRq
                    $("#"+id).parent().addClass("active");
                })
            } else {
               layer.alert(data.msg)
            }
        }
    });

})

function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

function Excel() {
    // 通过创建a标签实现
    const link = document.createElement("a");
    link.href = prefix + "/zbExcel?deptId="+zid+"&zwlx="+zwlx+"&startDate="+$('#startDate').val()+"&endDate="+$('#endDate').val()
    // 对下载的文件命名
    link.click();
}