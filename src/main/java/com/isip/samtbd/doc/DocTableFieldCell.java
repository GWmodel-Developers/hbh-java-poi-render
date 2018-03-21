package com.isip.samtbd.doc;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

/**
 * 文档表格字段单元格
 * @author huyigong
 *
 */
public class DocTableFieldCell {
	/**
	 * 单元格值
	 */
	private String value = "";
	
	/**
	 * 构造函数
	 * @param cellValue 单元格值
	 */
	public DocTableFieldCell(String cellValue) {
		setValue(cellValue);
	}
	
	/**
	 * 设置单元格样式
	 * @param cell 单元格对象
	 */
	public void setStyle(XWPFTableCell cell) {
		return;
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
