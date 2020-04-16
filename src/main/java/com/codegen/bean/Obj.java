/*
 * 文件名称: Obj.java
 * 版权信息: Copyright 2001-2012 admin
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: admin
 * 修改日期:
 * 修改内容: 
 */
package com.codegen.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author admin
 */
public class Obj {
	/**作者*/
	private String author;
    private String pkg;
    private String className;
    private String classNameUpper;
    private String tableName;
    private String primaryKey;//主键属性
    private String jdbcPK;//数据库中主键名
    private String pkJavaType;//主键的java类型
    private List<Prop> bizKeys;//业务联合主键
    private List<Prop> props;
    private List<String> entityImpls = new ArrayList<String>(); //pojo类需要实现的接口名列表
    private String entityExtend = null; //pojo类需要继承的类名列表
    private List<String> entityImports = new ArrayList<String>();//pojo类需要import的包名列表；
    private List<String> serviceImpls = new ArrayList<String>();//service类需要实现的接口名列表
    private List<String> serviceImports = new ArrayList<String>();//service类需要import的包名列表；
    private List<String> daoImports = new ArrayList<String>();//dao类需要import的包名列表；

    private ArgInfo argInfo;
	
	private static String[] keyProps = new String[] {"id", "flag", "status", "ver", "roomId", 
			"transId", "transFlag", "unitId", "createdBy", "createdDate", "updatedBy", "updatedDate"};

    
    //----------------------------//
    
    private Date date = new Date();
    private String propTypeStr;
    private String propNameStr;

    public void initProps() {
        String[] strArr = propNameStr.split(",");
        String[] strTypeArr = propTypeStr.split(",");
        if(strArr.length != strTypeArr.length) {
            throw new RuntimeException("Number of Prop type and name not equal.");
        }
        props = new ArrayList<Prop>();
        for (int i=0;i<strArr.length;i++) {
            props.add(new Prop(strTypeArr[i],strArr[i]));
        }
    }
    
    public void addEntityImpl(String entityImpl) {
    	entityImpls.add(entityImpl);
    }

    public void fillEntityExtend(String extendsName, String pkType) {
    	this.entityExtend = extendsName + "<" + pkType + ">";
    }
    
    public void addServiceImpl(String serviceImpl) {
    	serviceImpls.add(serviceImpl);
    }
    
    public void addEntityImport(String entityImport) {
    	for (String item : entityImports) {
    		if (item.equals(entityImport))
    			return; //已经存在了
    	}
    	entityImports.add(entityImport);
    }

    /**
     * 添加一个dao导入类名
     * @param daoImport
     */
    public void addDaoImport(String daoImport) {
    	for (String item : daoImports) {
    		if (item.equals(daoImport))
    			return; //已经存在了
    	}
    	daoImports.add(daoImport);
    }

    public List<String> getDaoImports() {
		return daoImports;
	}

	public void addServiceImport(String serviceImport) {
    	for (String item : serviceImports) {
    		if (item.equals(serviceImport))
    			return; //已经存在了
    	}
    	serviceImports.add(serviceImport);
    }

    public List<String> getServiceImports() {
		return serviceImports;
	}

	public void setServiceImports(List<String> serviceImports) {
		this.serviceImports = serviceImports;
	}

	public List<String> getEntityImports() {
		return entityImports;
	}

	public void setEntityImports(List<String> entityImports) {
		this.entityImports = entityImports;
	}

	public List<String> getServiceImpls() {
		return serviceImpls;
	}

	public void setServiceImpls(List<String> serviceImpls) {
		this.serviceImpls = serviceImpls;
	}

	public String getEntityExtend() {
		return entityExtend;
	}

	public void setEntityExtend(String entityExtend) {
		this.entityExtend = entityExtend;
	}

	public List<String> getEntityImpls() {
		return entityImpls;
	}

	public void setEntityImpls(List<String> entityImpls) {
		this.entityImpls = entityImpls;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getClassName() {
        return className;
    }
    
    public String getClassNameUpper() {
        return classNameUpper;
    }
    
    public void setClassName(String className) {
        this.className = className;
        this.classNameUpper = className.toUpperCase();
    }    

    public List<Prop> getProps() {
        return props;
    }
    
    public void setProps(List<Prop> props) {
        this.props = props;
    }

    public void setPropNameStr(String propNameStr) {
        this.propNameStr = propNameStr;
    }

    public String getPropNameStr() {
        return propNameStr;
    }

    public void setPropTypeStr(String propTypeStr) {
        this.propTypeStr = propTypeStr;
    }

    public String getPropTypeStr() {
        return propTypeStr;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;//.toUpperCase();
    }

    public String getTableName() {
        return tableName;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setPkg(String pak) {
        this.pkg = pak;
    }

    public String getPkg() {
        return pkg;
    }

    public void setJdbcPK(String jdbcPK) {
        this.jdbcPK = jdbcPK;
    }

    public String getJdbcPK() {
        return jdbcPK;
    }

    public ArgInfo getArgInfo() {
        return argInfo;
    }
    
    public void setArgInfo(ArgInfo argInfo) {
        this.argInfo = argInfo;
    }
    
    public String getPkJavaType() {
        return pkJavaType;
    }
    
    public void setPkJavaType(String pkJavaType) {
        this.pkJavaType = pkJavaType;
    }

    public List<Prop> getBizKeys()
    {
        return bizKeys;
    }

    public void setBizKeys(List<Prop> bizKeys)
    {
        this.bizKeys = bizKeys;
    }
    
    /**
     * 是否客户化属性
     * @param propName
     * @return
     */
    public boolean isCustomProp(String propName) {
    	return itsCustomProp(propName);
    }
    
    public static boolean itsCustomProp(String propName) {
    	for (String keyProp : keyProps) {
    		if (keyProp.equals(propName))
    			return false;
    	}
    	return true;
    }

    /**
     * 判断是否有附件文件索引字段
     * @return 0：没有；1：有
     * @author admin created on 2013-9-4
     */
    public int getHaveFileField() {
        return (argInfo.getAttachFileFieldName() == null || argInfo.getAttachFileFieldName().length() == 0) ? 0 : 1;
    }
    
    /**
     * 是否有混合文件索引字段
     * @return
     * @author admin created on 2013-9-4
     */
    public int getHaveMixField() {
        return (argInfo.getMixFileFieldName() == null || argInfo.getMixFileFieldName().length() == 0) ? 0 : 1;        
    }

    /**
     * 是否有 业务联合主键
     * @return
     * @Author:zhanggd created on 2014-6-18
     */
    public int getHaveBizkeys(){
        return (argInfo.getBizKeys() == null || argInfo.getBizKeys().length() == 0) ? 0 : 1;
    }


}
