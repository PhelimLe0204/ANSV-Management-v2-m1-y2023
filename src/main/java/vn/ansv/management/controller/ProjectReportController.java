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

    @RequestMapping(value = "/danh-sach/report-pm", method = RequestMethod.GET)
    public ModelAndView viewReportByPm(HttpSession session, HttpServletRequest request) {
        int currentPage = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
                pageSize = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return new ModelAndView("redirect:/");
            }
        }

        // Lấy last url
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);

        Init(session); // Lấy dữ liệu cơ bản
        String userRole = (String) session.getAttribute("userRole");
        userRole = userRole.substring(0, userRole.indexOf("___"));
        if (userRole.contains("Main_PM") == false) {
            session.setAttribute("authorizationError", "Chỉ PM có thể truy cập!");
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        int week = (int) session.getAttribute("thisWeek");
        int year = (int) session.getAttribute("thisYear");

        ResponseObject dataType3Week = projectReportService.findListReportType3(
                week, year, username, 3L, currentPage, pageSize);
        _mvShare.addObject("listReportPM", dataType3Week != null ? dataType3Week : null);
        _mvShare.setViewName("non-admin/report/report-pm");
        return _mvShare;
    }

    @RequestMapping(value = "/danh-sach/kd-vien-thong", method = RequestMethod.GET)
    public ModelAndView viewReportType1(HttpSession session, HttpServletRequest request) {
        int currentPage = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
                pageSize = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return new ModelAndView("redirect:/");
            }
        }

        // Lay last url
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);

        Init(session); // Lấy dữ liệu cơ bản
        String userRole = (String) session.getAttribute("userRole");
        userRole = userRole.substring(0, userRole.indexOf("___"));
        if (userRole.contains("DOC_DO") || userRole.contains("Manager_PM")
                || userRole.contains("Main_PM") || userRole.contains("Member_PM")) {
            session.setAttribute("authorizationError", "Không đủ quyền hạn để truy cập!");
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        int week = (int) session.getAttribute("thisWeek");
        int year = (int) session.getAttribute("thisYear");
        if (userRole.contains("Admin") || userRole.contains("CEO")
                || userRole.contains("DGD") || userRole.contains("Manager_AM")) {
            ResponseObject dataType1Week = projectReportService.findAllReportType12(
                    week, year, null, 1L, currentPage, pageSize);
            _mvShare.addObject("listReportType1Week", dataType1Week != null ? dataType1Week : null);
            _mvShare.setViewName("non-admin/report/kd-vien-thong");
            return _mvShare;
        } else {
            ResponseObject dataType1Week = projectReportService.findAllReportType12(
                    week, year, username, 1L, currentPage, pageSize);
            _mvShare.addObject("listReportType1Week", dataType1Week != null ? dataType1Week : null);
            _mvShare.setViewName("non-admin/report/kd-vien-thong");
            return _mvShare;
        }
    }

    @RequestMapping(value = "/danh-sach/kd-chuyen-doi-so", method = RequestMethod.GET)
    public ModelAndView viewReportType2(HttpSession session, HttpServletRequest request) {
        int currentPage = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
                pageSize = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return new ModelAndView("redirect:/");
            }
        }

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
            session.setAttribute("authorizationError", "Không đủ quyền hạn để truy cập!");
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        int week = (int) session.getAttribute("thisWeek");
        int year = (int) session.getAttribute("thisYear");
        if (userRole.contains("Admin") || userRole.contains("CEO")
                || userRole.contains("DGD") || userRole.contains("Manager_AM")) {
            ResponseObject dataType2Week = projectReportService.findAllReportType12(
                    week, year, null, 2L, currentPage, pageSize);
            _mvShare.addObject("listReportType2Week", dataType2Week != null ? dataType2Week : null);
            _mvShare.setViewName("non-admin/report/kd-chuyen-doi-so");
            return _mvShare;
        } else {
            ResponseObject dataType2Week = projectReportService.findAllReportType12(
                    week, year, username, 2L, currentPage, pageSize);
            _mvShare.addObject("listReportType2Week", dataType2Week != null ? dataType2Week : null);
            _mvShare.setViewName("non-admin/report/kd-chuyen-doi-so");
            return _mvShare;
        }
    }

    @RequestMapping(value = "/danh-sach/trien-khai", method = RequestMethod.GET)
    public ModelAndView viewReportType3(HttpSession session, HttpServletRequest request) {
        int currentPage = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
                pageSize = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return new ModelAndView("redirect:/");
            }
        }

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
            session.setAttribute("authorizationError", "Không đủ quyền hạn để truy cập!");
            return new ModelAndView("redirect:/");
        }
        String username = (String) session.getAttribute("username");
        int week = (int) session.getAttribute("thisWeek");
        int year = (int) session.getAttribute("thisYear");
        if (userRole.contains("Admin") || userRole.contains("CEO")
                || userRole.contains("DGD") || userRole.contains("Manager_PM")) {
            ResponseObject dataType3Week = projectReportService.findListReportType3(
                    week, year, null, 3L, currentPage, pageSize);
            _mvShare.addObject("listReportType3Week", dataType3Week != null ? dataType3Week : null);
            _mvShare.setViewName("non-admin/report/trien-khai");
            return _mvShare;
        } else {
            ResponseObject dataType3Week = projectReportService.findListReportType3(
                    week, year, username, 3L, currentPage, pageSize);
            _mvShare.addObject("listReportType3Week", dataType3Week != null ? dataType3Week : null);
            _mvShare.setViewName("non-admin/report/trien-khai");
            return _mvShare;
        }
    }

    @RequestMapping(value = "report/addNew/{type}", method = RequestMethod.POST)
    public String addNew(HttpSession session, @ModelAttribute AddNewReportDTO dataUpdate, @PathVariable Integer type) {
        Integer result = projectReportService.addNewReport(dataUpdate);
        session.setAttribute("thisWeek", dataUpdate.getWeek());
        session.setAttribute("thisYear", dataUpdate.getYear());
        if (type == 3) {
            return "redirect:/danh-sach/trien-khai?addNew=" + result;
        }
        if (type == 2) {
            return "redirect:/danh-sach/kd-chuyen-doi-so?addNew=" + result;
        }
        if (type == 1) {
            return "redirect:/danh-sach/kd-vien-thong?addNew=" + result;
        }

        return "redirect:/dashboard";

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
    ResponseEntity<ResponseObject> exportReport(HttpSession session,
            @ModelAttribute ExportInputDTO dataInput, @PathVariable Integer type) {
        Integer week = dataInput.getWeekExport();
        Integer year = dataInput.getYearExport();
        String userRole = (String) session.getAttribute("userRole");
        Long userId = (Long) session.getAttribute("userId");
        userRole = userRole.substring(0, userRole.indexOf("___"));

        String msg = "Có lỗi! Vui lòng thử lại sau.";
        if (type == 1) {
            List<ExportVienThongDTO> listOfReport = projectReportService.findAllExportVienThong(type, week, year);
            if (listOfReport.size() == 0) {
                msg = "KD VT tuần " + week + " năm " + year + " không có dữ liệu!";
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", msg, null));
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cho phép export!", null));
        }

        if (type == 2) {
            List<ExportChuyenDoiSoDTO> listOfReport = projectReportService.findAllExportChuyenDoiSo(type, week, year);
            if (listOfReport.size() == 0) {
                msg = "KD CĐS tuần " + week + " năm " + year + " không có dữ liệu!";
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", msg, null));
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cho phép export!", null));
        }

        if (type == 3) {
            List<ExportTrienKhaiDTO> listOfReport = projectReportService.findAllExportTrienKhai(
                    userRole, userId, type, week, year);
            if (listOfReport.size() == 0) {
                msg = "Triển khai tuần " + week + " năm " + year + " không có dữ liệu!";
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", msg, null));
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cho phép export!", null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", msg, null));
    }

    @RequestMapping(value = "/do-export/{type}", method = RequestMethod.POST)
    public void exportIntoExcelFile(HttpSession session, HttpServletResponse response,
            @ModelAttribute ExportInputDTO dataInput,
            @PathVariable Integer type) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "";
        Integer week = dataInput.getWeekExport();
        Integer year = dataInput.getYearExport();
        String userRole = (String) session.getAttribute("userRole");
        Long userId = (Long) session.getAttribute("userId");
        userRole = userRole.substring(0, userRole.indexOf("___"));

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

            List<ExportTrienKhaiDTO> listOfReport = projectReportService.findAllExportTrienKhai(
                    userRole, userId, type, week, year);
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
