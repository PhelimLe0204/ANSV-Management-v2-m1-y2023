package vn.ansv.management.util;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.ansv.management.dto.Export.ExportVienThongDTO;

public class ExcelGenerator1 {

    private List<ExportVienThongDTO> reportList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator1(List<ExportVienThongDTO> reportList) {
        this.reportList = reportList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Dự án");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 11000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 8000);
        sheet.setColumnWidth(5, 8000);
        sheet.setColumnWidth(6, 8000);
        sheet.setColumnWidth(7, 8000);
        sheet.setColumnWidth(8, 8000);
        sheet.setColumnWidth(9, 8000);
        sheet.setColumnWidth(10, 8000);
        sheet.setColumnWidth(11, 8000);
        sheet.setColumnWidth(12, 8000);
        sheet.setColumnWidth(13, 8000);
        sheet.setColumnWidth(14, 8000);
        sheet.setColumnWidth(15, 8000);
        sheet.setColumnWidth(16, 8000);
        sheet.setColumnWidth(17, 8000);
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        createCell(row, 0, "STT", style);
        createCell(row, 1, "Dự án", style);
        createCell(row, 2, "Khách hàng", style);
        createCell(row, 3, "Mô tả dự án", style);
        createCell(row, 4, "Hình thức đầu tư", style);
        createCell(row, 5, "Tổng mức đầu tư", style);
        createCell(row, 6, "Mức độ khả thi (%)", style);
        createCell(row, 7, "Phân tích SWOOT", style);
        createCell(row, 8, "Khó khăn", style);
        createCell(row, 9, "Giải pháp", style);
        createCell(row, 10, "Priority", style);
        createCell(row, 11, "Status", style);
        createCell(row, 12, "PIC", style);
        createCell(row, 13, "Phó ban", style);
        createCell(row, 14, "Kết quả thực hiện\ntuần trước", style);
        createCell(row, 15, "Kết quả thực hiện\ntuần này", style);
        createCell(row, 16, "Kế hoạch tuần này", style);
        createCell(row, 17, "Kế hoạch tuần sau", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        // sheet.autoSizeColumn(columnCount);
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;

        CellStyle style1 = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setFontHeight(14);
        style1.setFont(font1);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle style2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontHeight(14);
        style2.setFont(font2);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);

        int stt = 0;
        for (ExportVienThongDTO record : reportList) {
            stt++;
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, stt, style1);
            createCell(row, columnCount++, record.getJobName(), style2);
            createCell(row, columnCount++, record.getCustomerName(), style1);
            createCell(row, columnCount++, record.getDescription() == null ? "" : record.getDescription(), style2);
            createCell(row, columnCount++, record.getHinhThucDauTu() == null ? "" : record.getHinhThucDauTu(), style1);
            createCell(row, columnCount++,
                    record.getTongMucDauTuDuKien() == null ? "" : record.getTongMucDauTuDuKien(), style1);
            createCell(row, columnCount++, record.getMucDoKhaThi() == null ? "" : record.getMucDoKhaThi(), style1);
            createCell(row, columnCount++, record.getPhanTichSwoot() == null ? "" : record.getPhanTichSwoot(), style2);
            createCell(row, columnCount++, record.getGeneralIssue() == null ? "" : record.getGeneralIssue(), style2);
            createCell(row, columnCount++, record.getSolution() == null ? "" : record.getSolution(), style2);
            createCell(row, columnCount++, record.getPriorityName(), style1);
            createCell(row, columnCount++, record.getStatusName(), style1);
            createCell(row, columnCount++, record.getAmName() == null ? "" : record.getAmName(), style1);
            createCell(row, columnCount++, record.getAmManagerName() == null ? "" : record.getAmManagerName(), style1);
            createCell(row, columnCount++,
                    record.getKetQuaTuanTruoc() == null ? "" : record.getKetQuaTuanTruoc(), style2);
            createCell(row, columnCount++,
                    record.getKetQuaTuanNay() == null ? "" : record.getKetQuaTuanNay(), style2);
            createCell(row, columnCount++,
                    record.getKeHoachTuanNay() == null ? "" : record.getKeHoachTuanNay(), style2);
            createCell(row, columnCount++,
                    record.getKeHoachTuanSau() == null ? "" : record.getKeHoachTuanSau(), style2);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}