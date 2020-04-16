/*
 * 文件名称: FreeMarkerUtils.java
 * 版权信息: Copyright 2001-2011 admin
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: Huangweibing
 * 修改日期: 2011-10-9
 * 修改内容: 
 */
package com.codegen.freemarker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * 
 * @author admin
 *
 */
public class TemplParserUtils {
    private static Configuration cfg;
    Logger logger = LogManager.getLogger(getClass());
    
    @SuppressWarnings("deprecation")
	private static Configuration getConfiguration(){
        //创建一个Configuration实例
        if (cfg == null){
            synchronized(TemplParserUtils.class){
                if (cfg == null){
                    cfg = new Configuration();
                }
            }
        }
         //设置FreeMarker的模版文件位置
        cfg.setClassForTemplateLoading(TemplParserUtils.class, "");
        return cfg;
    }
    
    /**
     * 根据内容与模板名称生成xml格式文本
     * @param data 数据
     * @param templateName 模板名称
     * @return
     * @throws Exception
     * @author Huangweibing created on 2011-10-10 
     * @since DE 5.2
     */
    public String getTemplateContent(Map<String,Object> data,String templateName) throws Exception{
        Configuration cfg = TemplParserUtils.getConfiguration();
        try {
            Template template = cfg.getTemplate(templateName,"utf-8");
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            template.process(data, new PrintWriter(baos));
            return baos.toString();
        }
        catch (IOException e) {
            logger.warn("读取模块文件出错",e);
            throw new Exception("读取模块文件出错",e);
        }
        catch (TemplateException e) {
            logger.warn("模块格式化出错",e);
            throw new Exception("模块格式化出错",e);
        }
    }
}
