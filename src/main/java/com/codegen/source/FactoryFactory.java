/*
 * 文件名称: FactoryFactory.java
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

import com.codegen.source.sqlfile.ObjSqlFactory;


/**
 * 
 * @author admin
 */
public class FactoryFactory {
    
    public static ObjFactory createFacotry(Integer facType){
        switch(facType) {
            case 1:
                return new ObjSqlFactory();
            case 2:
//                return new ObjMetaFactory();
            case 3:
                return null;
            case 4:
                return null;
            default:
                    return null;
        }
        
    } 
    
    public interface AryType {
        Integer STRING = 1;
        Integer SQLFILE = 2;
        Integer METADATA = 3;
        Integer DATABASE = 4; 
    }
    
    
}
