<#include "common.ftl">
/*
 * 文件名称: ${_cname}Controller.java
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_controllerpkg};

import ${_servicefullname};
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述: ${_description}请求入口<p>  
 * 新增原因: TODO<p>  
 * 新增日期: ${_time} <p>  
 * 
 * @author ${_uname}
 * @version 1.0.0
 */
@RestController
@RequestMapping("/")
public class ${_controllername}{

	// -------------------------------- 以下自动生成 -------------------------------- //
	@Resource
	private ${_servicename} ${_serviceuname};

}
