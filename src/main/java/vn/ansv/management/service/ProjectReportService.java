package vn.ansv.management.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import vn.ansv.management.dto.Export.ExportChuyenDoiSoDTO;
import vn.ansv.management.dto.Export.ExportTrienKhaiDTO;
import vn.ansv.management.dto.Export.ExportVienThongDTO;
import vn.ansv.management.dto.Report.AddNewReportDTO;
import vn.ansv.management.dto.Report.ListReport12DTO;
import vn.ansv.management.dto.Report.ListReport3DTO;
import vn.ansv.management.dto.Report.ShowDashboardDTO;
import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.repository.CustomerRepository;
import vn.ansv.management.repository.ProjectPriorityRepository;
import vn.ansv.management.repository.ProjectReportRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.repository.ProjectStatusRepository;
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
    private ProjectPriorityRepository projectPriorityRepository;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

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
                    dataUpdate.getSoTienGiaoHang(), dataUpdate.getHopDongGiaoHang(), dataUpdate.getMucTieuGiaoHang(),
                    dataUpdate.getThucTeGiaoHang(), dataUpdate.getNoteGiaoHang(),
                    dataUpdate.getSoTienDac(), dataUpdate.getHopDongDac(), dataUpdate.getMucTieuDac(),
                    dataUpdate.getThucTeDac(), dataUpdate.getNoteDac(),
                    dataUpdate.getSoTienPac(), dataUpdate.getHopDongPac(), dataUpdate.getMucTieuPac(),
                    dataUpdate.getThucTePac(), dataUpdate.getNotePac(),
                    dataUpdate.getSoTienFac(), dataUpdate.getHopDongFac(), dataUpdate.getMucTieuFac(),
                    dataUpdate.getThucTeFac(), dataUpdate.getNoteFac(),
                    dataUpdate.getTongGiaTriThucTe(), dataUpdate.getNoteTongGiaTri(), dataUpdate.getSoTienTamUng(),
                    dataUpdate.getKeHoachTamUng(), dataUpdate.getNoteTamUng());
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
                    dataInsert.getJobName(), dataInsert.getDescription(), 1, dataInsert.getMucDoKhaThi(),
                    dataInsert.getTongMucDauTuDuKien(), dataInsert.getHinhThucDauTu(), dataInsert.getPhamViCungCap(),
                    dataInsert.getPhanTichSwoot(), dataInsert.getSoTienGiaoHang(),
                    (dataInsert.getHopDongGiaoHang().length() != 0) ? dataInsert.getHopDongGiaoHang() : null,
                    (dataInsert.getMucTieuGiaoHang().length() != 0) ? dataInsert.getMucTieuGiaoHang() : null,
                    (dataInsert.getThucTeGiaoHang().length() != 0) ? dataInsert.getThucTeGiaoHang() : null,
                    dataInsert.getNoteGiaoHang(), dataInsert.getSoTienDac(),
                    (dataInsert.getHopDongDac().length() != 0) ? dataInsert.getHopDongDac() : null,
                    (dataInsert.getMucTieuDac().length() != 0) ? dataInsert.getMucTieuDac() : null,
                    (dataInsert.getThucTeDac().length() != 0) ? dataInsert.getThucTeDac() : null,
                    dataInsert.getNoteDac(), dataInsert.getSoTienPac(),
                    (dataInsert.getHopDongPac().length() != 0) ? dataInsert.getHopDongPac() : null,
                    (dataInsert.getMucTieuPac().length() != 0) ? dataInsert.getMucTieuPac() : null,
                    (dataInsert.getThucTePac().length() != 0) ? dataInsert.getThucTePac() : null,
                    dataInsert.getNotePac(), dataInsert.getSoTienFac(),
                    (dataInsert.getHopDongFac().length() != 0) ? dataInsert.getHopDongFac() : null,
                    (dataInsert.getMucTieuFac().length() != 0) ? dataInsert.getMucTieuFac() : null,
                    (dataInsert.getThucTeFac().length() != 0) ? dataInsert.getThucTeFac() : null,
                    dataInsert.getNoteFac(), dataInsert.getTongGiaTriThucTe(), dataInsert.getNoteTongGiaTri(),
                    dataInsert.getSoTienTamUng(), dataInsert.getKeHoachTamUng(), dataInsert.getNoteTamUng(),
                    dataInsert.getTienDoChung(), dataInsert.getGeneralIssue(), dataInsert.getSolution(),
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

    public List<Map<String, String>> readExcelDataTest(MultipartFile readExcelDataFile) throws IOException {
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
                Long checkKhachHang = customerRepository.findIdByCustomerName(row.getCell(2).getStringCellValue());
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

    /*
     * Kiểm tra file excel (Viễn thông / Chuyển đổi số)
     * TRUE => Thực hiện import
     * FALSE => Trả về thông tin lỗi
     */
    public List<Map<String, String>> checkExcelDataType1AndType2(
            MultipartFile readExcelDataFile, String username, Long type, Integer week, Integer year) {
        List<Map<String, String>> dataError = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);

                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    /* Check dữ liệu file */
                    // 1. Dự án
                    if (row.getCell(1).getRawValue() == null) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "B" + (i + 1));
                        errors.put("error", "Tên dự án trống");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 2. Khách hàng
                    Long checkKhachHang = customerRepository.findIdByCustomerName(row.getCell(2).getStringCellValue());
                    if (checkKhachHang == null) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "C" + (i + 1));
                        errors.put("error", "Khách hàng không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 3. Priority
                    String excelPriority = row.getCell(10).getStringCellValue();
                    if (!excelPriority.equals("First") && !excelPriority.equals("Second")
                            && !excelPriority.equals("Third")) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "K" + (i + 1));
                        errors.put("error", "Priority không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 4. Status
                    String excelStatus = row.getCell(11).getStringCellValue();
                    if (!excelStatus.equals("High") && !excelStatus.equals("Medium")
                            && !excelStatus.equals("Low")) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "L" + (i + 1));
                        errors.put("error", "Status không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 5. PIC
                    int checkPic = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(12).getStringCellValue(), "AM");
                    if (checkPic == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "M" + (i + 1));
                        errors.put("error", "PIC không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 6. Phó ban
                    int checkPhoBan = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(13).getStringCellValue(), "Manager_AM");
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
            }

            if (!dataError.isEmpty()) {
                return dataError; // Nếu dữ liệu excel có lỗi => Dừng và trả về thông tin lỗi
            }

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);

                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    // Tìm kiếm báo cáo theo tên công việc, tuần, năm
                    Long reportId = projectReportRepository.findIdByJobNameWeekYear(
                            row.getCell(1).getStringCellValue(), week, year);

                    String uid = RandomStringUtils.randomAlphanumeric(20);
                    // customer's id
                    Long customerId = customerRepository.findIdByCustomerName(row.getCell(2).getStringCellValue());
                    // Kiểm tra tên dự án đã tồn tại hay chưa, nếu đã tồn tại => Lấy Project's ID
                    Long projectId = projectRepository.findIdByProjectName(row.getCell(1).getStringCellValue());
                    if (projectId == null) {
                        // Thêm mới project
                        projectRepository.addNewProject(username, uid, row.getCell(3).getStringCellValue(), 1,
                                row.getCell(1).getStringCellValue(), customerId);
                        projectId = projectRepository.findIdByProjectName(row.getCell(1).getStringCellValue());
                    }

                    // String jobName = row.getCell(1).getStringCellValue();
                    // String description = row.getCell(3).getStringCellValue();
                    // String hinhThucDauTu = row.getCell(4).getStringCellValue();
                    // String tongMucDauTuDuKien = row.getCell(5).getStringCellValue();
                    // Integer mucDoKhaThi = (int) row.getCell(6).getNumericCellValue();
                    // String phanTichSwoot = row.getCell(7).getStringCellValue();
                    // String generalIssue = row.getCell(8).getStringCellValue();
                    // String solution = row.getCell(9).getStringCellValue();
                    Long priorityId = projectPriorityRepository
                            .findIdByPriorityName(row.getCell(10).getStringCellValue());
                    Long statusId = projectStatusRepository
                            .findIdByStatusName(row.getCell(11).getStringCellValue());
                    Long amId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(12).getStringCellValue(), "AM");
                    Long amManagerId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(13).getStringCellValue(), "Manager_AM");
                    // String ketQuaTuanTruoc = row.getCell(14).getStringCellValue();
                    // String ketQuaTuanNay = row.getCell(15).getStringCellValue();
                    // String keHoachTuanNay = row.getCell(16).getStringCellValue();
                    // String keHoachTuanSau = row.getCell(17).getStringCellValue();

                    if (reportId == null) {
                        /* Chưa tồn tại báo cáo => Thêm mới */
                        projectReportRepository.addNewReport(uid, amId, amManagerId, null, null,
                                username, projectId, type, priorityId, statusId, week, year, null,
                                null, 1L, row.getCell(1).getStringCellValue(),
                                (row.getCell(3).getStringCellValue().length() != 0)
                                        ? row.getCell(3).getStringCellValue()
                                        : null,
                                1, (int) row.getCell(6).getNumericCellValue(),
                                (row.getCell(5).getStringCellValue().length() != 0)
                                        ? row.getCell(5).getStringCellValue()
                                        : null,
                                (row.getCell(4).getStringCellValue().length() != 0)
                                        ? row.getCell(4).getStringCellValue()
                                        : null,
                                null, (row.getCell(7).getStringCellValue().length() != 0)
                                        ? row.getCell(7).getStringCellValue()
                                        : null,
                                null, null, null, null, null,
                                null, null, null, null, null,
                                null, null, null, null, null,
                                null, null, null, null, null,
                                null, null, null, null, null, null,
                                (row.getCell(8).getStringCellValue().length() != 0)
                                        ? row.getCell(8).getStringCellValue()
                                        : null,
                                (row.getCell(9).getStringCellValue().length() != 0)
                                        ? row.getCell(9).getStringCellValue()
                                        : null,
                                (row.getCell(16).getStringCellValue().length() != 0)
                                        ? row.getCell(16).getStringCellValue()
                                        : null,
                                (row.getCell(17).getStringCellValue().length() != 0)
                                        ? row.getCell(17).getStringCellValue()
                                        : null,
                                (row.getCell(14).getStringCellValue().length() != 0)
                                        ? row.getCell(14).getStringCellValue()
                                        : null,
                                (row.getCell(15).getStringCellValue().length() != 0)
                                        ? row.getCell(15).getStringCellValue()
                                        : null);
                    } else {
                        /* Đã tồn tại báo cáo => Cập nhật */
                        projectReportRepository.updateReport(reportId, uid, amId, amManagerId, null, null,
                                username, projectId, type, priorityId, statusId, week, year, null,
                                null, 1L, row.getCell(1).getStringCellValue(),
                                (row.getCell(3).getStringCellValue().length() != 0)
                                        ? row.getCell(3).getStringCellValue()
                                        : null,
                                1, (int) row.getCell(6).getNumericCellValue(),
                                (row.getCell(5).getStringCellValue().length() != 0)
                                        ? row.getCell(5).getStringCellValue()
                                        : null,
                                (row.getCell(4).getStringCellValue().length() != 0)
                                        ? row.getCell(4).getStringCellValue()
                                        : null,
                                null, (row.getCell(7).getStringCellValue().length() != 0)
                                        ? row.getCell(7).getStringCellValue()
                                        : null,
                                null, null, null, null,
                                null, null, null, null,
                                null, null, null, null,
                                null, null, null, null,
                                (row.getCell(8).getStringCellValue().length() != 0)
                                        ? row.getCell(8).getStringCellValue()
                                        : null,
                                (row.getCell(9).getStringCellValue().length() != 0)
                                        ? row.getCell(9).getStringCellValue()
                                        : null,
                                (row.getCell(16).getStringCellValue().length() != 0)
                                        ? row.getCell(16).getStringCellValue()
                                        : null,
                                (row.getCell(17).getStringCellValue().length() != 0)
                                        ? row.getCell(17).getStringCellValue()
                                        : null,
                                (row.getCell(14).getStringCellValue().length() != 0)
                                        ? row.getCell(14).getStringCellValue()
                                        : null,
                                (row.getCell(15).getStringCellValue().length() != 0)
                                        ? row.getCell(15).getStringCellValue()
                                        : null);
                    }
                } else {
                    System.out.println("Dòng " + (i + 1) + " rỗng");
                    break;
                }
            }

            workbook.close();
            return dataError;
        } catch (Exception e) {
            System.out.println(
                    "----- Error ----- ProjectReportService.checkExcelDataType1AndType2(): " + e.getMessage());
            e.printStackTrace();
            return dataError;
        }
    }

    /*
     * Kiểm tra file excel (Triển khai)
     * TRUE => Thực hiện import
     * FALSE => Trả về thông tin lỗi
     */
    public List<Map<String, String>> checkExcelDataType3(
            MultipartFile readExcelDataFile, String username, Long type, Integer week, Integer year) {
        List<Map<String, String>> dataError = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);

                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    /* Check dữ liệu file */
                    // 1. Dự án
                    if (row.getCell(1).getRawValue() == null) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "B" + (i + 1));
                        errors.put("error", "Tên dự án trống");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 2. Số hợp đồng
                    if (row.getCell(2).getRawValue() == null) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "C" + (i + 1));
                        errors.put("error", "Số hợp đồng trống");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 3. Khách hàng
                    Long checkKhachHang = customerRepository.findIdByCustomerName(row.getCell(4).getStringCellValue());
                    if (checkKhachHang == null) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "E" + (i + 1));
                        errors.put("error", "Khách hàng không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 4. Tổng giá trị thực tế
                    if (row.getCell(5).getRawValue() == null) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "F" + (i + 1));
                        errors.put("error", "Giá trị trống");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 5. Priority
                    String excelPriority = row.getCell(24).getStringCellValue();
                    if (!excelPriority.equals("First") && !excelPriority.equals("Second")
                            && !excelPriority.equals("Third")) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "Y" + (i + 1));
                        errors.put("error", "Priority không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 6. Status
                    String excelStatus = row.getCell(25).getStringCellValue();
                    if (!excelStatus.equals("High") && !excelStatus.equals("Medium")
                            && !excelStatus.equals("Low")) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "Z" + (i + 1));
                        errors.put("error", "Status không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 7. AM
                    int checkAm = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(26).getStringCellValue(), "AM");
                    if (checkAm == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "AA" + (i + 1));
                        errors.put("error", "AM không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 8. PM
                    int checkPm = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(27).getStringCellValue(), "PM");
                    if (checkPm == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "AB" + (i + 1));
                        errors.put("error", "PM không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 8. PM's Manager
                    int checkPmManager = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(28).getStringCellValue(), "Manager_PM");
                    if (checkPmManager == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "AC" + (i + 1));
                        errors.put("error", "Phó ban không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }
                } else {
                    System.out.println("Dòng " + (i + 1) + " rỗng");
                    break;
                }
            }

            if (!dataError.isEmpty()) {
                return dataError; // Nếu dữ liệu excel có lỗi => Dừng và trả về thông tin lỗi
            }

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);

                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    // Tìm kiếm báo cáo theo tên công việc, tuần, năm
                    Long reportId = projectReportRepository.findIdByJobNameWeekYear(
                            row.getCell(1).getStringCellValue(), week, year);
                    String uid = RandomStringUtils.randomAlphanumeric(20);
                    // customer's id
                    Long customerId = customerRepository.findIdByCustomerName(row.getCell(4).getStringCellValue());
                    // Kiểm tra tên dự án đã tồn tại hay chưa, nếu đã tồn tại => Lấy Project's ID
                    Long projectId = projectRepository.findIdByProjectName(row.getCell(1).getStringCellValue());
                    if (projectId == null) {
                        // Thêm mới project
                        projectRepository.addNewProject(username, uid, null, 1,
                                row.getCell(1).getStringCellValue(), customerId);
                        projectId = projectRepository.findIdByProjectName(row.getCell(1).getStringCellValue());
                    }
                    // String jobName = row.getCell(1).getStringCellValue();
                    // String description = row.getCell(3).getStringCellValue();
                    // String hinhThucDauTu = row.getCell(4).getStringCellValue();
                    // String tongMucDauTuDuKien = row.getCell(5).getStringCellValue();
                    // Integer mucDoKhaThi = (int) row.getCell(6).getNumericCellValue();
                    // String phanTichSwoot = row.getCell(7).getStringCellValue();
                    // String generalIssue = row.getCell(8).getStringCellValue();
                    // String solution = row.getCell(9).getStringCellValue();
                    Long priorityId = projectPriorityRepository
                            .findIdByPriorityName(row.getCell(24).getStringCellValue());
                    Long statusId = projectStatusRepository
                            .findIdByStatusName(row.getCell(25).getStringCellValue());
                    Long amId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(26).getStringCellValue(), "AM");
                    Long pmId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(27).getStringCellValue(), "PM");
                    Long pmManagerId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(28).getStringCellValue(), "Manager_PM");
                    // String ketQuaTuanTruoc = row.getCell(14).getStringCellValue();
                    // String ketQuaTuanNay = row.getCell(15).getStringCellValue();
                    // String keHoachTuanNay = row.getCell(16).getStringCellValue();
                    // String keHoachTuanSau = row.getCell(17).getStringCellValue();

                    if (reportId == null) {
                        /* Chưa tồn tại báo cáo => Thêm mới */
                        projectReportRepository.addNewReport(uid, amId, null, pmId, pmManagerId,
                                username, projectId, type, priorityId, statusId, week, year,
                                (row.getCell(2).getStringCellValue().length() != 0)
                                        ? row.getCell(2).getStringCellValue()
                                        : null,
                                (row.getCell(3).getStringCellValue().length() != 0)
                                        ? row.getCell(3).getStringCellValue()
                                        : null,
                                1L,
                                (row.getCell(1).getStringCellValue().length() != 0)
                                        ? row.getCell(1).getStringCellValue()
                                        : null,
                                null, 1, null, null,
                                null, null, null,
                                null, null, null, null, null,
                                (row.getCell(6).getStringCellValue().length() != 0)
                                        ? row.getCell(6).getStringCellValue()
                                        : null,
                                (row.getCell(7).getStringCellValue().length() != 0)
                                        ? row.getCell(7).getStringCellValue()
                                        : null,
                                (row.getCell(8).getStringCellValue().length() != 0)
                                        ? row.getCell(8).getStringCellValue()
                                        : null,
                                (row.getCell(9).getStringCellValue().length() != 0)
                                        ? row.getCell(9).getStringCellValue()
                                        : null,
                                null,
                                (row.getCell(11).getStringCellValue().length() != 0)
                                        ? row.getCell(11).getStringCellValue()
                                        : null,
                                (row.getCell(12).getStringCellValue().length() != 0)
                                        ? row.getCell(12).getStringCellValue()
                                        : null,
                                (row.getCell(13).getStringCellValue().length() != 0)
                                        ? row.getCell(13).getStringCellValue()
                                        : null,
                                (row.getCell(14).getStringCellValue().length() != 0)
                                        ? row.getCell(14).getStringCellValue()
                                        : null,
                                null,
                                (row.getCell(16).getStringCellValue().length() != 0)
                                        ? row.getCell(16).getStringCellValue()
                                        : null,
                                (row.getCell(17).getStringCellValue().length() != 0)
                                        ? row.getCell(17).getStringCellValue()
                                        : null,
                                (row.getCell(18).getStringCellValue().length() != 0)
                                        ? row.getCell(18).getStringCellValue()
                                        : null,
                                (row.getCell(19).getStringCellValue().length() != 0)
                                        ? row.getCell(19).getStringCellValue()
                                        : null,
                                null,
                                (row.getCell(5).getStringCellValue().length() != 0)
                                        ? row.getCell(5).getStringCellValue()
                                        : null,
                                null,
                                null, null, null,
                                (row.getCell(21).getStringCellValue().length() != 0)
                                        ? row.getCell(21).getStringCellValue()
                                        : null,
                                (row.getCell(22).getStringCellValue().length() != 0)
                                        ? row.getCell(22).getStringCellValue()
                                        : null,
                                (row.getCell(23).getStringCellValue().length() != 0)
                                        ? row.getCell(23).getStringCellValue()
                                        : null,
                                (row.getCell(31).getStringCellValue().length() != 0)
                                        ? row.getCell(31).getStringCellValue()
                                        : null,
                                (row.getCell(32).getStringCellValue().length() != 0)
                                        ? row.getCell(32).getStringCellValue()
                                        : null,
                                (row.getCell(29).getStringCellValue().length() != 0)
                                        ? row.getCell(29).getStringCellValue()
                                        : null,
                                (row.getCell(30).getStringCellValue().length() != 0)
                                        ? row.getCell(30).getStringCellValue()
                                        : null);
                    } else {
                        /* Đã tồn tại báo cáo => Cập nhật */
                        projectReportRepository.updateReport(reportId, uid, amId, null, pmId,
                                pmManagerId, username, projectId, type, priorityId, statusId, week, year,
                                (row.getCell(2).getStringCellValue().length() != 0)
                                        ? row.getCell(2).getStringCellValue()
                                        : null,
                                (row.getCell(3).getStringCellValue().length() != 0)
                                        ? row.getCell(3).getStringCellValue()
                                        : null,
                                1L,
                                (row.getCell(1).getStringCellValue().length() != 0)
                                        ? row.getCell(1).getStringCellValue()
                                        : null,
                                null, 1, null, null,
                                null, null, null,
                                (row.getCell(6).getStringCellValue().length() != 0)
                                        ? row.getCell(6).getStringCellValue()
                                        : null,
                                (row.getCell(7).getStringCellValue().length() != 0)
                                        ? row.getCell(7).getStringCellValue()
                                        : null,
                                (row.getCell(8).getStringCellValue().length() != 0)
                                        ? row.getCell(8).getStringCellValue()
                                        : null,
                                (row.getCell(9).getStringCellValue().length() != 0)
                                        ? row.getCell(9).getStringCellValue()
                                        : null,
                                (row.getCell(11).getStringCellValue().length() != 0)
                                        ? row.getCell(11).getStringCellValue()
                                        : null,
                                (row.getCell(12).getStringCellValue().length() != 0)
                                        ? row.getCell(12).getStringCellValue()
                                        : null,
                                (row.getCell(13).getStringCellValue().length() != 0)
                                        ? row.getCell(13).getStringCellValue()
                                        : null,
                                (row.getCell(14).getStringCellValue().length() != 0)
                                        ? row.getCell(14).getStringCellValue()
                                        : null,
                                (row.getCell(16).getStringCellValue().length() != 0)
                                        ? row.getCell(16).getStringCellValue()
                                        : null,
                                (row.getCell(17).getStringCellValue().length() != 0)
                                        ? row.getCell(17).getStringCellValue()
                                        : null,
                                (row.getCell(18).getStringCellValue().length() != 0)
                                        ? row.getCell(18).getStringCellValue()
                                        : null,
                                (row.getCell(19).getStringCellValue().length() != 0)
                                        ? row.getCell(19).getStringCellValue()
                                        : null,
                                (row.getCell(5).getStringCellValue().length() != 0)
                                        ? row.getCell(5).getStringCellValue()
                                        : null,
                                null, null,
                                (row.getCell(21).getStringCellValue().length() != 0)
                                        ? row.getCell(21).getStringCellValue()
                                        : null,
                                (row.getCell(22).getStringCellValue().length() != 0)
                                        ? row.getCell(22).getStringCellValue()
                                        : null,
                                (row.getCell(23).getStringCellValue().length() != 0)
                                        ? row.getCell(23).getStringCellValue()
                                        : null,
                                (row.getCell(31).getStringCellValue().length() != 0)
                                        ? row.getCell(31).getStringCellValue()
                                        : null,
                                (row.getCell(32).getStringCellValue().length() != 0)
                                        ? row.getCell(32).getStringCellValue()
                                        : null,
                                (row.getCell(29).getStringCellValue().length() != 0)
                                        ? row.getCell(29).getStringCellValue()
                                        : null,
                                (row.getCell(30).getStringCellValue().length() != 0)
                                        ? row.getCell(30).getStringCellValue()
                                        : null);
                    }
                } else {
                    System.out.println("Dòng " + i + " rỗng");
                    break;
                }
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
    public List<Map<String, String>> processingImportReport(
            MultipartFile readExcelDataFile, String username, Long type, Integer week, Integer year) {
        List<Map<String, String>> dataError = new ArrayList<>();

        try {
            String fileName = readExcelDataFile.getOriginalFilename();
            String fileType = getFileExtension(fileName);

            if (!fileType.matches("xls|xlsx")) {
                Map<String, String> errors = new HashMap<String, String>();
                errors.put("position", "Định dạng file");
                errors.put("error", "File không thuộc dạng excel (xls / xlsx)");
                dataError.add(0, errors);
                return dataError;
            }

            if (fileName != null) {
                if (fileName.contains("Vien thong")) {
                    dataError = checkExcelDataType1AndType2(readExcelDataFile, username, type, week, year);
                    return dataError;
                }
                if (fileName.contains("Chuyen doi so")) {
                    dataError = checkExcelDataType1AndType2(readExcelDataFile, username, type, week, year);
                    return dataError;
                }
                if (fileName.contains("Trien khai")) {
                    dataError = checkExcelDataType3(readExcelDataFile, username, type, week, year);
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

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    @Override
    public List<ExportVienThongDTO> findAllExportVienThong(Integer type, Integer week, Integer year) {
        return projectReportRepository.findAllExportVienThong(type, week, year);
    }

    @Override
    public List<ExportChuyenDoiSoDTO> findAllExportChuyenDoiSo(Integer type, Integer week, Integer year) {
        return projectReportRepository.findAllExportChuyenDoiSo(type, week, year);
    }

    @Override
    public List<ExportTrienKhaiDTO> findAllExportTrienKhai(Integer type, Integer week, Integer year) {
        return projectReportRepository.findAllExportTrienKhai(type, week, year);
    }
}
