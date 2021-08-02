
var prefix = "/jczy/jqtj"
$(function() {
	load();
});

function load() {
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
									checkbox : true
								},
																{
									field : 'jqTywysbm', 
									title : '警情_通用唯一识别码' 
								},
																{
									field : 'bjTywysbm', 
									title : '报警_通用唯一识别码' 
								},
																{
									field : 'mc', 
									title : '名称' 
								},
																{
									field : 'ddmc', 
									title : '地点名称' 
								},
																{
									field : 'jqflydm', 
									title : '警情分类与代码' 
								},
																{
									field : 'jqdjdm', 
									title : '警情等级代码' 
								},
																{
									field : 'jqztLbdm', 
									title : '警情状态类别代码' 
								},
																{
									field : 'jqztRqsj', 
									title : '日期时间' 
								},
																{
									field : 'jqdxTywysbm', 
									title : '警情对象通用唯一识别码' 
								},
																{
									field : 'jqdxMc', 
									title : '警情对象名称' 
								},
																{
									field : 'jqdxJyqk', 
									title : '警情对象简要情况' 
								},
																{
									field : 'jqJyqk', 
									title : '警情j简要情况' 
								},
																{
									field : 'jqbslbdm', 
									title : '警情标识类别代码' 
								},
																{
									field : 'lc', 
									title : '楼层' 
								},
																{
									field : 'dqjd', 
									title : '地球经度' 
								},
																{
									field : 'dqwd', 
									title : '地球纬度' 
								},
																{
									field : 'bjrxm', 
									title : '报警人姓名' 
								},
																{
									field : 'bjdh', 
									title : '报警电话' 
								},
																{
									field : 'lxdh', 
									title : '联系电话' 
								},
																{
									field : 'bjfslbdm', 
									title : '报警方式类别代码' 
								},
																{
									field : 'bjsj', 
									title : '报警时间' 
								},
																{
									field : 'lasj', 
									title : '立案时间' 
								},
																{
									field : 'jasj', 
									title : '结案时间' 
								},
																{
									field : 'jsmlsj', 
									title : '接受命令时间' 
								},
																{
									field : 'cdsj', 
									title : '出动时间' 
								},
																{
									field : 'dcsj', 
									title : '到场时间' 
								},
																{
									field : 'zdzksj', 
									title : '战斗展开时间' 
								},
																{
									field : 'cssj', 
									title : '出水时间' 
								},
																{
									field : 'hskzsj', 
									title : '火势控制时间' 
								},
																{
									field : 'jbpmsj', 
									title : '基本扑灭时间' 
								},
																{
									field : 'tssj', 
									title : '停水时间' 
								},
																{
									field : 'gdsj', 
									title : '归队时间' 
								},
																{
									field : 'ztfhsj', 
									title : '中途返回时间' 
								},
																{
									field : 'xfjyjgTywysbm', 
									title : '消防救援机构_通用唯一识别码（接警员所在的机构）' 
								},
																{
									field : 'xzqhdm', 
									title : '行政区划代码' 
								},
																{
									field : 'jzjglxdm', 
									title : '建筑结构类型代码' 
								},
																{
									field : 'ywqkdm', 
									title : '烟雾情况代码' 
								},
																{
									field : 'zhcsdm', 
									title : '灾害场所代码' 
								},
																{
									field : 'rswdm', 
									title : '燃烧物代码' 
								},
																{
									field : 'bkrs', 
									title : '被困人数' 
								},
																{
									field : 'rysw', 
									title : '人员伤亡' 
								},
																{
									field : 'cperson', 
									title : '创建人' 
								},
																{
									field : 'cdate', 
									title : '创建时间' 
								},
																{
									field : 'status', 
									title : '状态' 
								},
																{
									field : 'parentnodeid', 
									title : '父警情主键' 
								},
																{
									field : 'jqbh', 
									title : '警情编号' 
								},
																{
									field : 'jjdz', 
									title : '就近队站（消防救援机构_通用唯一识别码）' 
								},
																{
									field : 'xqdz', 
									title : '辖区队站（消防救援机构_通用唯一识别码）' 
								},
																{
									field : 'jqzllxdm', 
									title : '警情指令类型代码' 
								},
																{
									field : 'bz', 
									title : '备注 受理工单动输入' 
								},
																{
									field : 'ryskdm', 
									title : '人员受困代码' 
								},
																{
									field : 'ghmj', 
									title : '过火面积' 
								},
																{
									field : 'rsqy', 
									title : '燃烧区域' 
								},
																{
									field : 'zhqkdm', 
									title : '灾害情况代码' 
								},
																{
									field : 'zhjtqk', 
									title : '灾害具体情况' 
								},
																{
									field : 'xcjzbqk', 
									title : '现场及周边情况' 
								},
																{
									field : 'szmj', 
									title : '受灾面积' 
								},
																{
									field : 'dljzwshsl', 
									title : '道路、建（构）筑物损坏数量' 
								},
																{
									field : 'yfzh', 
									title : '引发的灾害' 
								},
																{
									field : 'jrzqdlqk', 
									title : '进入灾区道路情况' 
								},
																{
									field : 'jqxxdz', 
									title : '警情详细信息' 
								},
																{
									field : 'mainJqbsdm', 
									title : '主辅警情标识：1：主要警情；2：辅助警情' 
								},
																{
									field : 'zxxw', 
									title : '坐席席位' 
								} ]
					});
}
