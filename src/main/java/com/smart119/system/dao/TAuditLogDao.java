package com.smart119.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.system.domain.TAuditLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Mapper
@Repository
public interface TAuditLogDao extends BaseMapper<TAuditLogEntity> {
//
//    @SelectProvider(type = DataProvider.class, method = "getCountsql")
//    @Results({
//        @Result(property = "counts", column = "counts"),
//        @Result(property = "operationName", column = "operation_name")
//	})
//    List<JSONObject> countResultList(PageRequest<TAuditLogEntity> page);
//
//
//
//	@Select("use information_schema;select round(sum(data_length/1024/1024/1024),2) as data_length_GB, "
//			+ " round(sum(index_length/1024/1024/1024),2) as index_length_GB  "
//			+" from tables where  "
//			+" table_schema='cloudportal' "
//			+" and table_name = 't_audit_log'; use cloudportal; ")
//	@Results({
//        @Result(property = "dataLength", column = "data_length_GB"),
//        @Result(property = "indexLength", column = "index_length_GB")
//	})
//    JSONObject queryMysqlSpace();
//
//
//    class DataProvider{
//
//    	public String getCountsql(PageRequest<TAuditLogEntity> page){
//
//    		String groupByName="";
//
//
//    		StringBuffer sqlOrder =new StringBuffer(" WHERE 1=1 ");
//
//
//
//    		for(QueryParam queryParam:page.getQueryParamList()){
//    			String operation = queryParam.getOperator();
//    			if(operation.equalsIgnoreCase("like")){
//    				sqlOrder.append(" AND "+queryParam.getFieldName()+" "+queryParam.getOperator()+" '"+queryParam.getFieldValue()+"%' ");
//    			}else if(operation.equalsIgnoreCase("gt")){
//    				sqlOrder.append(" AND "+queryParam.getFieldName()+" >= '"+queryParam.getFieldValue()+"' ");
//
//    			}else if(operation.equalsIgnoreCase("lt")){
//    				sqlOrder.append(" AND "+queryParam.getFieldName()+" <= '"+queryParam.getFieldValue()+"' ");
//    			}else if(operation.equalsIgnoreCase("group")){
//    				groupByName = queryParam.getFieldName();
//    			}else{
//    				sqlOrder.append(" AND "+queryParam.getFieldName()+" "+queryParam.getOperator()+" '"+queryParam.getFieldValue()+"' ");
//    			}
//
//    		}
//			sqlOrder.append(" AND USER_NAME  IS NOT NULL AND USER_NAME!='' AND OPERATION_TYPE IS NOT NULL AND OPERATION_TYPE!='' AND IP!='0:0:0:0:0:0:0:1'");
//    		sqlOrder.append(" GROUP BY "+groupByName);
//
//    		StringBuffer sqlOrigin =new StringBuffer("SELECT COUNT("+groupByName+") counts, "+groupByName+" operation_name FROM t_audit_log ");
//    		sqlOrigin.append(sqlOrder);
//
//    		return sqlOrigin.toString();
//    	}
//    }
}
