package com.bytebeats.codelab.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class CodeGenerator {
	
	public static void generate(Map<String, Object> root, File templateDir, String templateFilename, File outputFile) throws IOException, TemplateException{
		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(templateDir);	
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		Template temp = cfg.getTemplate(templateFilename);	// load E:/Work/Freemarker/templates/person.ftl
		
//		Writer out = new OutputStreamWriter(System.out);
		
		OutputStream fos = new  FileOutputStream(outputFile); //生成java文件的路径
		Writer out = new OutputStreamWriter(fos);
		temp.process(root, out);
		
		fos.flush();  
        fos.close();
        
        System.out.println("gen code success!");
	}
}
