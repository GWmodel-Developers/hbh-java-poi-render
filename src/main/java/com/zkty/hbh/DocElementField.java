package com.zkty.hbh;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocElementField implements IDocField {
	/**
	 * 字段值
	 */
	String value;
	
	/**
	 * 下划线样式
	 */
	UnderlinePatterns underlinePatterns = UnderlinePatterns.SINGLE;
	
	/**
	 * 构造函数
	 * @param fieldValue 字段值
	 */
	public DocElementField(String fieldValue) {
		value = fieldValue;
	}
	
	/**
	 * 构造函数
	 * @param fieldValue 字段值
	 * @param patterns 下划线样式
	 */
	public DocElementField(String fieldValue, UnderlinePatterns patterns) {
		value = fieldValue;
		underlinePatterns = patterns;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public void updateDocument(Object sender, int start) throws ClassCastException {
		XWPFParagraph para = (XWPFParagraph) sender;
		XWPFRun newRun = para.insertNewRun(start);
        newRun.setText(this.toString());
        setStyle(newRun);
	}
	
	/**
	 * 设置样式
	 * @param run 文本块
	 */
	public void setStyle(XWPFRun run) {
		run.setUnderline(this.underlinePatterns);
	}

	public DocFieldType getType() {
		// TODO Auto-generated method stub
		return DocFieldType.ELEMENT;
	}
}
