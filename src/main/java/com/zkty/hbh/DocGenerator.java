package com.zkty.hbh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class DocGenerator {
	/**
	 * 源文档路径
	 */
	String docPath = "";
	
	/**
	 * 生成文档保存路径
	 */
	String docSavePath = "";
	
	/**
	 * 字段值映射
	 */
	HashMap<String, IDocField> fieldMap = new HashMap<String, IDocField>(); // Doc field map.
	
	/**
	 * Word 文档对的
	 */
	XWPFDocument docuemnt;
	
	/**
	 * 加载文档
	 * @throws IOException 文件无法找到时抛出异常
	 */
	public void loadDocument() throws IOException {
		FileInputStream ifstream = new FileInputStream(docPath);
		docuemnt = new XWPFDocument(ifstream);
		ifstream.close();
	}
	
	/**
	 * 显示文档中的段落
	 */
	public void showDocument() {
		java.util.List<XWPFParagraph> paragraphs = docuemnt.getParagraphs();
		for (XWPFParagraph xwpfParagraph : paragraphs) {
			System.out.print("Paragraph Position " + docuemnt.getPosOfParagraph(xwpfParagraph) + ": ");
			System.out.println(xwpfParagraph.getText());
		}
	}
	
	/**
	 * 保存渲染的文档
	 * @throws IOException 文件路径无法找到时抛出异常
	 */
	public void saveDocument() throws IOException {
		FileOutputStream ofStream = new FileOutputStream(docSavePath);
		docuemnt.write(ofStream);
		ofStream.close();
	}
	
	/** 
     * 替换段落里面的变量 
     */  
    public void replaceInDoc() {  
        Iterator<XWPFParagraph> iterator = docuemnt.getParagraphsIterator();  
        XWPFParagraph para;  
        while (iterator.hasNext()) {  
            para = iterator.next();  
            this.replaceInPara(para, fieldMap);  
        }  
    }  
  
    /** 
     * 替换段落里面的变量 
     * @param para   要替换的段落 
     * @param params 参数 
     */  
    public void replaceInPara(XWPFParagraph para, Map<String, IDocField> params) {  
        List<XWPFRun> runs;  
        Matcher matcher;  
        while (this.matcher(para.getParagraphText()).find()) {  
            runs = para.getRuns();  
//            System.out.println(runs);
            int start = -1;  
            int end = -1;  
            String str = "";  
            for (int i = 0; i < runs.size(); i++) {  
                XWPFRun run = runs.get(i);  
                String runText = run.toString().trim();
                if (runText.length() == 0) continue;
                if (runText.length() > 1 && '$' == runText.charAt(0) && '{' == runText.charAt(1)) {  
                    start = i;  
                }  
                if ((start != -1)) {  
                    str += runText;  
                }  
                if ('}' == runText.charAt(runText.length() - 1)) {  
                    if (start != -1) {  
                        end = i;  
                        break;  
                    }  
                }  
            }  
            System.out.print("str--->>>" + str + ", ");
            System.out.print("start: " + start + ", ");
            System.out.println("end: " + end + ".");
  
            for (int i = start; i <= end; i++) {  
                para.removeRun(i);  
                i--;  
                end--;
            }  
            for (String key : params.keySet()) {  
                if (str.equals(key)) {
                		IDocField field = params.get(key);
                		switch (field.getType()) {
						case ELEMENT:
							field.updateDocument(para, start);
							break;
						case TABLE:
							field.updateDocument(docuemnt, docuemnt.getPosOfParagraph(para));
							break;
						default:
							throw new IllegalArgumentException();
					}
//                    XWPFRun newRun = para.insertNewRun(start);
//                    DocElementField field = params.get(key);
//                    newRun.setText(field.toString());
//                    newRun.setUnderline(field.underlinePatterns);
                    break;  
                }  
            }
        }  
    }  
  
    /** 
     * 替换表格里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数 
     */  
    public void replaceInTable(XWPFDocument doc, Map<String, IDocField> params) {  
        Iterator<XWPFTable> iterator = doc.getTablesIterator();  
        XWPFTable table;  
        List<XWPFTableRow> rows;  
        List<XWPFTableCell> cells;  
        List<XWPFParagraph> paras;  
        while (iterator.hasNext()) {  
            table = iterator.next();  
            rows = table.getRows();  
            for (XWPFTableRow row : rows) {  
                cells = row.getTableCells();  
                for (XWPFTableCell cell : cells) {  
                    paras = cell.getParagraphs();  
                    for (XWPFParagraph para : paras) {  
                        this.replaceInPara(para, params);  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * 正则匹配字符串 
     * 
     * @param str 正则表达式
     * @return 匹配器
     */  
    private Matcher matcher(String str) {  
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(str);  
        return matcher;  
    }  
}
