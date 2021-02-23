
var prefix = "/jczy/xfcl"
$(function() {
    getSelectAll("XFZBLXDM","XFZBLXDM-DIV","xfzblxdm","xfzblxdm-title");
    load();
});

function load() {
    remembermeOptions.options ={
        remembermeKey:"xfclTywysbm",
        checkCallBack:function (remembermeList) {
            var htmlStr = "";
            for(var i in remembermeList){
                htmlStr+="<span class=\"label label-info\">"+remembermeList[i].xfzblx+"</span>&nbsp;"
            }
            $("#checkInfo").html(htmlStr);
        },
        onLoadSuccessCallBack: function(checkRowIndexList) {
            for(var i  in checkRowIndexList){
                $('#exampleTable').bootstrapTable('check', checkRowIndexList[i]);
            }
            if(checkRowIndexList.length ==0 ){
                var remembermeStr = localStorage.getItem("remembermeDataList");
                if(remembermeStr && remembermeStr!=''){
                    var remembermelist = JSON.parse(remembermeStr);
                    if(remembermelist.length > 0){
                        var htmlStr = "";
                        for(var i in remembermelist){
                            htmlStr+="<span class=\"label label-info\">"+remembermelist[i].xfzblx+"</span>&nbsp;"
                        }
                        $("#checkInfo").html(htmlStr);
                    }
                }
            }
        }
    }
    $('#exampleTable')
        .bootstrapTable(Object.assign(remembermeOptions,{
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/carlist", // 服务器数据的加载地址
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
                pageSize : 5, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        xfzblxdmname:$('#searchName').val(),  //车辆属性名称查询项
                        xfjyjgTywysbm:xfjyjgTywysbm,
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
                        checkbox : true
                    },
                    {
                        field: 'xfzblx',
                        title: '消防装备器材分类'
                    },
                    {
                        field: 'xfclzzgn',
                        title: '消防车辆作战功能'
                    },
                    {
                        field: 'xfcldj',
                        title: '消防车辆等级'
                    },
                    {
                        field: 'jdchphm',
                        title: '机动车号牌号码'
                    },
                ]
            }));
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function resetPwd(id) {
}
function batchSelect() {
    var remembermeStr = localStorage.getItem("remembermeDataList");
    var remembermelist = []
    if(remembermeStr && remembermeStr!=''){
        remembermelist = JSON.parse(remembermeStr);
    }
    var rows = remembermelist
    if (rows.length == 0) {
        layer.msg("请选择需要添加的消防车辆数据");
        return;
    }
    layer.confirm("确认要添加选中的 " + rows.length + " 个消防车辆吗?", {
        btn : [ '确定', '取消' ]
    }, function() {
        $('#xfclTywysbm', window.parent.document).val("");
        $('#addxfcl', window.parent.document).empty();
        $.each(rows, function(i, row) {
            var id = row['xfclTywysbm'];
            $('#xfclTywysbm', window.parent.document).val($('#xfclTywysbm', window.parent.document).val()+id+",");
            if (i != 0 && (i + 1) % 4 == 0) {
                $('#addxfcl', window.parent.document).append("<span style='line-height:30px;' id='zzdy"+id+"' name='zzdy"+id+"' class='label label-info'>"+row['xfzblx']+"</span>&nbsp;</br>")
            }else{
                $('#addxfcl', window.parent.document).append("<span id='zzdy"+id+"' name='zzdy"+id+"' class='label label-info'>"+row['xfzblx']+"</span>&nbsp;")
            }
        });
        localStorage.setItem("remembermeDataList",JSON.stringify(remembermelist));
        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
        parent.layer.close(index);
    }, function() {

    });
}