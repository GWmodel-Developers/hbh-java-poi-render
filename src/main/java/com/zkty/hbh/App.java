package com.zkty.hbh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    		DocGenerator generator = new DocGenerator();
    		generator.docPath = "/Users/huyigong/Documents/Work/huangbaihe/formCaseSource.docx";
    		generator.docSavePath = "/Users/huyigong/Documents/Work/huangbaihe/formCaseSource-output.docx";
    		generator.fieldMap.put("${EL_reportYear}", new DocElementField("2017"));
    		generator.fieldMap.put("${EL_reportMonth}", new DocElementField("3"));
    		generator.fieldMap.put("${EL_reportDay}", new DocElementField("19"));
    		generator.fieldMap.put("${EL_reportHour}", new DocElementField("19"));
    		generator.fieldMap.put("${EL_recordYear}", new DocElementField("2017", UnderlinePatterns.NONE));
    		generator.fieldMap.put("${EL_recordMonth}", new DocElementField("3", UnderlinePatterns.NONE));
    		generator.fieldMap.put("${EL_recordDay}", new DocElementField("19", UnderlinePatterns.NONE));
    		generator.fieldMap.put("${EL_examYear}", new DocElementField("2017", UnderlinePatterns.NONE));
    		generator.fieldMap.put("${EL_examMonth}", new DocElementField("3", UnderlinePatterns.NONE));
    		generator.fieldMap.put("${EL_examDay}", new DocElementField("19", UnderlinePatterns.NONE));
    		generator.fieldMap.put("${EL_source}", new DocElementField("违法电鱼"));
    		generator.fieldMap.put("${EL_reporter}", new DocElementField("张三三"));
    		generator.fieldMap.put("${EL_sex}", new DocElementField("男"));
    		generator.fieldMap.put("${EL_gender}", new DocElementField("22"));
    		generator.fieldMap.put("${EL_number}", new DocElementField("13000000000"));
    		generator.fieldMap.put("${EL_address}", new DocElementField("武汉大学遥感信息工程学院"));
    		generator.fieldMap.put("${EL_content}", new DocElementField("电鱼"));
    		generator.fieldMap.put("${EL_recorder}", new DocElementField("张三三"));
    		generator.fieldMap.put("${EL_examSuggest}", new DocElementField("同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案同意立案"));
    		generator.fieldMap.put("${EL_responsible}", new DocElementField("李中华"));
    		
    		// 创建表格参数
    		List<DocTableFieldHeader> headerMap = new ArrayList<DocTableFieldHeader>();
    		headerMap.add(new DocTableFieldHeader("test1", "测试1"));
    		headerMap.add(new DocTableFieldHeader("test2", "测试2"));
    		headerMap.add(new DocTableFieldHeader("test3", "测试3"));
    		DocTableFieldHeader header4 = new DocTableFieldHeader("test4", "测试4");
    		header4.width = 2000;
    		headerMap.add(header4);
    		headerMap.add(new DocTableFieldHeader("test5", "测试5"));
    		List<Map<String, DocTableFieldCell>> contentMap = new ArrayList<Map<String, DocTableFieldCell>>();
    		for (int i = 0; i < 10; i++) {
    			Map<String, DocTableFieldCell> content = new HashMap<String, DocTableFieldCell>();
    			for (int j = 0; j < 5; j++) {
    				content.put("test" + (j + 1), new DocTableFieldCell(("测试内容" + (i + 1)) + (j + 1)));
			}
    			contentMap.add(content);
		}
    		generator.fieldMap.put("${TB_demo}", new DocTableField(headerMap, contentMap));
    		try {
				generator.loadDocument();
				generator.showDocument();
				generator.replaceInDoc();
				generator.showDocument();
				generator.saveDocument();
			} catch (IOException e) {
				System.out.println("Open document filed!");
				e.printStackTrace();
			}
    }
}
