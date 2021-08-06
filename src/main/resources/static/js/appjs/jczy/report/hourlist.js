
var prefix = "/webapi/jjtjxxll"
$(function() {
    load();
});

function load() {

}
function reLoad() {
    $.ajax({
        url: prefix + "/getZbFile", // 服务器数据的加载地址
        type: "get",
        data: {
            startDate:$('#startDate').val(),
            endDate:$('#endDate').val()
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
            startDate:$('#startDate').val(),
            endDate:$('#endDate').val()
        },
        success: function (r) {
            if(r !=null && r !=""){
                window.location.href = prefix+'/getUpload?filename=值班信息'+'&templeteName='+r+''
            }
        }
    });
}