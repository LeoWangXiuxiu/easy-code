package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFToExcelConverter {
    public static void main(String[] args) {
        String pdfFilePath = "/Users/wangxiuxiu/Downloads/国家危险废物名录（2021年版）.pdf";
        String excelFilePath = "/Users/wangxiuxiu/Downloads/123.xlsx";

        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            String[] lines = text.split("\\r?\\n");

            for (int i = 0; i < lines.length; i++) {
                Row row = sheet.createRow(i);
                String[] cells = lines[i].split("\\s+");

                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(cells[j]);
                }
            }

            FileOutputStream output = new FileOutputStream(excelFilePath);
            workbook.write(output);
            workbook.close();
            output.close();

            System.out.println("PDF转Excel成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
