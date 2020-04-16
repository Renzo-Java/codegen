/*
 * 文件名称: CodeGen.java
 * 版权信息: Copyright 2001-2012 admin
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: admin
 * 修改日期:
 * 修改内容: 
 */
package com.codegen;

import java.util.List;
import java.util.Scanner;

import org.dom4j.DocumentException;

import com.codegen.bean.ArgInfo;
import com.codegen.util.ObjHelper;
import com.codegen.util.TmpParseUtil;


/**
 * 代码生成器
 * @author admin
 *
 */
public class CodeGen {

    /**
     * @param args
     * @author admin
     */
    public static void main(String[] args) {
        String filePath = "/com/codegen/gentemplate.xml";
        Scanner scan = new Scanner(System.in);
		System.err.print("请输入1确认开始生成, 新生成代码将覆盖原代码：");
		int op = scan.nextInt();
		if (op == 1) {
			startGenCode(filePath);
			System.out.println("代码生成运行结束!");
		} else {
			System.out.println("取消代码生成!");
		}
        scan.close();
    }

	private static void startGenCode(String filePath) {
		List<ArgInfo> argInfos;
        try {
            argInfos = TmpParseUtil.parseXml(filePath);
            ObjHelper helper = new ObjHelper();
            for (ArgInfo argInfo : argInfos) {
            	try {
            		helper.write(argInfo);
				} catch (Exception e) {
					e.printStackTrace();
				} 
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

}
