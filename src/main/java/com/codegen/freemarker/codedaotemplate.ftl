<#include "common.ftl">
/*
 * 文件名称: ${_cname}DAO.java
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_daopkg};

import ${_cfullname};
import java.util.List;
import java.util.Map;
<#list obj.daoImports as import>import ${import};
</#list>

/**
 * 功能描述: ${_description}DAO接口类<p>
 * 新增原因: TODO<p>  
 * 新增日期: ${_time} <p>  
 * 
 * @author ${_uname}
 * @version 1.0.0
 */
public interface ${_daoname} {


      Long create(${_cnamedo} ${_clnamedo});


      ${_cnamedo} getBy${obj.primaryKey?cap_first}(${obj.pkJavaType} ${obj.primaryKey});


      ${_cnamedo} getByMap(Map<String, Object> req);


      List<${_cnamedo}> getListByMap(Map<String, Object> req);


      void updateBy${obj.primaryKey?cap_first}(${_cnamedo} ${_clnamedo});


      void deleteBy${obj.primaryKey?cap_first}(${obj.pkJavaType} ${obj.primaryKey});
      <#if _haveBizkeys = '1'>


      ${_cnamedo}  getByBizKeys (Map<String, Object> req);


      void updateByBizKeys(${_cnamedo} ${_clnamedo});

      </#if>

}
