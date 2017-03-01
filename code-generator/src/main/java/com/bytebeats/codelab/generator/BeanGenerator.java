package com.bytebeats.codelab.generator;

import com.bytebeats.codelab.generator.bean.Attribute;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class BeanGenerator {
	
	public static void main(String[] args) {

		// Create the root hash
		Map<String, Object> root = new HashMap<String, Object>();

		root.put("packageName", "com.ricky.java");
		root.put("className", "Person");
		root.put("author", "Ricky Fung");

		List<Attribute> attr_list = new ArrayList<Attribute>();
		attr_list.add(new Attribute("id", "Long"));
		attr_list.add(new Attribute("name", "String"));
		attr_list.add(new Attribute("age", "Integer"));
		attr_list.add(new Attribute("hobby", "List<String>"));

		root.put("attrs", attr_list);
		
		File outputDir = new File(BeanGenerator.class.getResource("/").getPath(), "src");
		if(!outputDir.exists()){
			outputDir.mkdirs();
		}
		System.out.println("outputDir:"+outputDir);
		
		File templatesDir = new File(BeanGenerator.class.getResource("/").getPath(),"templates");
		
		System.out.println("templatesDir:"+templatesDir);
		
		try {
			CodeGenerator.generate(root, templatesDir, "person.ftl", new File(outputDir, "Person.java"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
