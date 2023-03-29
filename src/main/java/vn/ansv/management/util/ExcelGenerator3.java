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

import vn.ansv.management.dto.Export.ExportTrienKhaiDTO;

public class ExcelGenerator3 {

    private List<ExportTrienKhaiDTO> reportList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator3(List<ExportTrienKhaiDTO> reportList) {
        this.reportList = reportList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Dự án");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 11000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 8000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        sheet.setColumnWidth(9, 6000);
        sheet.setColumnWidth(10, 6000);
        sheet.setColumnWidth(11, 6000);
        sheet.setColumnWidth(12, 6000);
        sheet.setColumnWidth(13, 6000);
        sheet.setColumnWidth(14, 6000);
        sheet.setColumnWidth(15, 6000);
        sheet.setColumnWidth(16, 6000);
        sheet.setColumnWidth(17, 6000);
        sheet.setColumnWidth(18, 6000);
        sheet.setColumnWidth(19, 6000);
        sheet.setColumnWidth(20, 6000);
        sheet.setColumnWidth(21, 6000);
        sheet.setColumnWidth(22, 6000);
        sheet.setColumnWidth(23, 6000);
        sheet.setColumnWidth(24, 4000);
        sheet.setColumnWidth(25, 4000);
        sheet.setColumnWidth(26, 6000);
        sheet.setColumnWidth(27, 6000);
        sheet.setColumnWidth(28, 6000);
        sheet.setColumnWidth(29, 8000);
        sheet.setColumnWidth(30, 8000);
        sheet.setColumnWidth(31, 8000);
        sheet.setColumnWidth(32, 8000);
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
        createCell(row, 2, "Số hợp đồng", style);
        createCell(row, 3, "Mã số kế toán", style);
        createCell(row, 4, "Khách hàng", style);
        createCell(row, 5, "Giá trị", style);
        createCell(row, 6, "Số tiền DAC", style);
        createCell(row, 7, "DAC hợp đồng", style);
        createCell(row, 8, "Mục tiêu DAC", style);
        createCell(row, 9, "DAC thực tế", style);
        createCell(row, 10, "Số ngày còn lại\nDAC", style);
        createCell(row, 11, "Số tiền PAC", style);
        createCell(row, 12, "PAC hợp đồng", style);
        createCell(row, 13, "Mục tiêu PAC", style);
        createCell(row, 14, "PAC thực tế", style);
        createCell(row, 15, "Số ngày còn lại\nPAC", style);
        createCell(row, 16, "Số tiền FAC", style);
        createCell(row, 17, "FAC hợp đồng", style);
        createCell(row, 18, "Mục tiêu FAC", style);
        createCell(row, 19, "FAC thực tế", style);
        createCell(row, 20, "Số ngày còn lại\nFAC", style);
        createCell(row, 21, "Tiến độ chung", style);
        createCell(row, 22, "Khó khăn", style);
        createCell(row, 23, "Giải pháp", style);
        createCell(row, 24, "Priority", style);
        createCell(row, 25, "Status", style);
        createCell(row, 26, "AM", style);
        createCell(row, 27, "PM", style);
        createCell(row, 28, "Phó ban", style);
        createCell(row, 29, "Kết quả thực hiện\ntuần trước", style);
        createCell(row, 30, "Kết quả thực hiện\ntuần này", style);
        createCell(row, 31, "Kế hoạch tuần này", style);
        createCell(row, 32, "Kế hoạch tuần sau", style);
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
        for (ExportTrienKhaiDTO record : reportList) {
            stt++;
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, stt, style1);
            createCell(row, columnCount++, record.getJobName(), style2);
            createCell(row, columnCount++, record.getMaHopDong() == null ? "" : record.getMaHopDong(), style2);
            createCell(row, columnCount++, record.getMaKeToan() == null ? "" : record.getMaKeToan(), style1);
            createCell(row, columnCount++, record.getCustomerName(), style1);
            createCell(row, columnCount++,
                    record.getTongGiaTriThucTe() == null ? "" : record.getTongGiaTriThucTe(), style1);
            createCell(row, columnCount++,
                    record.getSoTienDac() == null ? "" : record.getSoTienDac(), style1);
            createCell(row, columnCount++,
                    record.getHopDongDac() == null ? "" : record.getHopDongDac(), style1);
            createCell(row, columnCount++,
                    record.getMucTieuDac() == null ? "" : record.getMucTieuDac(), style1);
            createCell(row, columnCount++,
                    record.getThucTeDac() == null ? "" : record.getThucTeDac(), style1);
            createCell(row, columnCount++,
                    record.getChenhLechDac() == null ? "" : record.getChenhLechDac(), style1);
            createCell(row, columnCount++,
                    record.getSoTienPac() == null ? "" : record.getSoTienPac(), style1);
            createCell(row, columnCount++,
                    record.getHopDongPac() == null ? "" : record.getHopDongPac(), style1);
            createCell(row, columnCount++,
                    record.getMucTieuPac() == null ? "" : record.getMucTieuPac(), style1);
            createCell(row, columnCount++,
                    record.getThucTePac() == null ? "" : record.getThucTePac(), style1);
            createCell(row, columnCount++,
                    record.getChenhLechPac() == null ? "" : record.getChenhLechPac(), style1);
            createCell(row, columnCount++,
                    record.getSoTienFac() == null ? "" : record.getSoTienFac(), style1);
            createCell(row, columnCount++,
                    record.getHopDongFac() == null ? "" : record.getHopDongFac(), style1);
            createCell(row, columnCount++,
                    record.getMucTieuFac() == null ? "" : record.getMucTieuFac(), style1);
            createCell(row, columnCount++,
                    record.getThucTeFac() == null ? "" : record.getThucTeFac(), style1);
            createCell(row, columnCount++,
                    record.getChenhLechFac() == null ? "" : record.getChenhLechFac(), style1);
            createCell(row, columnCount++, record.getTienDoChung() == null ? "" : record.getTienDoChung(), style2);
            createCell(row, columnCount++, record.getGeneralIssue() == null ? "" : record.getGeneralIssue(), style2);
            createCell(row, columnCount++, record.getSolution() == null ? "" : record.getSolution(), style2);
            createCell(row, columnCount++, record.getPriorityName() == null ? "" : record.getPriorityName(), style1);
            createCell(row, columnCount++, record.getStatusName() == null ? "" : record.getStatusName(), style1);
            createCell(row, columnCount++, record.getAmName() == null ? "" : record.getAmName(), style1);
            createCell(row, columnCount++, record.getPmName() == null ? "" : record.getPmName(), style1);
            createCell(row, columnCount++, record.getPmManagerName() == null ? "" : record.getPmManagerName(), style1);
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