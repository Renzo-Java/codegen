<#include "common.ftl">
/*
 * 文件名称: ${_cname}.java
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_cpkg};

import java.io.Serializable;
<#list obj.entityImports as import>import ${import};
</#list>
/**
 * 功能描述: ${_description}对象类<p>  
 * 新增原因: TODO<p>  
 * 新增日期: ${_time} <p>  
 * 
 * @author ${_uname}
 * @version 1.0.0
 */
public class ${_cnamedo} implements Serializable {

	private static final long serialVersionUID = 1L;
<#list obj.props as prop>

	/**${prop.caption}*/
	private ${prop.type} ${prop.name};

</#list>

	<#list obj.props as prop>
	public ${prop.type} get${prop.name?cap_first}() {
		return ${prop.name};
	}

	public void set${prop.name?cap_first}(${prop.type} ${prop.name}) {
		this.${prop.name} = ${prop.name};
	}

	</#list>
}