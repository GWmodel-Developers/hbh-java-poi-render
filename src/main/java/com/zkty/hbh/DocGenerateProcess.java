package com.zkty.hbh;

import java.util.Map;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.isip.samtbd.doc.DocElementField;
import com.isip.samtbd.doc.DocGenerator;

public class DocGenerateProcess {
	
	public static XWPFDocument formCaseInfo(Map<String, Object> docInfo, String docPath) {
		DocGenerator generator = new DocGenerator();
		generator.setDocPath(docPath);
		generator.getFieldMap().put("key1", new DocElementField(docInfo.get("key1").toString(), UnderlinePatterns.SINGLE));
		generator.getFieldMap().put("key1", new DocElementField(docInfo.get("key1").toString()));
		generator.getFieldMap().put("key1", new DocElementField(docInfo.get("key1").toString()));
		generator.getFieldMap().put("key1", new DocElementField(docInfo.get("key1").toString()));
		generator.replaceInDoc();
		return generator.getDocuemnt();
	}
}
