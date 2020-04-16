<#include "common.ftl">
/*
 * 文件名称: ${_cname}ServiceImpl.java
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_serviceimplpkg};

import ${_daofullname};
import ${_cfullname};
import ${_servicefullname};
<#if _haveBizkeys = '1'>import org.springframework.transaction.annotation.Transactional;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: ${_description}服务实现类<p>  
 * 新增原因: TODO<p>  
 * 新增日期: ${_time} <p>  
 * 
 * @author ${_uname}
 * @version 1.0.0
 */
@Service
public class ${_serviceimplname} implements  ${_servicename}{

	// -------------------------------- 以下自动生成 -------------------------------- //

	@Autowired
	private ${_daoname} ${_daouname};

<#if _haveBizkeys = '1'>

	/**
	 * 根据业务主键查询数据，返回单条记录
	<#list obj.bizKeys as prop>
	 * @param ${prop.name} ${prop.name}
	</#list>
	 * @return ${_description}
	 */
	public ${_cnamedo} getByBizKeys(<#list obj.bizKeys as prop><#if (prop_index>=1)>, </#if>${prop.type} ${prop.name}</#list>) {

	    Map<String, Object> req = new HashMap<>();

	   <#list obj.bizKeys as prop>
		   req.put("${prop.name}", ${prop.name});

	   </#list>
		return ${_daouname}.getByBizKeys(req);

	}

	/**
	 * 根据业务主键修改数据
	 */
	@Transactional(rollbackFor = java.lang.Exception.class)
	public void updateByBizKeys(${_cnamedo} ${_clnamedo}) {

	   ${_daouname}.updateByBizKeys(${_clnamedo});

	}

	/**
	 * 根据业务主键删除数据
	<#list obj.bizKeys as prop>
	 * @param ${prop.name} ${prop.name}
	</#list>
	 */
	@Transactional(rollbackFor = java.lang.Exception.class)
	public void deleteByBizKeys(<#list obj.bizKeys as prop><#if (prop_index>=1)>, </#if>${prop.type} ${prop.name}</#list>) {

		${_cnamedo} bean = getByBizKeys(<#list obj.bizKeys as prop><#if (prop_index>=1)>, </#if>${prop.name}</#list>);
		if (bean != null)
	       ${_daouname}.deleteBy${obj.primaryKey?cap_first}(bean.get${obj.primaryKey?cap_first}());
	}
</#if>

}
