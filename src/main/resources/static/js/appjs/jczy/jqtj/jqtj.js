
var prefix = "/jczy/jqtj"
var zid;
$(function() {
    getTreeData();
    load();
});

function load() {

    var day1 = new Date();
    day1.setTime(day1.getTime()-24*60*60*1000);
    var s1 = day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate();

    var day2 = new Date();
    day2.setTime(day2.getTime()-24*60*60*1000);
    var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();

    $('#startDate').val(s1+" 00:00:00");
    $('#endDate').val(s2+" 23:59:59");
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        startDate:$('#startDate').val(),
                        endDate:$('#endDate').val(),
                        deptId : zid
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        field : 'mc',
                        title : '警情类型名称'
                    },
                    {
                        field : 'count',
                        title : '数量'
                    },
                    {
                        field : 'percentage',
                        title : '百分比'
                    }]
            });
}
function reLoad() {
    setTime()
    $('#exampleTable').bootstrapTable('refresh');
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
}
$('#jstree').on("changed.jstree", function(e, data) {
    $("#exampleTable").bootstrapTable('destroy');  // 销毁原表格
    zid = data.selected[0],
        load();
});
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
            url : prefix+"/remove",
            type : "post",
            data : {
                'kbmbId' : id
            },
            success : function(r) {
                if (r.code==0) {
                    layer.msg(r.msg);
                    reLoad();
                }else{
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
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['kbmbId'];
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
    }, function() {

    });
}
function  setTime() {
    var myDate = new Date();
    myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    myDate.getMonth();       //获取当前月份(0-11,0代表1月)
    myDate.getDate();        //获取当前日(1-31)
    var gettime=myDate.getFullYear()+"年"+(myDate.getMonth()+1)+"月"+myDate.getDate()+"日";
    $('#gettime').html(gettime);
    var t1 = $('#startDate').val();
    var t2= $('#endDate').val();
    if(t1 !="" &&t2 !="" && t1 !=null &&  t2 !=null){
        var startDate=getYMDHMS (t1);
        var  endDate=getYMDHMS (t2);
        $('#time').html(startDate+"--"+endDate);
    }

}
function  getYMDHMS (timesrtamp) {
    var time = new Date(timesrtamp)
    var year = time.getFullYear()
    var month = (time.getMonth() + 1).toString().padStart(2, '0')
    var date = (time.getDate()).toString().padStart(2, '0')
    var hours = (time.getHours()).toString().padStart(2, '0')
    var minute = (time.getMinutes()).toString().padStart(2, '0')
    var second = (time.getSeconds()).toString().padStart(2, '0')

    return year + '年' + month + '月' + date + '日';
}
function Excel() {

    $.ajax({
        url: prefix + "/jqtjExcel", // 服务器数据的加载地址
        type: "get",
        data: {
            startDate:$('#startDate').val(),
            endDate:$('#endDate').val(),
            deptId : zid
        },
        success: function (r) {
            exportList = r.data
            // 列标题，逗号隔开，每一个逗号就是隔开一个单元格
            let str = `警情类型,数量,百分比\n`;
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
            link.download =  "警情统计数据表.csv";
            link.click();
        }

    });
}