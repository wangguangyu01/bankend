
function getSelectById(dictId,objId,selected){
    $.ajax({
        url : "/common/dict/getListByParentId",
        type : "get",
        data : {
            'id' : dictId
        },
        success : function(list) {
            $("#"+objId).empty();
            $("#"+objId).append('<option value="" >--请选择--</option>');
            if(list!=null && list!=undefined && list.length>0){

                $.each(list,function(i,item){
                    if(selected!=null && selected==item.value){
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'" selected="selected">'+item.name+'</option>');
                    }else{
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'">'+item.name+'</option>');
                    }

                });
            }

        }
    });
}



function getSelectByType(type,objId,selected){
    $.ajax({
        url : "/common/dict/getListByParentType",
        type : "get",
        data : {
            'type' : type
        },
        success : function(list) {
            $("#"+objId).empty();
            $("#"+objId).append('<option value="" >--请选择--</option>');
            if(list!=null && list!=undefined && list.length>0){
                $.each(list,function(i,item){
                    if(selected!=null && selected==item.value){
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'" selected="selected">'+item.name+'</option>');
                    }else{
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'">'+item.name+'</option>');
                    }
                });
            }
        }
    });
}


function getSelectByValue(value,objId,selected){
    $.ajax({
        url : "/common/dict/getListByParentValue",
        type : "get",
        data : {
            'value' : value
        },
        success : function(list) {
            $("#"+objId).empty();
            $("#"+objId).append('<option value="" >--请选择--</option>');
            if(list!=null && list!=undefined && list.length>0){

                $.each(list,function(i,item){
                    if(selected!=null && selected==item.value){
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'" selected="selected">'+item.name+'</option>');
                    }else{
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'">'+item.name+'</option>');
                    }
                });
            }
        }
    });
}

function getSelectAll(type,divClass,inputId,spanId){
    $.ajax({
        url : "/common/dict/getChildAll",
        type : "get",
        data : {
            'type' : type
        },
        success : function(list) {
            var html = '<ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">';
            $.each(list,function(n,obj) {

                if(obj.child!=null && obj.child.length>0){
                    html += '<li class="dropdown-submenu"><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
                    html += decomposeChild(obj.child);
                }else{
                    html += '<li><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
                }
                html += '</li>';
            });
            html += '</ul>';
            $("."+divClass).append(html);
            addOnclick(divClass,inputId,spanId);
        }
    });
}

function getSelectAllCallBack(type,divClass,inputId,spanId,callback){
    $.ajax({
        url : "/common/dict/getChildAll",
        type : "get",
        data : {
            'type' : type
        },
        success : function(list) {
            list = list.filter(function (t) {
                return t.id==1168;
            })
            list = [list[0].child[0]];
            var html = '<ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">';
            $.each(list,function(n,obj) {
                if(obj.child!=null && obj.child.length>0){
                    html += '<li class="dropdown-submenu"><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
                    html += decomposeChild(obj.child);
                }else{
                    html += '<li><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
                }
                html += '</li>';
            });
            html += '</ul>';
            $("."+divClass).append(html);
            addOnclick(divClass,inputId,spanId);
            callback();
        }
    });
}

function getSelectAllCallBack1(type,divClass,inputId,spanId,callback){
    $.ajax({
        url : "/common/dict/getChildAll",
        type : "get",
        data : {
            'type' : type
        },
        success : function(list) {
            var html = '<ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">';
            $.each(list,function(n,obj) {
                if(obj.child!=null && obj.child.length>0){
                    html += '<li class="dropdown-submenu"><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
                    html += decomposeChild(obj.child);
                }else{
                    html += '<li><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
                }
                html += '</li>';
            });
            html += '</ul>';
            $("."+divClass).append(html);
            addOnclick(divClass,inputId,spanId);
            callback();
        }
    });
}

function decomposeChild(list){
    var html = '<ul class="dropdown-menu"> ';
    $.each(list,function(n,obj) {
        if(obj.child!=null && obj.child.length>0){
            html += '<li class="dropdown-submenu"><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
            html += decomposeChild(obj.child);
        }else{
            html += '<li><a data-index="'+obj.value+'" data-title="'+obj.name+'">'+obj.name+'</a>'
        }
        html += '</li>';
    });
    html += '</ul>';
    return html
}

function addOnclick(divClass,inputId,spanId){

    $('.'+divClass+' li a').click(function(){
        title = $(this).attr("data-title");
        id = $(this).attr("data-index");
        $("#"+spanId).text(title);
        //$("#category_id").val(id);
        $("#"+inputId).val(id);
        $("#"+inputId).change()
        if($("#childAttr").val()=="yes" && (inputId=="xfzblxdm" || inputId=="xldm") ){
            addChildAttr($("#childAttr").attr("class"));
        }
    })
}

function getSelectByXfjbType(type,objId,selected){
    $.ajax({
        url : "/common/dict/getSelectByXfjbType",
        type : "get",
        data : {
            'type' : type
        },
        success : function(list) {
            if(list!=null && list!=undefined && list.length>0){
                // $("#"+objId).empty();
                // $("#"+objId).append('<option value="" >--请选择--</option>');
                $.each(list,function(i,item){
                    if(selected!=null && selected==item.value){
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'" selected="selected">'+item.name+'</option>');
                    }else{
                        $("#"+objId).append('<option id="'+item.id+'" value="'+item.value+'">'+item.name+'</option>');
                    }
                });
            }
        }
    });
}
