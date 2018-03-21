package com.isip.samtbd.doc;

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

/**
 * 文档表格字段
 * @author huyigong
 *
 */
public class DocTableField implements IDocField {
	
	/**
	 * 表头
	 */
	private List<DocTableFieldHeader> tableHeaders = null;
	
	/**
	 * 文档表格内容
	 */
	private List<Map<String, DocTableFieldCell>> tableContents = null;
	
	/**
	 * 构造函数
	 * @param headers 表头
	 * @param content 表格内容
	 */
	public DocTableField(List<DocTableFieldHeader> headers, List<Map<String, DocTableFieldCell>> content) {
		if (content.size() > 0) {
			if (headers.size() == content.get(0).keySet().size()) {
				setTableHeaders(headers);
				setTableContents(content);
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
		for (int i = 1; i < getTableHeaders().size(); i++) {
			headerRow.createCell();
		}
		for (int i = 0; i < getTableHeaders().size(); i++) {
			headerRow.getCell(i).setText(getTableHeaders().get(i).getValue());
			getTableHeaders().get(i).setStyle(headerRow.getCell(i));
		}
		// 填充内容
		for (Map<String,DocTableFieldCell> content : getTableContents()) {
			XWPFTableRow row = table.createRow();
			for (int i = 0; i < getTableHeaders().size(); i++) {
				XWPFTableCell cell = row.getCell(i);
				DocTableFieldCell fieldCell = content.get(getTableHeaders().get(i).getKey());
				cell.setText(fieldCell.getValue());
				fieldCell.setStyle(cell);
			}
		}
	}
	
	public DocFieldType getType() {
		// TODO Auto-generated method stub
		return DocFieldType.TABLE;
	}

	/**
	 * @return the tableHeaders
	 */
	List<DocTableFieldHeader> getTableHeaders() {
		return tableHeaders;
	}

	/**
	 * @param tableHeaders the tableHeaders to set
	 */
	void setTableHeaders(List<DocTableFieldHeader> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

	/**
	 * @return the tableContents
	 */
	List<Map<String, DocTableFieldCell>> getTableContents() {
		return tableContents;
	}

	/**
	 * @param tableContents the tableContents to set
	 */
	void setTableContents(List<Map<String, DocTableFieldCell>> tableContents) {
		this.tableContents = tableContents;
	}
	
}
