package com.zkty.hbh;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

public class DocTableFieldHeader {
	/**
	 * 表头键
	 */
	String key;
	
	/**
	 * 表头值
	 */
	String value;
	
	/**
	 * 宽度
	 */
	int width = 0;
	
	/**
	 * 构造函数
	 * @param strKey 键
	 * @param strValue 值
	 */
	public DocTableFieldHeader(String strKey, String strValue) {
		key = strKey;
		value = strValue;
	}
	
	/**
	 * 设置表头样式
	 * @param cell 单元格元素
	 */
	public void setStyle(XWPFTableCell cell) {
		CTTcPr cPr = cell.getCTTc().addNewTcPr();
		CTTblWidth tblWidth = cPr.addNewTcW();
		tblWidth.setW(BigInteger.valueOf(width));
	}
}
