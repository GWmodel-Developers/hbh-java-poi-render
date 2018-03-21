package com.isip.samtbd.doc;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * 文档单子段
 * @author huyigong
 *
 */
public class DocElementField implements IDocField {
	/**
	 * 字段值
	 */
	private String value;
	
	/**
	 * 下划线样式
	 */
	private UnderlinePatterns underlinePatterns = UnderlinePatterns.SINGLE;
	
	/**
	 * 构造函数
	 * @param fieldValue 字段值
	 */
	public DocElementField(String fieldValue) {
		setValue(fieldValue);
	}
	
	/**
	 * 构造函数
	 * @param fieldValue 字段值
	 * @param patterns 下划线样式
	 */
	public DocElementField(String fieldValue, UnderlinePatterns patterns) {
		setValue(fieldValue);
		setUnderlinePatterns(patterns);
	}
	
	@Override
	public String toString() {
		return getValue();
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
		run.setUnderline(this.getUnderlinePatterns());
	}

	public DocFieldType getType() {
		// TODO Auto-generated method stub
		return DocFieldType.ELEMENT;
	}

	/**
	 * 获取字段值
	 * @return the value
	 */
	String getValue() {
		return value;
	}

	/**
	 * 设置字段值
	 * @param value the value to set
	 */
	void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取下划线样式
	 * @return the underlinePatterns
	 */
	UnderlinePatterns getUnderlinePatterns() {
		return underlinePatterns;
	}

	/**
	 * 设置下划线样式
	 * @param underlinePatterns the underlinePatterns to set
	 */
	void setUnderlinePatterns(UnderlinePatterns underlinePatterns) {
		this.underlinePatterns = underlinePatterns;
	}
}
