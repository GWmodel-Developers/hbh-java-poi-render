package com.isip.samtbd.doc;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

/**
 * 文档表格字段表头
 * @author huyigong
 *
 */
public class DocTableFieldHeader {
	/**
	 * 表头键
	 */
	private String key;
	
	/**
	 * 表头值
	 */
	private String value;
	
	/**
	 * 宽度
	 */
	private int width = 0;
	
	/**
	 * 构造函数
	 * @param strKey 键
	 * @param strValue 值
	 */
	public DocTableFieldHeader(String strKey, String strValue) {
		setKey(strKey);
		setValue(strValue);
	}
	
	/**
	 * 设置表头样式
	 * @param cell 单元格元素
	 */
	public void setStyle(XWPFTableCell cell) {
		CTTcPr cPr = cell.getCTTc().addNewTcPr();
		CTTblWidth tblWidth = cPr.addNewTcW();
		tblWidth.setW(BigInteger.valueOf(getWidth()));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the key
	 */
	String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	void setValue(String value) {
		this.value = value;
	}
}
