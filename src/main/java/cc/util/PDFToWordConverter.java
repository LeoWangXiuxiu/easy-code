package cc.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFToWordConverter {
    public static void main(String[] args) {
        String pdfFilePath = "/Users/wangxiuxiu/Downloads/国家危险废物名录（2021年版）.pdf";
        String wordFilePath = "/Users/wangxiuxiu/Downloads/111111.docx";

        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            XWPFDocument wordDocument = new XWPFDocument();
            XWPFTable table = wordDocument.createTable();

            // 可能需要根据实际情况设置表格样式、列数等
            int rowCount = 0; // 记录当前行数
            String[] lines = text.split("\\r?\\n");
            for (String line : lines) {
                String[] cells = line.split("\\s+");

                XWPFTableRow row = table.createRow();
                for (int i = 0; i < cells.length; i++) {
                    XWPFTableCell cell = row.getCell(i);
                    if (cell == null)
                        cell = row.createCell();
                    cell.setText(cells[i]);
                }

                rowCount++;
            }

            // 调整表格样式和布局，可根据需要进行更多设置

            FileOutputStream output = new FileOutputStream(wordFilePath);
            wordDocument.write(output);
            wordDocument.close();
            output.close();

            System.out.println("PDF转Word成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
