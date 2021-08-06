
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

    $('#startDate').val(s1);
    $('#endDate').val(s2);
    reLoad();
}
function reLoad() {
    $.ajax({
        url: prefix + "/getZbFile", // 服务器数据的加载地址
        type: "get",
        data: {
            startDate:$('#startDate').val()+" "+" 06:00:00",
            endDate:$('#endDate').val()+" "+" 06:00:00"
        },
        success: function (r) {
            $('#time').html(r.time);
            $('#zd1').html(r.zd1);
            $('#zd2').html(r.zd2);
            $('#zd3').html(r.zd3);
            $('#zd4').html(r.zd4);
            $('#zd5').html(r.zd5);
        }

    });
}


function Excel() {

    $.ajax({
        url: prefix + "/getFile", // 服务器数据的加载地址
        type: "get",
        data: {
            startDate:$('#startDate').val()+" "+" 06:00:00",
            endDate:$('#endDate').val()+" "+" 06:00:00"
        },
        success: function (r) {
            if(r !=null && r !=""){
                window.location.href = prefix+'/getUpload?filename=值班信息'+'&templeteName='+r+''
            }
        }
    });
}