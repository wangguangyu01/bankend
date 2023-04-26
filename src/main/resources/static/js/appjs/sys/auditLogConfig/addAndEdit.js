// 以下为官方示例
$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
function save() {
    var operationCode = $("#operationType option:selected").attr("code")
    $("#operationCode").val(operationCode);
    var id = $("#id").val();
    var url;
    if(id==null || id=="" || id=='undefined'){
        url = "/sys/tauditlogconfig/add";
    }else{
        url = "/sys/tauditlogconfig/edit";
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : url,
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg(data.msg);
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            url : {
                required : true
            },
            bussiness : {
                required : true,
            }
        },
        messages : {

            url : {
                required : icon + "请输入权限标识"
            },
            bussiness : {
                required : icon + "请输入菜单信息",
            },
            operationType : {
                required : icon + "请选择操作类型",
            },
        }
    })
}