/*
 * 文件名称: Temlate.java
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

/**
 * 
 * @author admin
 *
 */
public enum TemlateEnum {
    TEMPCODEFILENAME(0,"codetemplate.ftl","dal.dataobject","DO.java",true),
    TEMPDAOFILENAME(1,"codedaotemplate.ftl","dal.dao","DAO.java",true),
    TEMPMAPPERFILENAME(3,"mappertemplate.ftl","dal.mapper",".xml",true),
    TEMPSERVICEFILENAME(4,"codeservicetemplate.ftl","biz.service.impl","ServiceImpl.java",true),//原来是接口，现在变成了新版的servcie实现类
    TEMPCONTROLLFILENAME(10,"codeactiontemplate.ftl","web.controller","Controller.java",true),
	TEMPINTERSERVICEFILENAME(11,"serviceinterfacetemplate.ftl","biz.service","Service.java", true);

    
    private int tmpNo;
    private String tmpName;
    private String pkgSux;
    private String suffix;
    private Boolean isJavaFile;
    
   TemlateEnum(int tmpNo,String tmpName,String pkgSux,String suffix,Boolean isJavaFile) {
    	this.tmpNo = tmpNo;
    	this.tmpName = tmpName;
    	this.suffix = suffix;
    	this.pkgSux = pkgSux;
    	this.isJavaFile = isJavaFile;
    }

	public int getTmpNo() {
		return tmpNo;
	}

	public void setTmpNo(int tmpNo) {
		this.tmpNo = tmpNo;
	}

	public String getTmpName() {
		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setPkgSux(String pkgSux) {
		this.pkgSux = pkgSux;
	}

	public String getPkgSux() {
		return pkgSux;
	}

	public void setIsJavaFile(Boolean isJavaFile) {
		this.isJavaFile = isJavaFile;
	}

	public Boolean getIsJavaFile() {
		return isJavaFile;
	}
    
}
