/*
 * 文件名称: ObjFactory.java
 * 版权信息: Copyright 2001-2012 admin
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: admin
 * 修改日期:
 * 修改内容: 
 */
package com.codegen.source;

import com.codegen.bean.Obj;
import com.codegen.bean.ArgInfo;


/**
 * 
 * @author admin
 */
public interface ObjFactory {
    public Obj getObj(ArgInfo argInfo);
}
