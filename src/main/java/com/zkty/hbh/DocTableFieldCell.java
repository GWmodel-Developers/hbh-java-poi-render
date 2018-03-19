package com.zkty.hbh;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class DocTableFieldCell {
	/**
	 * 单元格值
	 */
	String value = "";
	
	/**
	 * 构造函数
	 * @param cellValue 单元格值
	 */
	public DocTableFieldCell(String cellValue) {
		value = cellValue;
	}
	
	/**
	 * 设置单元格样式
	 * @param cell 单元格对象
	 */
	public void setStyle(XWPFTableCell cell) {
		return;
	}
}
