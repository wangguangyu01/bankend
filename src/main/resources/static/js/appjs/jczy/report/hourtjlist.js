
var prefix = "/webapi/jjtjxxll"
$(function() {
    load();
});

function load() {
    var day1 = new Date();
    day1.setTime(day1.getTime()-48*60*60*1000);
     var s1 = day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate();

     var day2 = new Date();
    day2.setTime(day2.getTime()-24*60*60*1000);
    var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();

     $('#startDate').val(s1+" 00:00:00");
     $('#endDate').val(s2+" 23:59:59");

    setTime();

    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/getHourList", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : false, // 设置为true会在底部显示分页条
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
                columns : [
                    {
                        field : 'hour',
                        title : '时间'
                    },
                    {
                        field : 'jqcount',
                        title : '警情数量'
                    },
                    {
                        field : 'bfb',
                        title : '百分比'
                    },
                    {
                        field : 'hzcount',
                        title : '火灾扑救数量'
                    },
                    {
                        field : 'count',
                        title : '出动次数'
                    },
                    {
                        field : 'clcount',
                        title : '出动车辆'
                    },
                    {
                        field : 'xycount',
                        title : '出动人数',
                    },
                   ]
            });
}
function reLoad() {
     setTime();
    $('#exampleTable').bootstrapTable('refresh');
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

    return year + '年' + month + '月' + date + '日'+hours+"时"+minute+"分"+second+"秒";
}
function Excel() {
   var time=$('#time').html();
   var gettime= $('#gettime').html();
    $.ajax({
        url: prefix + "/getHourFile", // 服务器数据的加载地址
        type: "get",
        data: {
            time:time,
            gettime:gettime,
            org:"临沂消防救援支队",
            startDate:$('#startDate').val(),
            endDate:$('#endDate').val()
        },
        success: function (r) {
            if(r !=null && r !=""){
                window.location.href = prefix+'/getUpload?filename=警情时段分布统计'+'&templeteName='+r+"";
            }
        }
    });
}