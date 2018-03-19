package com.zkty.hbh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;

public class DocTableField implements IDocField {
	
	/**
	 * 表头
	 */
	List<DocTableFieldHeader> tableHeaders = null;
	
	/**
	 * 文档表格内容
	 */
	List<Map<String, DocTableFieldCell>> tableContents = null;
	
	/**
	 * 构造函数
	 * @param content 表格内容
	 */
	public DocTableField(List<DocTableFieldHeader> headers, List<Map<String, DocTableFieldCell>> content) {
		if (content.size() > 0) {
			if (headers.size() == content.get(0).keySet().size()) {
				tableHeaders = headers;
				tableContents = content;
			}
		}
	}

	public void updateDocument(Object sender, int start) {
		// 获取游标
		XWPFDocument doc = (XWPFDocument) sender;
		XWPFParagraph para = doc.getParagraphArray(start);
		XmlCursor cursor = para.getCTP().newCursor();
		// 插入表格
		XWPFTable table = doc.insertNewTbl(cursor);
		XWPFTableRow headerRow = table.getRow(0);
		// 创建列和表头
		for (int i = 1; i < tableHeaders.size(); i++) {
			headerRow.createCell();
		}
		for (int i = 0; i < tableHeaders.size(); i++) {
			headerRow.getCell(i).setText(tableHeaders.get(i).key);
			tableHeaders.get(i).setStyle(headerRow.getCell(i));
		}
		// 填充内容
		for (Map<String,DocTableFieldCell> content : tableContents) {
			XWPFTableRow row = table.createRow();
			for (int i = 0; i < tableHeaders.size(); i++) {
				XWPFTableCell cell = row.getCell(i);
				DocTableFieldCell fieldCell = content.get(tableHeaders.get(i).key);
				cell.setText(fieldCell.value);
				fieldCell.setStyle(cell);
			}
		}
	}
	
	public DocFieldType getType() {
		// TODO Auto-generated method stub
		return DocFieldType.TABLE;
	}
	
}
