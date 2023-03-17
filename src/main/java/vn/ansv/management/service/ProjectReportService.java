package vn.ansv.management.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabQuaTrinhDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabCptgDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Report.AddNewReportDTO;
import vn.ansv.management.dto.Report.ListReport12DTO;
import vn.ansv.management.dto.Report.ListReport3DTO;
import vn.ansv.management.dto.Report.ShowDashboardDTO;
import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.repository.CustomerRepository;
import vn.ansv.management.repository.ProjectReportRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.repository.UserRepository;
import vn.ansv.management.service.Interface.IProjectReport;

@Service
public class ProjectReportService implements IProjectReport {
    @Autowired
    private ProjectReportRepository projectReportRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<ProjectDashboardDTO> findAllDashboardProjectStep1(int enabled, Long type, int week, int year) {
        return projectReportRepository.findAllDashboardProjectStep1(enabled, type, week, year);
    }

    @Override
    public List<ProjectDashboardDTO> findAllDashboardProjectStep2(int enabled, Long type, int week, int year) {
        return projectReportRepository.findAllDashboardProjectStep2(enabled, type, week, year);
    }

    /*
     * =======================================
     * ----- Start: Detail tab phân loại -----
     * =======================================
     */
    @Override
    public ReportDetailTabPhanLoaiDTO findDetailTabPhanLoai(Long id, int enabled) {
        return projectReportRepository.findDetailTabPhanLoai(id, enabled);
    }

    @Override
    public Boolean updateDetailTabPhanLoai(Long id, UpdateDetailTabPhanLoaiDTO dataUpdate) {
        try {
            projectReportRepository.updateDetailTabPhanLoai(id, dataUpdate.getProjectId(),
                    dataUpdate.getProjectTypeId(), dataUpdate.getProjectPriorityId(), dataUpdate.getProjectStatusId(),
                    dataUpdate.getWeek(), dataUpdate.getYear(), dataUpdate.getMaHopDong(), dataUpdate.getMaKeToan(),
                    dataUpdate.getCurrencyUnitId());
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.updateDetailTabPhanLoai(): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /* ========== End: Detail tab phân loại ========== */

    /*
     * =====================================
     * ----- Start: Detail tab dự thầu -----
     * =====================================
     */
    @Override
    public ReportDetailTabDuThauDTO findDetailTabDuThau(Long id, int enabled) {
        return projectReportRepository.findDetailTabDuThau(id, enabled);
    }

    @Override
    public Boolean updateDetailTabDuThau(Long id, UpdateDetailTabDuThauDTO dataUpdate) {
        try {
            // update project_report
            projectReportRepository.updateDetailTabDuThau(id, dataUpdate.getJobName(),
                    dataUpdate.getDescription(), dataUpdate.getPhanTichSwoot(), dataUpdate.getHinhThucDauTu(),
                    dataUpdate.getPhamViCungCap(), dataUpdate.getTongMucDauTuDuKien(), dataUpdate.getMucDoKhaThi());

            int countProject = projectRepository.findByCustomerAndReport(dataUpdate.getId(),
                    dataUpdate.getCustomerId());
            if (countProject < 1) {
                // update project's customer
                projectRepository.updateCustomerByReportId(dataUpdate.getId(), dataUpdate.getCustomerId());
            }
            System.out.println("----- projectRepository.findByCustomerAndReport: " + countProject);
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.updateDetailTabDuThau(): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /* ========== End: Detail tab dự thầu ========== */

    /*
     * =================================================
     * ----- Start: Detail tab chi phí & thời gian -----
     * =================================================
     */
    @Override
    public ReportDetailTabCptgDTO findDetailTabChiPhiThoiGian(Long id, int enabled) {
        return projectReportRepository.findDetailTabChiPhiThoiGian(id, enabled);
    }

    @Override
    public Boolean updateDetailTabCptg(Long id, ReportDetailTabCptgDTO dataUpdate) {
        try {
            projectReportRepository.updateDetailTabCptg(id,
                    dataUpdate.getSoTienDac(), dataUpdate.getHopDongDac(), dataUpdate.getMucTieuDac(),
                    dataUpdate.getThucTeDac(),
                    dataUpdate.getSoTienPac(), dataUpdate.getHopDongPac(), dataUpdate.getMucTieuPac(),
                    dataUpdate.getThucTePac(),
                    dataUpdate.getSoTienFac(), dataUpdate.getHopDongFac(), dataUpdate.getMucTieuFac(),
                    dataUpdate.getThucTeFac(),
                    dataUpdate.getTongGiaTriThucTe(), dataUpdate.getSoTienTamUng(), dataUpdate.getKeHoachTamUng());
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.updateDetailTabCptg(): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /* ========== End: Detail tab chi phí & thời gian ========== */

    /*
     * =======================================
     * ----- Start: Detail tab quá trình -----
     * =======================================
     */
    @Override
    public ReportDetailTabQuaTrinhDTO findDetailTabQuaTrinh(Long id, int enabled) {
        return projectReportRepository.findDetailTabQuaTrinh(id, enabled);
    }

    @Override
    public Boolean updateDetailTabQuaTrinh(Long id, ReportDetailTabQuaTrinhDTO dataUpdate) {
        try {
            projectReportRepository.updateDetailTabQuaTrinh(id, dataUpdate.getGeneralIssue(),
                    dataUpdate.getSolution(), dataUpdate.getKeHoachTuanNay(), dataUpdate.getKeHoachTuanSau(),
                    dataUpdate.getKetQuaTuanTruoc(), dataUpdate.getKetQuaTuanNay());
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.updateDetailTabQuaTrinh(): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /* ========== End: Detail tab quá trình ========== */

    @Override
    public List<ListReport12DTO> findAllReportType12(Long type) {
        try {
            List<ListReport12DTO> result = projectReportRepository.findAllReportType12(type);
            return result;
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
        }
        return null;

    }

    @Override
    public List<ListReport3DTO> findAllReportType3(Long type) {
        try {
            List<ListReport3DTO> result = projectReportRepository.findAllReportType3(type);
            return result;
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
        }
        return null;
    }

    @Override
    public Integer deleteReportById(Long first_report_id) {
        try {
            // Kiểm tra bản ghi có tồn tại không
            int count = projectReportRepository.checkReportById(first_report_id);

            if (count == 0) {
                return 2;
            }

            projectReportRepository.deleteReportById(first_report_id);
            return 1;
        } catch (Exception e) {
            System.out.println("----- Error ----- " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer addNewReport(AddNewReportDTO dataInsert) {
        try {
            String uid = RandomStringUtils.randomAlphanumeric(20);
            dataInsert.setUid(uid);
            dataInsert.setAmId(1L); // Set AM

            // Get username và Set người tạo (AM / PM / AM's manager / PM's manager)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                // Define user's id
                UserDefineDTO user_define = userService.userDefine(currentUserName);
                Long user_id = user_define.getId();
                String user_role = user_define.getUserRole();
                switch (user_role) {
                    case "AM":
                        dataInsert.setAmId(user_id); // Set AM
                        break;
                    case "PM":
                        dataInsert.setPmId(user_id); // Set PM
                        break;
                    case "Manager_AM":
                        dataInsert.setAmManagerId(user_id); // Set AM's manager
                        break;
                    case "Manager_PM":
                        dataInsert.setPmManagerId(user_id); // Set PM's manager
                        break;
                }
                dataInsert.setCreatedBy(currentUserName + " - " + user_id); // Set createdBy
                // return currentUserName;
                // System.out.println("---------- " + currentUserName);
            }

            if (dataInsert.getJobName().isEmpty()) {
                dataInsert.setJobName("Công việc: " + uid); // Test tên công việc
            }

            // Add new report
            projectReportRepository.addNewReport(dataInsert.getUid(), dataInsert.getAmId(), dataInsert.getAmManagerId(),
                    dataInsert.getPmId(), dataInsert.getPmManagerId(), dataInsert.getCreatedBy(),
                    dataInsert.getProjectId(), dataInsert.getProjectTypeId(), dataInsert.getProjectPriorityId(),
                    dataInsert.getProjectStatusId(), dataInsert.getWeek(), dataInsert.getYear(),
                    dataInsert.getMaHopDong(), dataInsert.getMaKeToan(), dataInsert.getCurrencyUnitId(),
                    dataInsert.getJobName(), dataInsert.getDescription(), dataInsert.getMucDoKhaThi(),
                    dataInsert.getTongMucDauTuDuKien(), dataInsert.getHinhThucDauTu(), dataInsert.getPhamViCungCap(),
                    dataInsert.getPhanTichSwoot(), dataInsert.getSoTienDac(), dataInsert.getHopDongDac(),
                    dataInsert.getMucTieuDac(), dataInsert.getThucTeDac(), dataInsert.getSoTienPac(),
                    dataInsert.getHopDongPac(), dataInsert.getMucTieuPac(), dataInsert.getThucTePac(),
                    dataInsert.getSoTienFac(), dataInsert.getHopDongFac(), dataInsert.getMucTieuFac(),
                    dataInsert.getThucTeFac(), dataInsert.getTongGiaTriThucTe(), dataInsert.getSoTienTamUng(),
                    dataInsert.getKeHoachTamUng(), dataInsert.getGeneralIssue(), dataInsert.getSolution(),
                    dataInsert.getKeHoachTuanNay(), dataInsert.getKeHoachTuanSau(), dataInsert.getKetQuaTuanTruoc(),
                    dataInsert.getKetQuaTuanNay());

            // Cập nhật khách hàng
            if (dataInsert.getCustomerId() != projectRepository.findCustomerIdById(dataInsert.getProjectId())) {
                projectRepository.updateCustomerById(dataInsert.getProjectId(), dataInsert.getCustomerId());
            }

            return 1;
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.addNewReport(): " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    // Show maodel dashboard
    @Override
    public List<ShowDashboardDTO> modalShowDashboard(int enabled, int week, int year, Long status, Long type) {
        return projectReportRepository.modalShowDashboard(enabled, week, year, status, type);
    }

    // public void readExcelData1(MultipartFile readExcelDataFile) throws
    // IOException {
    // // List<Test> tempStudentList = new ArrayList<Test>();
    // XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
    // XSSFSheet worksheet = workbook.getSheetAt(0);

    // for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
    // // Test tempStudent = new Test();

    // XSSFRow row = worksheet.getRow(i);
    // if (i == 0) {
    // for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
    // System.out.println(j + ": " + row.getCell(j).getStringCellValue());
    // }
    // } else {
    // System.out.println("0: " + row.getCell(0).getNumericCellValue());
    // System.out.println("1: " + row.getCell(1).getStringCellValue());
    // }

    // // tempStudent.setId((int) row.getCell(0).getNumericCellValue());
    // // tempStudent.setContent(row.getCell(1).getStringCellValue());
    // // tempStudentList.add(tempStudent);
    // }
    // }

    public List<Map<String, String>> readExcelData34(MultipartFile readExcelDataFile) throws IOException {
        List<Map<String, String>> dataError = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
            System.out.println("=============================== Tổng số dòng: " + worksheet.getPhysicalNumberOfRows());
            System.out.println("=============================== Dòng: " + i);

            XSSFRow row = worksheet.getRow(i);
            System.out.println("=============================== Tổng Số cột: " + row.getPhysicalNumberOfCells());
            if (i == 0) {
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    System.out.println(j + ": " + row.getCell(j).getStringCellValue());
                }
            } else {
                System.out.println(" 0: " + row.getCell(0).getNumericCellValue());
                System.out.println(" 1: " + row.getCell(1).getStringCellValue());
                System.out.println(" 2: " + row.getCell(2).getStringCellValue());
                System.out.println(" 3: " + row.getCell(3).getStringCellValue());
                System.out.println(" 4: " + row.getCell(4).getStringCellValue());
                System.out.println(" 5: " + row.getCell(5).getStringCellValue());
                System.out.println(" 6: " + row.getCell(6).getNumericCellValue());
                System.out.println(" 7: " + row.getCell(7).getStringCellValue());
                System.out.println(" 8: " + row.getCell(8).getStringCellValue());
                System.out.println(" 9: " + row.getCell(9).getStringCellValue());
                System.out.println("10: " + row.getCell(10).getStringCellValue());
                System.out.println("11: " + row.getCell(11).getStringCellValue());
                System.out.println("12: " + row.getCell(12).getStringCellValue());
                System.out.println("13: " + row.getCell(13).getStringCellValue());
                System.out.println("14: " + row.getCell(14).getStringCellValue());
                System.out.println("15: " + row.getCell(15).getStringCellValue());
                System.out.println("16: " + row.getCell(16).getStringCellValue());
                System.out.println("17: " + row.getCell(17).getStringCellValue());

                Map<String, String> errors = new HashMap<String, String>();

                /* Check dữ liệu file */
                // 1. Kiểm tra tên dự án đã tồn tại hay chưa
                int checkDuAn = projectRepository
                        .checkIssetByProjectName(row.getCell(1).getStringCellValue());
                if (checkDuAn > 0) {
                    errors.put("position", "B" + (i + 1));
                    errors.put("error", "Dự án trùng lặp");
                    // errorPossition = "Dòng " + i + ": Dự án đã tồn tại";
                    // workbook.close();
                    // break;
                }

                // 2. Kiểm tra khách hàng đã tồn tại hay chưa
                int checkKhachHang = customerRepository
                        .checkIssetByCustomerName(row.getCell(2).getStringCellValue());
                if (checkKhachHang == 0) {
                    errors.put("position", "C" + (i + 1));
                    errors.put("error", "Khách hàng không tồn tại");
                }

                // 3. Kiểm tra Priority
                String excelPriority = row.getCell(10).getStringCellValue();
                if (excelPriority != "First" && excelPriority != "Second" && excelPriority != "Third") {
                    errors.put("position", "K" + (i + 1));
                    errors.put("error", "Priority không xác định");
                }

                // 4. Kiểm tra Status
                String excelStatus = row.getCell(11).getStringCellValue();
                if (excelStatus != "High" && excelStatus != "Medium" && excelStatus != "Low") {
                    errors.put("position", "L" + (i + 1));
                    errors.put("error", "Status không xác định");
                }

                // 4. Kiểm tra PIC
                int checkPic = userRepository
                        .checkIssetByFullname(row.getCell(12).getStringCellValue());
                if (checkPic == 0) {
                    errors.put("position", "M" + (i + 1));
                    errors.put("error", "PIC không tồn tại");
                }

                // 5. Kiểm tra phó ban
                int checkPhoBan = userRepository
                        .checkIssetByFullname(row.getCell(13).getStringCellValue());
                if (checkPhoBan == 0) {
                    errors.put("position", "N" + (i + 1));
                    errors.put("error", "Phó ban không tồn tại");
                }

                dataError.add(i, errors); // Đẩy lỗi vào list
                // AddNewReportDTO data = new AddNewReportDTO();

                // String uid = RandomStringUtils.randomAlphanumeric(20);
                // data.setUid(uid);

                // System.out.println("0: " + row.getCell(0).getNumericCellValue());
                // System.out.println("1: " + row.getCell(1).getStringCellValue());
            }
            // tempStudent.setId((int) row.getCell(0).getNumericCellValue());
            // tempStudent.setContent(row.getCell(1).getStringCellValue());
            // tempStudentList.add(tempStudent);
        }
        workbook.close();
        return dataError;
    }

    public List<Map<String, String>> checkExcelData3(MultipartFile readExcelDataFile) {
        List<Map<String, String>> dataError = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);

                if (row.getCell(0) != null) {
                    /* Check dữ liệu file */
                    // 1. Kiểm tra tên dự án đã tồn tại hay chưa
                    int checkDuAn = projectRepository
                            .checkIssetByProjectName(row.getCell(1).getStringCellValue());
                    if (checkDuAn > 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "B" + (i + 1));
                        errors.put("error", "Dự án trùng lặp");
                        dataError.add(errors);
                        // errorPossition = "Dòng " + i + ": Dự án đã tồn tại";
                        // workbook.close();
                        // break;
                    }

                    // 2. Kiểm tra khách hàng đã tồn tại hay chưa
                    int checkKhachHang = customerRepository
                            .checkIssetByCustomerName(row.getCell(2).getStringCellValue());
                    if (checkKhachHang == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "C" + (i + 1));
                        errors.put("error", "Khách hàng không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 3. Kiểm tra Priority
                    String excelPriority = row.getCell(10).getStringCellValue();
                    if (excelPriority != "First" && excelPriority != "Second" && excelPriority != "Third") {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "K" + (i + 1));
                        errors.put("error", "Priority không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 4. Kiểm tra Status
                    String excelStatus = row.getCell(11).getStringCellValue();
                    if (excelStatus != "High" && excelStatus != "Medium" && excelStatus != "Low") {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "L" + (i + 1));
                        errors.put("error", "Status không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 5. Kiểm tra PIC
                    int checkPic = userRepository
                            .checkIssetByFullname(row.getCell(12).getStringCellValue());
                    if (checkPic == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "M" + (i + 1));
                        errors.put("error", "PIC không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 6. Kiểm tra phó ban
                    int checkPhoBan = userRepository
                            .checkIssetByFullname(row.getCell(13).getStringCellValue());
                    if (checkPhoBan == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "N" + (i + 1));
                        errors.put("error", "Phó ban không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // dataError.add((i - 1), errors); // Đẩy lỗi vào list
                } else {
                    System.out.println("Dòng " + i + " rỗng");
                    break;
                }

                // for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                // System.out.println("Cell - " + i);
                // Map<String, String> errors = new HashMap<String, String>();

                // if (row.getCell(0) != null) {
                // System.out.println("Dòng " + i + " không rỗng");
                // /* Check dữ liệu file */
                // // 1. Kiểm tra tên dự án đã tồn tại hay chưa
                // int checkDuAn = projectRepository
                // .checkIssetByProjectName(row.getCell(1).getStringCellValue());
                // if (checkDuAn > 0) {
                // errors.put("position", "B" + (i + 1));
                // errors.put("error", "Dự án trùng lặp");
                // dataError.add(errors);
                // // errorPossition = "Dòng " + i + ": Dự án đã tồn tại";
                // // workbook.close();
                // // break;
                // }

                // // 2. Kiểm tra khách hàng đã tồn tại hay chưa
                // int checkKhachHang = customerRepository
                // .checkIssetByCustomerName(row.getCell(2).getStringCellValue());
                // if (checkKhachHang == 0) {
                // errors.put("position", "C" + (i + 1));
                // errors.put("error", "Khách hàng không tồn tại");
                // dataError.add(errors); // Đẩy lỗi vào list
                // }

                // // 3. Kiểm tra Priority
                // String excelPriority = row.getCell(10).getStringCellValue();
                // if (excelPriority != "First" && excelPriority != "Second" && excelPriority !=
                // "Third") {
                // errors.put("position", "K" + (i + 1));
                // errors.put("error", "Priority không xác định");
                // dataError.add(errors); // Đẩy lỗi vào list
                // }

                // // 4. Kiểm tra Status
                // String excelStatus = row.getCell(11).getStringCellValue();
                // if (excelStatus != "High" && excelStatus != "Medium" && excelStatus != "Low")
                // {
                // errors.put("position", "L" + (i + 1));
                // errors.put("error", "Status không xác định");
                // dataError.add(errors); // Đẩy lỗi vào list
                // }

                // // 5. Kiểm tra PIC
                // int checkPic = userRepository
                // .checkIssetByFullname(row.getCell(12).getStringCellValue());
                // if (checkPic == 0) {
                // errors.put("position", "M" + (i + 1));
                // errors.put("error", "PIC không tồn tại");
                // dataError.add(errors); // Đẩy lỗi vào list
                // }

                // // 6. Kiểm tra phó ban
                // int checkPhoBan = userRepository
                // .checkIssetByFullname(row.getCell(13).getStringCellValue());
                // if (checkPhoBan == 0) {
                // errors.put("position", "N" + (i + 1));
                // errors.put("error", "Phó ban không tồn tại");
                // dataError.add(errors); // Đẩy lỗi vào list
                // }

                // // dataError.add((i - 1), errors); // Đẩy lỗi vào list
                // } else {
                // System.out.println("Dòng " + i + " rỗng");
                // break;
                // }

                // // if (row.getCell(j) != null) {
                // // System.out.println("Lần === " + j);
                // // switch (row.getCell(j).getCellType()) {
                // // case STRING:
                // // System.out.println("String: " + row.getCell(j).getStringCellValue());
                // // break;
                // // case NUMERIC:
                // // System.out.println("Number: " + row.getCell(j).getNumericCellValue());
                // // break;
                // // case BOOLEAN:
                // // System.out.println("Boolean: " + row.getCell(j).getBooleanCellValue());
                // // break;
                // // case BLANK:
                // // System.out.println("Blank: " + row.getCell(j).getNumericCellValue());
                // // break;
                // // default:

                // // }
                // // } else {
                // // System.out.println("Dòng " + (i + 1) + " rỗng");
                // // break;
                // // }
                // }
            }
            workbook.close();
            return dataError;
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.checkExcelData3(): " + e.getMessage());
            e.printStackTrace();
            return dataError;
        }
    }

    @Override
    public List<Map<String, String>> checkFileExcelImportReport(MultipartFile readExcelDataFile) {
        List<Map<String, String>> dataError = new ArrayList<>();

        try {
            String fileName = readExcelDataFile.getOriginalFilename();

            if (fileName != null) {
                if (fileName.contains("Vien thong")) {
                    dataError = checkExcelData3(readExcelDataFile);
                    return dataError;
                }
                if (fileName.contains("Chuyen doi so")) {
                    // Chưa làm
                    dataError = checkExcelData3(readExcelDataFile);
                    return dataError;
                }
                if (fileName.contains("Trien khai")) {
                    // Chưa làm
                    dataError = checkExcelData3(readExcelDataFile);
                    return dataError;
                }
                Map<String, String> errors = new HashMap<String, String>();
                errors.put("position", "Tên file");
                errors.put("error", "Tên file không đúng định dạng");
                dataError.add(0, errors); // Đẩy lỗi vào list
                return dataError;
            } else {
                Map<String, String> errors = new HashMap<String, String>();
                errors.put("position", "Tên file");
                errors.put("error", "Tên file rỗng");
                dataError.add(0, errors);
                return dataError;
            }
        } catch (Exception e) {
            System.out
                    .println("----- Error ----- ProjectReportService.checkFileExcelImportReport(): " + e.getMessage());
            e.printStackTrace();
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("position", "Hệ thống");
            errors.put("error", "Lỗi hệ thống");
            dataError.add(0, errors);
            return dataError;
        }
    }
}
