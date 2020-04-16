/*
 * 文件名称: ObjWriteHelper.java
 * 版权信息: Copyright 2001-2012 admin
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: admin
 * 修改日期:
 * 修改内容: 
 */
package com.codegen.util;

import java.util.Map;

import com.codegen.bean.Obj;
import com.codegen.source.FactoryFactory;
import com.codegen.source.ObjFactory;

import com.codegen.bean.ArgInfo;


/**
 * 
 * @author admin
 *
 */
public class ObjHelper {

    /**
     * 根据参数，把生成的对象写到文件
     * @param argInfo
     * @throws Exception
     * @author admin
     */
    public void write(ArgInfo argInfo) throws Exception {
        Obj obj = genObj(argInfo);
        Map<String,String> outputMap = argInfo.getOutputInfos();
        for (Map.Entry<String,String> entry : outputMap.entrySet()) {
            String tmplName = entry.getKey();

            writeFile(obj, tmplName, entry.getValue());
        }
    }
    
    /**
     * 生成一个obj
     * @param argInfo
     * @return
     * @author admin
     */
    public Obj genObj(ArgInfo argInfo) {
        ObjFactory factory = FactoryFactory.createFacotry(Integer.parseInt(argInfo.getGenType()));
        Obj obj = factory.getObj(argInfo);
        return obj;
    }
    
    private void writeFile(Object data,String templateName,String filepath) throws Exception{
        FileWriteUtil.writeFile(data,templateName,filepath);
    }

    
}
