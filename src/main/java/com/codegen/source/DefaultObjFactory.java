/*
 * 文件名称: DefaultObjFactory.java
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

import java.util.ArrayList;
import java.util.List;

import com.codegen.bean.Obj;

import com.codegen.bean.ArgInfo;
import com.codegen.bean.Column;
import com.codegen.bean.Prop;
import com.codegen.util.CodeGenUtil;


/**
 * 
 * @author admin
 */
public class DefaultObjFactory {
	
    /**
     * @param argInfo
     * @param columns
     * @return
     * @author admin
     * @since DE6.0
     */
    public Obj genObj(ArgInfo argInfo, List<Column> columns) {
        String name = "";
        String jdbcName = "";
        String jdbcType = "";
        String cName = argInfo.getClassName();
        String strTableName = argInfo.getTableName();
        String pkg = argInfo.getPkg();
        Obj obj = new Obj();
        obj.setAuthor(argInfo.getAuthor());
        obj.setClassName(cName);
        //obj.setTableName(strTableName);
        obj.setPkg(pkg);
        obj.setTableName(strTableName);
        obj.setArgInfo(argInfo);

        boolean supportCode = false;
		
		List<Prop> props = new ArrayList<Prop>();
		int transFlag = 0;//2个字段都要满足才行
		for (Column column : columns) {
			jdbcName = column.getColumnName();
			name = CodeGenUtil.convertPropName(jdbcName);
			jdbcType = column.getJdbcType();
			jdbcType = convertJdbcType(jdbcType);
			Prop prop = new Prop(CodeGenUtil.convertJdbcToJava(jdbcType), // 得到java类型
					name, jdbcType, //
					jdbcName);
			prop.setCaption(column.getComment());//字段描述
			props.add(prop);
			
			if (column.getIsPrimary()) {
				String pkName = prop.getName();
				obj.setPrimaryKey(pkName);
				obj.setJdbcPK(jdbcName);

				String type = prop.getType();
				if (type.equalsIgnoreCase(Integer.class.getSimpleName())) {
					if (column.getLength() > 11) {
						type = Long.class.getSimpleName();
						prop.setType(type);
					}
				}
				obj.setPkJavaType(type);
			}
			else {
				String type = prop.getType();
				if (type.equalsIgnoreCase(Integer.class.getSimpleName())) {
					if (column.getLength() > 11) {
						type = Long.class.getSimpleName();
						prop.setType(type);
					}
				}
				else if (type.equalsIgnoreCase(java.util.Date.class.getSimpleName())) {
					if (Obj.itsCustomProp(name))
						obj.addEntityImport("java.util.Date");
				}
			}
		}
		
		String pkType = obj.getPkJavaType();//前面还没有赋值

		//收集接口信息
        if (obj.getHaveFileField() == 1) {
        	obj.addEntityImpl("IFileAvailableBean<" + pkType + ">");
        	obj.addServiceImpl("IFileSupportService<" + pkType + "," + obj.getClassName() + ">"); //IFileSupportService<${obj.pkJavaType},${_cname}>
        }
        
        if (obj.getHaveMixField() == 1)
        	obj.addServiceImpl("IMixFileService");

        obj.setProps(props);
        if(argInfo.getBizKeys() !=null || argInfo.getBizKeys().length()>0)
            setBizkeys(obj, argInfo.getBizKeys().trim());
        
        return obj;
    }
    
    /**
     * 设置业务联合主键字段
     * @param obj
     * @param bizKeys
     * @Author:admin
     */
    private void setBizkeys(Obj obj, String bizKeys)
    {
        List<Prop> bizKeysList = new ArrayList<Prop>();
        List<Prop> props = obj.getProps();
        if (bizKeys.contains(",")){
            String[] array = bizKeys.trim().split(",");
            for (int i = 0; i < array.length; i++){
                iteratorProps(bizKeysList, props, array[i]);
            }
        }else{
            iteratorProps(bizKeysList, props, bizKeys);
        }
        obj.setBizKeys(bizKeysList);
    }

   
    /**
     * 设置业务联合主键辅助方法
     * @param bizKeysList
     * @param props
     * @param bizkeys
     * @Author:admin
     */
    private void iteratorProps(List<Prop> bizKeysList, List<Prop> props, String bizkeys)
    {
        for (Prop prop : props){
            if(bizkeys.equals(prop.getColumnName()))
                bizKeysList.add(prop); 
        }
    }

    //改为通用型,便于比较处理
    private String convertJdbcType(String jdbcType) {
        jdbcType = jdbcType.toUpperCase();
        if (jdbcType.equals("INT") || jdbcType.equals("BIGINT") || jdbcType.equals("TINYINT"))
            jdbcType = "INTEGER";
        else {
            jdbcType = jdbcType.replace("VARCHAR2", "VARCHAR");
            jdbcType = jdbcType.replace("NUMBER", "NUMERIC");//
            jdbcType = jdbcType.replace("DATETIME", "TIMESTAMP");
        }
        return jdbcType;
    }    
}
