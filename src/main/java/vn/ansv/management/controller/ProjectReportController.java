package vn.ansv.management.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import vn.ansv.management.dto.Report.ListReport12DTO;
import vn.ansv.management.dto.Report.ListReport3DTO;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.dto.Export.ExportChuyenDoiSoDTO;
import vn.ansv.management.dto.Export.ExportInputDTO;
import vn.ansv.management.dto.Export.ExportTrienKhaiDTO;
import vn.ansv.management.dto.Export.ExportVienThongDTO;
import vn.ansv.management.dto.Report.AddNewReportDTO;
import vn.ansv.management.service.ProjectReportService;
import vn.ansv.management.service.Interface.IStorageService;
import vn.ansv.management.util.ExcelGenerator1;
import vn.ansv.management.util.ExcelGenerator2;
import vn.ansv.management.util.ExcelGenerator3;

@Controller
@RequestMapping(path = "")
public class ProjectReportController extends BaseController {
    @Autowired // Inject "ProjectReportService" - Dependency Injection
    private ProjectReportService projectReportService;

    @Autowired // Inject "IStorageService" - Dependency Injection
    private IStorageService storageService;

    @RequestMapping(value = "/danh-sach/kd-vien-thong", method = RequestMethod.GET)
    public ModelAndView viewReportType1(HttpSession session, HttpServletRequest request) {
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);

        Init(session); // Lấy dữ liệu cơ bản
        String userRole = (String) session.getAttribute("userRole");
        userRole = userRole.substring(0, userRole.indexOf("___"));
        if (userRole.contains("DOC_DO") || userRole.contains("Manager_PM")
                || userRole.contains("Main_PM") || userRole.contains("Member_PM")) {
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        List<ListReport12DTO> dataType1 = null;
        if (userRole.contains("Admin") || userRole.contains("CEO")
                || userRole.contains("DGD") || userRole.contains("Manager_AM")) {
            dataType1 = projectReportService.findAllReportType12(null, 1L);
            _mvShare.addObject("listReportType1", dataType1);
            _mvShare.setViewName("non-admin/report/kd-vien-thong");
            return _mvShare;
        } else {
            dataType1 = projectReportService.findAllReportType12(username, 1L);
            _mvShare.addObject("listReportType1", dataType1);
            _mvShare.setViewName("non-admin/report/kd-vien-thong");
            return _mvShare;
        }
    }

    @RequestMapping(value = "/danh-sach/kd-chuyen-doi-so", method = RequestMethod.GET)
    public ModelAndView viewReportType2(HttpSession session, HttpServletRequest request) {
        // Lấy url
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);
        Init(session); // Lấy dữ liệu cơ bản
        String userRole = (String) session.getAttribute("userRole");
        userRole = userRole.substring(0, userRole.indexOf("___"));
        if (userRole.contains("DOC_DO") || userRole.contains("Manager_PM")
                || userRole.contains("Main_PM") || userRole.contains("Member_PM")) {
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        List<ListReport12DTO> dataType2 = null;
        if (userRole.contains("Admin") || userRole.contains("CEO")
                || userRole.contains("DGD") || userRole.contains("Manager_AM")) {
            dataType2 = projectReportService.findAllReportType12(null, 2L);
            _mvShare.addObject("listReportType2", dataType2);
            _mvShare.setViewName("non-admin/report/kd-chuyen-doi-so");
            return _mvShare;
        } else {
            dataType2 = projectReportService.findAllReportType12(username, 2L);
            _mvShare.addObject("listReportType2", dataType2);
            _mvShare.setViewName("non-admin/report/kd-chuyen-doi-so");
            return _mvShare;
        }
    }

    @RequestMapping(value = "/danh-sach/trien-khai", method = RequestMethod.GET)
    public ModelAndView viewReportType3(HttpSession session, HttpServletRequest request) {
        // Lấy last url
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);

        Init(session); // Lấy dữ liệu cơ bản
        String userRole = (String) session.getAttribute("userRole");
        userRole = userRole.substring(0, userRole.indexOf("___"));
        if (userRole.contains("DOC_BDC") || userRole.contains("Manager_AM")
                || userRole.contains("Main_AM") || userRole.contains("Member_AM")) {
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        List<ListReport3DTO> dataType3 = null;
        if (userRole.contains("Admin") || userRole.contains("CEO")
                || userRole.contains("DGD") || userRole.contains("Manager_PM")) {
            dataType3 = projectReportService.findAllReportType3(null, 3L);
            _mvShare.addObject("listReportType3", dataType3);
            _mvShare.setViewName("non-admin/report/trien-khai");
            return _mvShare;
        } else {
            dataType3 = projectReportService.findAllReportType3(username, 3L);
            _mvShare.addObject("listReportType3", dataType3);
            _mvShare.setViewName("non-admin/report/trien-khai");
            return _mvShare;
        }
    }

    @RequestMapping(value = "report/addNew/{type}", method = RequestMethod.POST)
    public String addNew(@ModelAttribute AddNewReportDTO dataUpdate, @PathVariable Integer type) {
        if (type == 3) {
            return "redirect:/danh-sach/trien-khai?addNew=" + projectReportService.addNewReport(dataUpdate);
        }
        if (type == 2) {
            return "redirect:/danh-sach/kd-chuyen-doi-so?addNew=" + projectReportService.addNewReport(dataUpdate);
        }
        if (type == 1) {
            return "redirect:/danh-sach/kd-vien-thong?addNew=" + projectReportService.addNewReport(dataUpdate);
        }

        return null;

    }

    // @RequestMapping(value = "/import", method = RequestMethod.POST)
    // public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile
    // reapExcelDataFile) {
    // List<Map<String, String>> dataError =
    // projectReportService.checkFileExcelImportReport(reapExcelDataFile);
    // if (dataError.isEmpty()) {
    // return "redirect:/danh-sach/trien-khai";
    // } else {
    // return "redirect:/danh-sach/trien-khai";
    // }
    // }
    // @RequestMapping(value = "/import", method = RequestMethod.POST)
    // ResponseEntity<ResponseObject> mapReapExcelDatatoDB(@RequestParam("file")
    // MultipartFile reapExcelDataFile) {
    // List<Map<String, String>> dataError =
    // projectReportService.checkFileExcelImportReport(reapExcelDataFile);

    // if (dataError.isEmpty()) {
    // // Chưa làm (thực hiện import)
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("success", "Thông tin 1 thành viên thuộc báo cáo", ""));
    // } else {
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("failed", "File lỗi", dataError));
    // }
    // }
    @RequestMapping(value = "/import/{type}", method = RequestMethod.POST)
    ResponseEntity<ResponseObject> mapReapExcelDatatoDB(@PathVariable Long type,
            @RequestParam("file") MultipartFile readExcelDataFile, HttpSession session) {
        String username = (String) session.getAttribute("username");
        int week = (int) session.getAttribute("currentWeek");
        int year = (int) session.getAttribute("currentYear");
        List<Map<String, String>> dataError = projectReportService.processingImportReport(
                readExcelDataFile, username, type, week, year);

        if (dataError.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Import file excel thành công!", ""));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Thất bại! Vui lòng chỉnh sửa file và thử lại.", dataError));
        }
    }

    @RequestMapping(value = "/export/{type}", method = RequestMethod.POST)
    public void exportIntoExcelFile(HttpServletResponse response, @ModelAttribute ExportInputDTO dataInput,
            @PathVariable Integer type) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "";
        Integer week = dataInput.getWeekExport();
        Integer year = dataInput.getYearExport();
        if (type == 1) {
            headerValue = "attachment; filename=Vien thong tuan " + week + " nam " + year + " "
                    + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<ExportVienThongDTO> listOfReport = projectReportService.findAllExportVienThong(type, week, year);
            ExcelGenerator1 generator = new ExcelGenerator1(listOfReport);
            generator.generateExcelFile(response);
        }

        if (type == 2) {
            headerValue = "attachment; filename=Chuyen doi so tuan " + week + " nam " + year + " "
                    + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<ExportChuyenDoiSoDTO> listOfReport = projectReportService.findAllExportChuyenDoiSo(type, week, year);
            ExcelGenerator2 generator = new ExcelGenerator2(listOfReport);
            generator.generateExcelFile(response);
        }

        if (type == 3) {
            headerValue = "attachment; filename=Trien khai tuan " + week + " nam " + year + " "
                    + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<ExportTrienKhaiDTO> listOfReport = projectReportService.findAllExportTrienKhai(type, week, year);
            ExcelGenerator3 generator = new ExcelGenerator3(listOfReport);
            generator.generateExcelFile(response);
        }
    }

    // public List<Map<String, String>> readExcelData3(MultipartFile
    // readExcelDataFile) throws IOException {
    // List<Map<String, String>> dataError = new ArrayList<>();

    // XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
    // XSSFSheet worksheet = workbook.getSheetAt(0);

    // for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
    // XSSFRow row = worksheet.getRow(i);
    // for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
    // if (row.getCell(j) != null) {
    // System.out.println("Lần === " + j);
    // switch (row.getCell(j).getCellType()) {
    // case STRING:
    // System.out.println("String: " + row.getCell(j).getStringCellValue());
    // break;
    // case NUMERIC:
    // System.out.println("Number: " + row.getCell(j).getNumericCellValue());
    // break;
    // case BOOLEAN:
    // System.out.println("Boolean: " + row.getCell(j).getBooleanCellValue());
    // break;
    // case BLANK:
    // System.out.println("Blank: " + row.getCell(j).getNumericCellValue());
    // break;
    // default:

    // }
    // } else {
    // System.out.println("Dòng " + (i + 1) + " rỗng");
    // break;
    // }
    // }
    // }
    // workbook.close();
    // return dataError;
    // }

    @RequestMapping(value = "/customer/uploadAvatar", method = RequestMethod.POST)
    ResponseEntity<ResponseObject> uploadAvatar(@RequestParam("avatarFile") MultipartFile avatarFile) {
        if (avatarFile.getOriginalFilename() == "image_undefined_2.jpg") {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("donothing", "File ảnh mặc định hoặc rỗng!", null));
        }

        String filename = storageService.storeFile(avatarFile);
        if (filename != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "File ảnh lưu thành công!", filename));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "File ảnh lưu thất bại!", ""));
        }
    }
}
