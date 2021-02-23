
{

}
/**
 * 记住我.js
 * @type {{options: {remembermeKey: string}, remembermeList: Array, onCheck: remembermeOptions.onCheck, onUncheck: remembermeOptions.onUncheck}}
 */
var remembermeOptions  = {
    remembermeList:[],
    options:{remembermeKey:"",checkCallBack:null,onLoadSuccessCallBack:null},
    onCheck:function (row) {
        var remembermeKey = this.options.remembermeKey;
        var dataIndex = this.remembermeList.findIndex(item=>{
            return item[remembermeKey] == row[remembermeKey]
        })
        if(dataIndex == -1){
            this.remembermeList.push(row);
        }
        localStorage.setItem("remembermeDataList",JSON.stringify(this.remembermeList))
        this.options.checkCallBack(this.remembermeList);
    },
    onUncheck:function(row){
        var remembermeKey = this.options.remembermeKey;
        var dataIndex = this.remembermeList.findIndex(item=>{
            return item[remembermeKey] == row[remembermeKey]
        })
        if(dataIndex !=-1){
            this.remembermeList.splice(dataIndex,1);
        }
        localStorage.setItem("remembermeDataList",JSON.stringify(this.remembermeList))
        this.options.checkCallBack(this.remembermeList);
    },
    onLoadSuccess: function(data) {
        var checkRowIndexList = [];
        var remembermeKey = this.options.remembermeKey;
        var remembermeStr = localStorage.getItem("remembermeDataList");
        if(remembermeStr && remembermeStr!=''){
            var remembermelist = JSON.parse(remembermeStr);
            if(remembermelist.length > 0){
                this.remembermeList = remembermelist;
            }
        }
        for(var i  in this.remembermeList){
            var dataIndex = data.rows.findIndex(item=>{
                return item[remembermeKey] == this.remembermeList[i][remembermeKey]
            });
            if(dataIndex!=-1){
                checkRowIndexList.push(dataIndex);
            }
        }
        this.options.onLoadSuccessCallBack(checkRowIndexList);
    },
}

