package vn.ansv.management.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabHopDongDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabQuaTrinhDTO;
import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.dto.Detail.SupportCptgDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabCptgDTO;
import vn.ansv.management.dto.Detail.HopDongDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabCptgDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Export.ExportChuyenDoiSoDTO;
import vn.ansv.management.dto.Export.ExportTrienKhaiDTO;
import vn.ansv.management.dto.Export.ExportVienThongDTO;
import vn.ansv.management.dto.Report.AddNewReportDTO;
import vn.ansv.management.dto.Report.ListReport12DTO;
import vn.ansv.management.dto.Report.ListReport3DTO;
import vn.ansv.management.dto.Report.ReportAutoUpdateCurrentWeek;
import vn.ansv.management.dto.Report.ShowDashboardDTO;
import vn.ansv.management.dto.Statistic.DashboardChartDTO;
import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.entity.PaginatedEntity;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.BaoLanhBhRepository;
import vn.ansv.management.repository.BaoLanhThhdRepository;
import vn.ansv.management.repository.BaoLanhTuRepository;
import vn.ansv.management.repository.CustomerRepository;
import vn.ansv.management.repository.HopDongRepository;
import vn.ansv.management.repository.ProjectPriorityRepository;
import vn.ansv.management.repository.ProjectReportRepository;
import vn.ansv.management.repository.ProjectReportSubdataRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.repository.ProjectStatusRepository;
import vn.ansv.management.repository.RoleRepository;
import vn.ansv.management.repository.UserRepository;
import vn.ansv.management.service.Interface.IProjectReport;

@Service
public class ProjectReportService implements IProjectReport {

    @Autowired
    private HopDongRepository hopDongRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectReportRepository projectReportRepository;

    @Autowired
    private ProjectReportSubdataRepository projectReportSubdataRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BaoLanhThhdRepository baoLanhThhdRepository;

    @Autowired
    private BaoLanhTuRepository baoLanhTuRepository;

    @Autowired
    private BaoLanhBhRepository baoLanhBhRepository;

    @Autowired
    private ProjectPriorityRepository projectPriorityRepository;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    final String OLD_FORMAT = "dd/MM/yyyy";
    final String NEW_FORMAT = "dd / MM / yyyy";

    @Override
    public DashboardChartDTO dashboardChart(int week, int year, Long type, int enabled) {
        DashboardChartDTO data = new DashboardChartDTO();
        data.setDangerPriority1(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 1L, 3L));
        data.setDangerPriority2(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 2L, 3L));
        data.setDangerPriority3(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 3L, 3L));
        data.setWarningPriority1(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 1L, 2L));
        data.setWarningPriority2(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 2L, 2L));
        data.setWarningPriority3(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 3L, 2L));
        data.setOngoingPriority1(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 1L, 1L));
        data.setOngoingPriority2(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 2L, 1L));
        data.setOngoingPriority3(
                projectReportRepository.countReportForDashboardChart(week, year, type, enabled, 3L, 1L));

        return data;
    }

    @Override
    public List<ProjectDashboardDTO> findAllDashboardProjectStep1(
            String username, int enabled, Long type, int week, int year) {
        if (username != null) {
            // Truy vấn theo người dùng
            return projectReportRepository.findAllDashboardProjectStep1Limit(username, enabled, type, week, year);
        } else {
            // Truy vấn toàn bộ
            return projectReportRepository.findAllDashboardProjectStep1(enabled, type, week, year);
        }
    }

    @Override
    public List<ProjectDashboardDTO> findAllDashboardProjectStep2(
            String username, int enabled, Long type, int week, int year) {
        if (username != null) {
            // Truy vấn theo người dùng
            return projectReportRepository.findAllDashboardProjectStep2Limit(username, enabled, type, week, year);
        } else {
            // Truy vấn toàn bộ
            return projectReportRepository.findAllDashboardProjectStep2(enabled, type, week, year);
        }
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

    /*
     * =====================================
     * ----- Start: Detail tab hợp đồng ----
     * =====================================
     */
    @Override
    public ReportDetailTabHopDongDTO findDetailTabHopDong(Long id, int enabled) {
        Long projectId = projectRepository.findIdByReport(id);
        ReportDetailTabHopDongDTO result = new ReportDetailTabHopDongDTO();
        HopDongDTO dataHopDong = hopDongRepository.findDetailTabHopDongByReport(id, enabled);
        if (dataHopDong == null) {
            hopDongRepository.addNewHopDong(projectId);
            dataHopDong = hopDongRepository.findDetailTabHopDongByReport(id, enabled);
        }
        SupportBaoLanhHopDongDTO dataBLTHHD = findBaoLanhHopDong(id, enabled, "BLTHHD");
        SupportBaoLanhHopDongDTO dataBLTU = findBaoLanhHopDong(id, enabled, "BLTU");
        SupportBaoLanhHopDongDTO dataBLBH = findBaoLanhHopDong(id, enabled, "BLBH");

        result.setId(id);

        result.setHopDongId(dataHopDong.getId());
        result.setNgayKy(dataHopDong.getNgayKy());
        result.setNgayHieuLuc(dataHopDong.getNgayHieuLuc());
        result.setNgayKetThuc(dataHopDong.getNgayKetThuc());
        result.setChenhLechHieuLuc(dataHopDong.getChenhLech());
        result.setNoteHopDong(dataHopDong.getNote());

        result.setBlThhdId(dataBLTHHD.getId());
        result.setNgayPhatHanhBLTHHD(dataBLTHHD.getNgayPhatHanh());
        result.setNgayHetHanBLTHHD(dataBLTHHD.getNgayHetHan());
        result.setChenhLechBLTHHD(dataBLTHHD.getChenhLech());
        result.setNoteBLTHHD(dataBLTHHD.getNote());

        result.setBlTuId(dataBLTU.getId());
        result.setNgayPhatHanhBLTU(dataBLTU.getNgayPhatHanh());
        result.setNgayHetHanBLTU(dataBLTU.getNgayHetHan());
        result.setChenhLechBLTU(dataBLTU.getChenhLech());
        result.setNoteBLTU(dataBLTU.getNote());

        result.setBlBhId(dataBLBH.getId());
        result.setNgayPhatHanhBLBH(dataBLBH.getNgayPhatHanh());
        result.setNgayHetHanBLBH(dataBLBH.getNgayHetHan());
        result.setChenhLechBLBH(dataBLBH.getChenhLech());
        result.setNoteBLBH(dataBLBH.getNote());

        return result;
    }

    public SupportBaoLanhHopDongDTO findBaoLanhHopDong(Long id, int enabled, String target) {
        if (target.equals("BLTHHD")) {
            SupportBaoLanhHopDongDTO result = baoLanhThhdRepository.findDetailTabHopDongByReport(id, enabled);
            if (result == null) {
                Long project_id = projectRepository.findIdByReport(id);
                baoLanhThhdRepository.addNewBLTHHD(project_id);
                result = baoLanhThhdRepository.findDetailTabHopDongByReport(id, enabled);
            }
            return result;
        }

        if (target.equals("BLTU")) {
            SupportBaoLanhHopDongDTO result = baoLanhTuRepository.findDetailTabHopDongByReport(id, enabled);
            if (result == null) {
                Long project_id = projectRepository.findIdByReport(id);
                baoLanhTuRepository.addNewBLTU(project_id);
                result = baoLanhTuRepository.findDetailTabHopDongByReport(id, enabled);
            }
            return result;
        }

        if (target.equals("BLBH")) {
            SupportBaoLanhHopDongDTO result = baoLanhBhRepository.findDetailTabHopDongByReport(id, enabled);
            if (result == null) {
                Long project_id = projectRepository.findIdByReport(id);
                baoLanhBhRepository.addNewBLBH(project_id);
                result = baoLanhBhRepository.findDetailTabHopDongByReport(id, enabled);
            }
            return result;
        }

        return null;
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
    public Boolean updateDetailTabCptg(Long id, UpdateDetailTabCptgDTO dataUpdate) {
        try {
            // Update project_report
            projectReportRepository.updateDetailTabCptg(id,
                    dataUpdate.getSoTienGiaoHang(), dataUpdate.getHopDongGiaoHang(), dataUpdate.getMucTieuGiaoHang(),
                    dataUpdate.getThucTeGiaoHang(), dataUpdate.getNoteGiaoHang(), dataUpdate.getSoTienDac(),
                    dataUpdate.getHopDongDac(), dataUpdate.getMucTieuDac(), dataUpdate.getThucTeDac(),
                    dataUpdate.getNoteDac(), dataUpdate.getSoTienPac(), dataUpdate.getHopDongPac(),
                    dataUpdate.getMucTieuPac(), dataUpdate.getThucTePac(), dataUpdate.getNotePac(),
                    dataUpdate.getSoTienFac(), dataUpdate.getHopDongFac(), dataUpdate.getMucTieuFac(),
                    dataUpdate.getThucTeFac(), dataUpdate.getNoteFac(), dataUpdate.getTongGiaTriThucTe(),
                    dataUpdate.getNoteTongGiaTri(), dataUpdate.getSoTienTamUng(), dataUpdate.getKeHoachTamUng(),
                    dataUpdate.getNoteTamUng());

            // Update project_report_subdata
            if (projectReportSubdataRepository.countReportSubdataByReportId(id) > 0) {
                // Tồn tại subdata => update
                projectReportSubdataRepository.updateDetailTabCptgSubdata(id,
                        dataUpdate.getSoTienDac2(), dataUpdate.getHopDongDac2(), dataUpdate.getMucTieuDac2(),
                        dataUpdate.getThucTeDac2(), dataUpdate.getNoteDac2(),
                        dataUpdate.getSoTienDac3(), dataUpdate.getHopDongDac3(), dataUpdate.getMucTieuDac3(),
                        dataUpdate.getThucTeDac3(), dataUpdate.getNoteDac3(),
                        dataUpdate.getSoTienDac4(), dataUpdate.getHopDongDac4(), dataUpdate.getMucTieuDac4(),
                        dataUpdate.getThucTeDac4(), dataUpdate.getNoteDac4(),
                        dataUpdate.getSoTienDac5(), dataUpdate.getHopDongDac5(), dataUpdate.getMucTieuDac5(),
                        dataUpdate.getThucTeDac5(), dataUpdate.getNoteDac5(),

                        dataUpdate.getSoTienPac2(), dataUpdate.getHopDongPac2(), dataUpdate.getMucTieuPac2(),
                        dataUpdate.getThucTePac2(), dataUpdate.getNotePac2(),
                        dataUpdate.getSoTienPac3(), dataUpdate.getHopDongPac3(), dataUpdate.getMucTieuPac3(),
                        dataUpdate.getThucTePac3(), dataUpdate.getNotePac3(),
                        dataUpdate.getSoTienPac4(), dataUpdate.getHopDongPac4(), dataUpdate.getMucTieuPac4(),
                        dataUpdate.getThucTePac4(), dataUpdate.getNotePac4(),
                        dataUpdate.getSoTienPac5(), dataUpdate.getHopDongPac5(), dataUpdate.getMucTieuPac5(),
                        dataUpdate.getThucTePac5(), dataUpdate.getNotePac5(),

                        dataUpdate.getSoTienFac2(), dataUpdate.getHopDongFac2(), dataUpdate.getMucTieuFac2(),
                        dataUpdate.getThucTeFac2(), dataUpdate.getNoteFac2(),
                        dataUpdate.getSoTienFac3(), dataUpdate.getHopDongFac3(), dataUpdate.getMucTieuFac3(),
                        dataUpdate.getThucTeFac3(), dataUpdate.getNoteFac3(),
                        dataUpdate.getSoTienFac4(), dataUpdate.getHopDongFac4(), dataUpdate.getMucTieuFac4(),
                        dataUpdate.getThucTeFac4(), dataUpdate.getNoteFac4(),
                        dataUpdate.getSoTienFac5(), dataUpdate.getHopDongFac5(), dataUpdate.getMucTieuFac5(),
                        dataUpdate.getThucTeFac5(), dataUpdate.getNoteFac5());
            } else {
                // Không tồn tại subdata => insert
                projectReportSubdataRepository.addNewReportSubdata(id,
                        dataUpdate.getSoTienDac2(), dataUpdate.getHopDongDac2(), dataUpdate.getMucTieuDac2(),
                        dataUpdate.getThucTeDac2(), dataUpdate.getNoteDac2(),
                        dataUpdate.getSoTienDac3(), dataUpdate.getHopDongDac3(), dataUpdate.getMucTieuDac3(),
                        dataUpdate.getThucTeDac3(), dataUpdate.getNoteDac3(),
                        dataUpdate.getSoTienDac4(), dataUpdate.getHopDongDac4(), dataUpdate.getMucTieuDac4(),
                        dataUpdate.getThucTeDac4(), dataUpdate.getNoteDac4(),
                        dataUpdate.getSoTienDac5(), dataUpdate.getHopDongDac5(), dataUpdate.getMucTieuDac5(),
                        dataUpdate.getThucTeDac5(), dataUpdate.getNoteDac5(),
                        dataUpdate.getSoTienPac2(), dataUpdate.getHopDongPac2(), dataUpdate.getMucTieuPac2(),
                        dataUpdate.getThucTePac2(), dataUpdate.getNotePac2(),
                        dataUpdate.getSoTienPac3(), dataUpdate.getHopDongPac3(), dataUpdate.getMucTieuPac3(),
                        dataUpdate.getThucTePac3(), dataUpdate.getNotePac3(),
                        dataUpdate.getSoTienPac4(), dataUpdate.getHopDongPac4(), dataUpdate.getMucTieuPac4(),
                        dataUpdate.getThucTePac4(), dataUpdate.getNotePac4(),
                        dataUpdate.getSoTienPac5(), dataUpdate.getHopDongPac5(), dataUpdate.getMucTieuPac5(),
                        dataUpdate.getThucTePac5(), dataUpdate.getNotePac5(),
                        dataUpdate.getSoTienFac2(), dataUpdate.getHopDongFac2(), dataUpdate.getMucTieuFac2(),
                        dataUpdate.getThucTeFac2(), dataUpdate.getNoteFac2(),
                        dataUpdate.getSoTienFac3(), dataUpdate.getHopDongFac3(), dataUpdate.getMucTieuFac3(),
                        dataUpdate.getThucTeFac3(), dataUpdate.getNoteFac3(),
                        dataUpdate.getSoTienFac4(), dataUpdate.getHopDongFac4(), dataUpdate.getMucTieuFac4(),
                        dataUpdate.getThucTeFac4(), dataUpdate.getNoteFac4(),
                        dataUpdate.getSoTienFac5(), dataUpdate.getHopDongFac5(), dataUpdate.getMucTieuFac5(),
                        dataUpdate.getThucTeFac5(), dataUpdate.getNoteFac5());
            }
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
    public ResponseObject findAllReportType12(
            int week, int year, String username, Long type, int currentPage, int pageSize) {
        try {
            PaginatedEntity paging = new PaginatedEntity();
            ResponseObject result = new ResponseObject();
            int totalReport = projectReportRepository.countAllByTypeWeekYear(week, year, type);
            if (totalReport == 0) {
                return null;
            }
            int totalPage = totalReport / pageSize;
            if (totalReport % pageSize > 0) {
                totalPage = (totalReport / pageSize) + 1;
            }
            paging.setPageSize(pageSize);
            paging.setPageNumber(currentPage);
            paging.setTotalPage(totalPage);
            if (totalPage - currentPage >= 2) {
                if (currentPage - 2 < 1) {
                    List<Integer> pageNumbers = IntStream.rangeClosed(1,
                            (totalPage - currentPage >= 5) ? 5 : totalPage).boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                } else {
                    List<Integer> pageNumbers = IntStream.rangeClosed(currentPage - 2, currentPage + 2)
                            .boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                }
            } else {
                if (totalPage >= 5) {
                    List<Integer> pageNumbers = IntStream.rangeClosed(totalPage - 4, totalPage)
                            .boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                } else {
                    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                            .boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                }
            }
            int startPosition = pageSize * (currentPage - 1);

            if (username == null) {

                List<ListReport12DTO> data = projectReportRepository.findAllReportType12WeekYear(
                        type, week, year, startPosition, pageSize);
                if (data.isEmpty()) {
                    return null;
                }
                result.setData(data);
                result.setPaging(paging);
                return result;

            } else {
                List<ListReport12DTO> data = projectReportRepository.findAllReportType12Limit(
                        username, type, week, year, startPosition, pageSize);
                result.setData(data);
                result.setPaging(paging);
                return result;
            }
        } catch (Exception e) {
            System.out.println("----- ProjectReportService.findAllReportType12().e ----- " + e);
        }
        return null;

    }

    @Override
    public ResponseObject findListReportType3(
            int week, int year, String username, Long type, int currentPage, int pageSize) {
        try {
            PaginatedEntity paging = new PaginatedEntity();
            ResponseObject result = new ResponseObject();
            int totalReport = projectReportRepository.countAllByTypeWeekYear(week, year, 3L);
            if (totalReport == 0) {
                return null;
            }
            int totalPage = totalReport / pageSize;
            if (totalReport % pageSize > 0) {
                totalPage = (totalReport / pageSize) + 1;
            }
            paging.setPageSize(pageSize);
            paging.setPageNumber(currentPage);
            paging.setTotalPage(totalPage);
            if (totalPage - currentPage >= 2) {
                if (currentPage - 2 < 1) {
                    List<Integer> pageNumbers = IntStream.rangeClosed(1,
                            (totalPage - currentPage >= 4) ? 5 : totalPage).boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                } else {
                    List<Integer> pageNumbers = IntStream.rangeClosed(currentPage - 2, currentPage + 2)
                            .boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                }
            } else {
                if (totalPage >= 5) {
                    List<Integer> pageNumbers = IntStream.rangeClosed(totalPage - 4, totalPage)
                            .boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                } else {
                    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                            .boxed().collect(Collectors.toList());
                    paging.setListPageNumbers(pageNumbers);
                }
            }
            int startPosition = pageSize * (currentPage - 1);

            if (username == null) {
                List<ListReport3DTO> data = projectReportRepository.findListReportType3WeekYear(
                        type, week, year, startPosition, pageSize);
                if (data.isEmpty()) {
                    return null;
                }
                result.setData(data);
                result.setPaging(paging);
                return result;
            } else {
                List<ListReport3DTO> data = projectReportRepository.findListReportType3WeekYearByUser(
                        username, type, week, year, startPosition, pageSize);
                result.setData(data);
                result.setPaging(paging);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----- ProjectReportService.findListReportType3() line 543: " + e);
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
            System.out.println("----- ProjectReportService.deleteReportById().e ----- " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer addNewReport(AddNewReportDTO dataInsert) {
        try {
            String uid = RandomStringUtils.randomAlphanumeric(20);
            dataInsert.setUid(uid);
            if (dataInsert.getAmId() != null) {
                dataInsert.setAmId(1L); // Set AM
            }

            // Get username và Set người tạo (AM / PM / AM's manager / PM's manager)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                // Define user's id
                UserDefineDTO user_define = userService.userDefine(currentUserName);
                Long user_id = user_define.getId();
                String user_role = user_define.getUserRole();
                // System.out.println("--------------------------- user_role: " + user_role);
                if (user_role.contains("Manager_AM")) {
                    dataInsert.setAmManagerId(user_id); // Set AM's manager
                } else if (user_role.contains("Manager_PM")) {
                    dataInsert.setPmManagerId(user_id); // Set PM's manager
                } else if (user_role.contains("Main_AM")) {
                    dataInsert.setAmId(user_id); // Set AM
                } else if (user_role.contains("Main_PM")) {
                    dataInsert.setPmId(user_id); // Set PM
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
                    (dataInsert.getHopDongGiaoHang() != null && dataInsert.getHopDongGiaoHang().length() != 0)
                            ? dataInsert.getHopDongGiaoHang()
                            : null,
                    (dataInsert.getMucTieuGiaoHang() != null && dataInsert.getMucTieuGiaoHang().length() != 0)
                            ? dataInsert.getMucTieuGiaoHang()
                            : null,
                    (dataInsert.getThucTeGiaoHang() != null && dataInsert.getThucTeGiaoHang().length() != 0)
                            ? dataInsert.getThucTeGiaoHang()
                            : null,
                    dataInsert.getNoteGiaoHang(), dataInsert.getSoTienDac(),
                    (dataInsert.getHopDongDac() != null && dataInsert.getHopDongDac().length() != 0)
                            ? dataInsert.getHopDongDac()
                            : null,
                    (dataInsert.getMucTieuDac() != null && dataInsert.getMucTieuDac().length() != 0)
                            ? dataInsert.getMucTieuDac()
                            : null,
                    (dataInsert.getThucTeDac() != null && dataInsert.getThucTeDac().length() != 0)
                            ? dataInsert.getThucTeDac()
                            : null,
                    dataInsert.getNoteDac(), dataInsert.getSoTienPac(),
                    (dataInsert.getHopDongPac() != null && dataInsert.getHopDongPac().length() != 0)
                            ? dataInsert.getHopDongPac()
                            : null,
                    (dataInsert.getMucTieuPac() != null && dataInsert.getMucTieuPac().length() != 0)
                            ? dataInsert.getMucTieuPac()
                            : null,
                    (dataInsert.getThucTePac() != null && dataInsert.getThucTePac().length() != 0)
                            ? dataInsert.getThucTePac()
                            : null,
                    dataInsert.getNotePac(), dataInsert.getSoTienFac(),
                    (dataInsert.getHopDongFac() != null && dataInsert.getHopDongFac().length() != 0)
                            ? dataInsert.getHopDongFac()
                            : null,
                    (dataInsert.getMucTieuFac() != null && dataInsert.getMucTieuFac().length() != 0)
                            ? dataInsert.getMucTieuFac()
                            : null,
                    (dataInsert.getThucTeFac() != null && dataInsert.getThucTeFac().length() != 0)
                            ? dataInsert.getThucTeFac()
                            : null,
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

    public Integer addNewReportAuto(AddNewReportDTO dataInsert) {
        try {
            int count_data = projectReportRepository.checkByJobNameWeekYearType(
                    dataInsert.getJobName(), dataInsert.getWeek(), dataInsert.getYear(), dataInsert.getProjectTypeId());
            if (count_data == 0) {
                String uid = RandomStringUtils.randomAlphanumeric(20);
                dataInsert.setUid(uid);
                // Add new report
                projectReportRepository.addNewReport(dataInsert.getUid(), dataInsert.getAmId(),
                        dataInsert.getAmManagerId(),
                        dataInsert.getPmId(), dataInsert.getPmManagerId(), dataInsert.getCreatedBy(),
                        dataInsert.getProjectId(), dataInsert.getProjectTypeId(), dataInsert.getProjectPriorityId(),
                        dataInsert.getProjectStatusId(), dataInsert.getWeek(), dataInsert.getYear(),
                        dataInsert.getMaHopDong(), dataInsert.getMaKeToan(), dataInsert.getCurrencyUnitId(),
                        dataInsert.getJobName(), dataInsert.getDescription(), 1, dataInsert.getMucDoKhaThi(),
                        dataInsert.getTongMucDauTuDuKien(), dataInsert.getHinhThucDauTu(),
                        dataInsert.getPhamViCungCap(),
                        dataInsert.getPhanTichSwoot(), dataInsert.getSoTienGiaoHang(),
                        (dataInsert.getHopDongGiaoHang() != null && dataInsert.getHopDongGiaoHang().length() != 0)
                                ? dataInsert.getHopDongGiaoHang()
                                : null,
                        (dataInsert.getMucTieuGiaoHang() != null && dataInsert.getMucTieuGiaoHang().length() != 0)
                                ? dataInsert.getMucTieuGiaoHang()
                                : null,
                        (dataInsert.getThucTeGiaoHang() != null && dataInsert.getThucTeGiaoHang().length() != 0)
                                ? dataInsert.getThucTeGiaoHang()
                                : null,
                        dataInsert.getNoteGiaoHang(), dataInsert.getSoTienDac(),
                        (dataInsert.getHopDongDac() != null && dataInsert.getHopDongDac().length() != 0)
                                ? dataInsert.getHopDongDac()
                                : null,
                        (dataInsert.getMucTieuDac() != null && dataInsert.getMucTieuDac().length() != 0)
                                ? dataInsert.getMucTieuDac()
                                : null,
                        (dataInsert.getThucTeDac() != null && dataInsert.getThucTeDac().length() != 0)
                                ? dataInsert.getThucTeDac()
                                : null,
                        dataInsert.getNoteDac(), dataInsert.getSoTienPac(),
                        (dataInsert.getHopDongPac() != null && dataInsert.getHopDongPac().length() != 0)
                                ? dataInsert.getHopDongPac()
                                : null,
                        (dataInsert.getMucTieuPac() != null && dataInsert.getMucTieuPac().length() != 0)
                                ? dataInsert.getMucTieuPac()
                                : null,
                        (dataInsert.getThucTePac() != null && dataInsert.getThucTePac().length() != 0)
                                ? dataInsert.getThucTePac()
                                : null,
                        dataInsert.getNotePac(), dataInsert.getSoTienFac(),
                        (dataInsert.getHopDongFac() != null && dataInsert.getHopDongFac().length() != 0)
                                ? dataInsert.getHopDongFac()
                                : null,
                        (dataInsert.getMucTieuFac() != null && dataInsert.getMucTieuFac().length() != 0)
                                ? dataInsert.getMucTieuFac()
                                : null,
                        (dataInsert.getThucTeFac() != null && dataInsert.getThucTeFac().length() != 0)
                                ? dataInsert.getThucTeFac()
                                : null,
                        dataInsert.getNoteFac(), dataInsert.getTongGiaTriThucTe(), dataInsert.getNoteTongGiaTri(),
                        dataInsert.getSoTienTamUng(), dataInsert.getKeHoachTamUng(), dataInsert.getNoteTamUng(),
                        dataInsert.getTienDoChung(), dataInsert.getGeneralIssue(), dataInsert.getSolution(),
                        dataInsert.getKeHoachTuanNay(), dataInsert.getKeHoachTuanSau(), dataInsert.getKetQuaTuanTruoc(),
                        dataInsert.getKetQuaTuanNay());
                return 1;
            }
            return 2;
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportService.addNewReportAuto(): " + e.getMessage());
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
                            .checkIssetByFullnameWithRoleName(row.getCell(12).getStringCellValue(), "Main_AM");
                    if (checkPic == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "M" + (i + 1));
                        errors.put("error", "PIC không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 6. Phó ban
                    if (row.getCell(13).getStringCellValue() != null
                            && row.getCell(13).getStringCellValue().length() > 0) {
                        int checkPhoBan = userRepository
                                .checkIssetByFullnameWithRoleName(row.getCell(13).getStringCellValue(), "Manager_AM");
                        if (checkPhoBan == 0) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "N" + (i + 1));
                            errors.put("error", "Phó ban không tồn tại");
                            dataError.add(errors); // Đẩy lỗi vào list
                        }
                    }

                    // dataError.add((i - 1), errors); // Đẩy lỗi vào list
                } else {
                    System.out.println("--- checkExcelDataType1AndType2() - Line 839: Dòng " + (i + 1) + " rỗng");
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
                            row.getCell(12).getStringCellValue(), "Main_AM");
                    Long amManagerId = 42L;
                    if (row.getCell(13).getStringCellValue() != null
                            && row.getCell(13).getStringCellValue().length() > 0) {
                        amManagerId = userRepository.findIdByFullnameWithRoleName(
                                row.getCell(13).getStringCellValue(), "Manager_AM");
                    }
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
                    System.out.println("--- checkExcelDataType1AndType2() - Line 977: Dòng " + (i + 1) + " rỗng");
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
        String hopDongDAC = null, mucTieuDAC = null, thucTeDAC = null, hopDongPAC = null, mucTieuPAC = null,
                thucTePAC = null, hopDongFAC = null, mucTieuFAC = null, thucTeFAC = null;
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

                    // 5. DAC hợp đồng (7)
                    if (row.getCell(7).getRawValue() != null && row.getCell(7).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(7).getStringCellValue());
                            if (!row.getCell(7).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            hopDongDAC = sdf.format(date);
                            // System.out.println("------------------------- hopDongDAC: " + hopDongDAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "H" + (i + 1));
                            errors.put("error", "DAC hợp đồng không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 6. Mục tiêu DAC (8)
                    if (row.getCell(8).getRawValue() != null && row.getCell(8).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(8).getStringCellValue());
                            if (!row.getCell(8).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            mucTieuDAC = sdf.format(date);
                            // System.out.println("------------------------- mucTieuDAC: " + mucTieuDAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "I" + (i + 1));
                            errors.put("error", "Mục tiêu DAC không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 7. DAC thực tế (9)
                    if (row.getCell(9).getRawValue() != null && row.getCell(9).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(9).getStringCellValue());
                            if (!row.getCell(9).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            thucTeDAC = sdf.format(date);
                            // System.out.println("------------------------- thucTeDAC: " + thucTeDAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "J" + (i + 1));
                            errors.put("error", "DAC thực tế không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 8. PAC hợp đồng (12)
                    if (row.getCell(12).getRawValue() != null && row.getCell(12).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(12).getStringCellValue());
                            if (!row.getCell(12).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            hopDongPAC = sdf.format(date);
                            // System.out.println("------------------------- hopDongPAC: " + hopDongPAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "M" + (i + 1));
                            errors.put("error", "PAC hợp đồng không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 9. Mục tiêu PAC (13)
                    if (row.getCell(13).getRawValue() != null && row.getCell(13).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(13).getStringCellValue());
                            if (!row.getCell(13).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            mucTieuPAC = sdf.format(date);
                            // System.out.println("------------------------- mucTieuPAC: " + mucTieuPAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "N" + (i + 1));
                            errors.put("error", "Mục tiêu PAC không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 10. PAC thực tế (14)
                    if (row.getCell(14).getRawValue() != null && row.getCell(14).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(14).getStringCellValue());
                            if (!row.getCell(14).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            thucTePAC = sdf.format(date);
                            // System.out.println("------------------------- thucTePAC: " + thucTePAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "O" + (i + 1));
                            errors.put("error", "PAC thực tế không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 11. FAC hợp đồng (17)
                    if (row.getCell(17).getRawValue() != null && row.getCell(17).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(17).getStringCellValue());
                            if (!row.getCell(17).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            hopDongFAC = sdf.format(date);
                            // System.out.println("------------------------- hopDongFAC: " + hopDongFAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "R" + (i + 1));
                            errors.put("error", "FAC hợp đồng không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 12. Mục tiêu FAC (18)
                    if (row.getCell(18).getRawValue() != null && row.getCell(18).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(18).getStringCellValue());
                            if (!row.getCell(18).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            mucTieuFAC = sdf.format(date);
                            // System.out.println("------------------------- mucTieuFAC: " + mucTieuFAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "N" + (i + 1));
                            errors.put("error", "Mục tiêu FAC không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 13. FAC thực tế (19)
                    if (row.getCell(19).getRawValue() != null && row.getCell(19).getStringCellValue().length() > 0) {
                        Date date = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            date = sdf.parse(row.getCell(19).getStringCellValue());
                            if (!row.getCell(19).getStringCellValue().equals(sdf.format(date))) {
                                date = null;
                            }
                            sdf.applyPattern(NEW_FORMAT);
                            thucTeFAC = sdf.format(date);
                            // System.out.println("------------------------- thucTeFAC: " + thucTeFAC);
                        } catch (ParseException parseException) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "O" + (i + 1));
                            errors.put("error", "FAC thực tế không đúng định dạng dd/MM/yyyy");
                            dataError.add(errors); // Đẩy lỗi vào list
                            System.out.println("----- ParseException ----- ProjectReportService.checkExcelDataType3(): "
                                    + parseException.getMessage());
                        }
                    }

                    // 14. Priority
                    String excelPriority = row.getCell(24).getStringCellValue();
                    if (!excelPriority.equals("First") && !excelPriority.equals("Second")
                            && !excelPriority.equals("Third")) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "Y" + (i + 1));
                        errors.put("error", "Priority không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 15. Status
                    String excelStatus = row.getCell(25).getStringCellValue();
                    if (!excelStatus.equals("High") && !excelStatus.equals("Medium")
                            && !excelStatus.equals("Low")) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "Z" + (i + 1));
                        errors.put("error", "Status không xác định");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 16. AM
                    int checkAm = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(26).getStringCellValue(), "Main_AM");
                    if (checkAm == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "AA" + (i + 1));
                        errors.put("error", "AM không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 17. PM
                    int checkPm = userRepository
                            .checkIssetByFullnameWithRoleName(row.getCell(27).getStringCellValue(), "Main_PM");
                    if (checkPm == 0) {
                        Map<String, String> errors = new HashMap<String, String>();
                        errors.put("position", "AB" + (i + 1));
                        errors.put("error", "PM không tồn tại");
                        dataError.add(errors); // Đẩy lỗi vào list
                    }

                    // 18. PM's Manager
                    if (row.getCell(28).getStringCellValue() != null
                            && row.getCell(28).getStringCellValue().length() > 0) {
                        int checkPmManager = userRepository
                                .checkIssetByFullnameWithRoleName(row.getCell(28).getStringCellValue(), "Manager_PM");

                        if (checkPmManager == 0) {
                            Map<String, String> errors = new HashMap<String, String>();
                            errors.put("position", "AC" + (i + 1));
                            errors.put("error", "Phó ban không tồn tại");
                            dataError.add(errors); // Đẩy lỗi vào list
                        }
                    }
                } else {
                    System.out.println("--- checkExcelDataType3() - Line 1297: Dòng " + (i + 1) + " rỗng");
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
                    // System.out.println("--- reportId: " + reportId);
                    // System.out.println("--- uid: " + uid);
                    // System.out.println("--- customerId: " + customerId);
                    // System.out.println("--- projectId: " + projectId);
                    String tongGiaTriThucTe = null;
                    if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                        tongGiaTriThucTe = currencyFormat(row.getCell(5).getNumericCellValue());
                        // System.out.println("--- tongGiaTriThucTe: " + tongGiaTriThucTe);
                    } else {
                        if (row.getCell(5).getCellType() == CellType.FORMULA
                                && row.getCell(5).getCachedFormulaResultType() == CellType.NUMERIC) {
                            tongGiaTriThucTe = currencyFormat(row.getCell(5).getNumericCellValue());
                        } else {
                            tongGiaTriThucTe = row.getCell(5).getStringCellValue();
                        }
                    }
                    String soTienDAC = null;
                    if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                        soTienDAC = currencyFormat(row.getCell(6).getNumericCellValue());
                        // System.out.println("--- soTienDAC: " + soTienDAC);
                    } else {
                        if (row.getCell(6).getCellType() == CellType.FORMULA
                                && row.getCell(6).getCachedFormulaResultType() == CellType.NUMERIC) {
                            soTienDAC = currencyFormat(row.getCell(6).getNumericCellValue());
                        } else {
                            soTienDAC = row.getCell(6).getStringCellValue();
                        }
                    }
                    String soTienPAC = null;
                    if (row.getCell(11).getCellType() == CellType.NUMERIC) {
                        soTienPAC = currencyFormat(row.getCell(11).getNumericCellValue());
                        // System.out.println("--- soTienPAC: " + soTienPAC);
                    } else {
                        if (row.getCell(11).getCellType() == CellType.FORMULA
                                && row.getCell(11).getCachedFormulaResultType() == CellType.NUMERIC) {
                            soTienPAC = currencyFormat(row.getCell(11).getNumericCellValue());
                        } else {
                            soTienPAC = row.getCell(11).getStringCellValue();
                        }
                    }
                    String soTienFAC = null;
                    if (row.getCell(16).getCellType() == CellType.NUMERIC) {
                        soTienFAC = currencyFormat(row.getCell(16).getNumericCellValue());
                        // System.out.println("--- soTienFAC: " + soTienFAC);
                    } else {
                        if (row.getCell(16).getCellType() == CellType.FORMULA
                                && row.getCell(16).getCachedFormulaResultType() == CellType.NUMERIC) {
                            soTienFAC = currencyFormat(row.getCell(16).getNumericCellValue());
                        } else {
                            soTienFAC = row.getCell(16).getStringCellValue();
                        }
                    }
                    Long priorityId = projectPriorityRepository
                            .findIdByPriorityName(row.getCell(24).getStringCellValue());
                    Long statusId = projectStatusRepository
                            .findIdByStatusName(row.getCell(25).getStringCellValue());
                    Long amId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(26).getStringCellValue(), "Main_AM");
                    Long pmId = userRepository.findIdByFullnameWithRoleName(
                            row.getCell(27).getStringCellValue(), "Main_PM");
                    Long pmManagerId = 43L;
                    if (row.getCell(28).getStringCellValue() != null
                            && row.getCell(28).getStringCellValue().length() > 0) {
                        pmManagerId = userRepository.findIdByFullnameWithRoleName(
                                row.getCell(28).getStringCellValue(), "Manager_PM");
                    }

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
                                soTienDAC, hopDongDAC, mucTieuDAC, thucTeDAC, null,
                                soTienPAC, hopDongPAC, mucTieuPAC, thucTePAC, null,
                                soTienFAC, hopDongFAC, mucTieuFAC, thucTeFAC, null, tongGiaTriThucTe,
                                null, null, null, null,
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
                                soTienDAC, hopDongDAC, mucTieuDAC, thucTeDAC,
                                soTienPAC, hopDongPAC, mucTieuPAC, thucTePAC,
                                soTienFAC, hopDongFAC, mucTieuFAC, thucTeFAC,
                                tongGiaTriThucTe, null, null,
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
                    System.out.println("--- checkExcelDataType3() - Line 1477: Dòng " + (i + 1) + " rỗng");
                    break;
                }
            }

            workbook.close();
            return dataError;
        } catch (Exception e) {
            System.out
                    .println("----- Error ----- ProjectReportService.checkExcelData3() - Line 1381: " + e.getMessage());
            e.printStackTrace();
            return dataError;
        }
    }

    public String currencyFormat(Double dataPrice) {
        System.out.println("---------------- dataPrice: " + dataPrice);
        String price_data = dataPrice + "";
        int indexOfE = price_data.indexOf("E");
        Double price_data_correct = dataPrice * Math.pow(10, Double.parseDouble(price_data.substring(indexOfE + 1)));
        String price_big_decimal = new BigDecimal(price_data_correct).toPlainString();
        // System.out.println(price_big_decimal.substring(0, (price_big_decimal.length()
        // / 2 + 1))
        // + " --- " + price_big_decimal);
        double currencyAmount = Double
                .parseDouble(price_big_decimal.substring(0, (price_big_decimal.length() / 2 + 1)));
        Locale viet = new Locale("vi", "VN"); // Create a new Locale
        // Create a Currency instance for the Locale
        // Currency dollars = Currency.getInstance(viet);
        // Create a formatter given the Locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(viet);
        // Format the Number into a Currency String
        String currencyAmountFormat = dollarFormat.format(currencyAmount);
        // System.out.println(dollars.getDisplayName() + ": "
        // + currencyAmountFormat.substring(0, currencyAmountFormat.length() - 2));
        return currencyAmountFormat.substring(0, currencyAmountFormat.length() - 2);
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
    public List<ExportTrienKhaiDTO> findAllExportTrienKhai(
            String userRole, Long userId, Integer type, Integer week, Integer year) {
        try {
            // Get allow_export
            String allow_export = roleRepository.findAllowExportByRoleName(userRole);

            if (allow_export.contains("NONE")) {
                return null;
            }

            List<ExportTrienKhaiDTO> result = null;

            if (allow_export.contains("ALL_REPORT") || allow_export.contains("ALL_3")) {
                // Cho phép xuất toàn bộ báo cáo Triển khai
                result = projectReportRepository.findAllExportTrienKhai(type, week, year);
                result.forEach((item) -> {
                    if (item.getKetQuaTuanTruoc() != null) {
                        item.setKetQuaTuanTruoc(item.getKetQuaTuanTruoc().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getKeHoachTuanNay() != null) {
                        item.setKeHoachTuanNay(item.getKeHoachTuanNay().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getKetQuaTuanNay() != null) {
                        item.setKetQuaTuanNay(item.getKetQuaTuanNay().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getGeneralIssue() != null) {
                        item.setGeneralIssue(item.getGeneralIssue().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getSolution() != null) {
                        item.setSolution(item.getSolution().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getKeHoachTuanSau() != null) {
                        item.setKeHoachTuanSau(item.getKeHoachTuanSau().replaceAll("<[^>]*>", ""));
                    }
                });
            }

            if (allow_export.contains("OWN_3")) {
                // Cho phép xuất báo cáo Triển khai của người dùng
                result = projectReportRepository.findExportTrienKhaiByPM(type, week, year, userId);
                result.forEach((item) -> {
                    if (item.getKetQuaTuanTruoc() != null) {
                        item.setKetQuaTuanTruoc(item.getKetQuaTuanTruoc().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getKeHoachTuanNay() != null) {
                        item.setKeHoachTuanNay(item.getKeHoachTuanNay().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getKetQuaTuanNay() != null) {
                        item.setKetQuaTuanNay(item.getKetQuaTuanNay().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getGeneralIssue() != null) {
                        item.setGeneralIssue(item.getGeneralIssue().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getSolution() != null) {
                        item.setSolution(item.getSolution().replaceAll("<[^>]*>", ""));
                    }
                    if (item.getKeHoachTuanSau() != null) {
                        item.setKeHoachTuanSau(item.getKeHoachTuanSau().replaceAll("<[^>]*>", ""));
                    }
                });
            }

            return result;
        } catch (Exception e) {
            System.out.println("----- ProjectReportService line 1649: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SupportCptgDTO findDetailMoreTabCptg(Long id, String target) {
        switch (target) {
            case "dac":
                return projectReportRepository.findDetailMoreDAC(id);
            case "pac":
                return projectReportRepository.findDetailMorePAC(id);
            case "fac":
                return projectReportRepository.findDetailMoreFAC(id);
            default:
                return null;
        }
    }

    @Override
    public Integer autoAddNewReport(int week, int year, Long id) {
        try {
            if (week > 1) {
                ReportAutoUpdateCurrentWeek data = projectReportRepository.findAllReportDetailById(id);
                AddNewReportDTO data_insert = new AddNewReportDTO();
                ModelMapper modelMapper = new ModelMapper();
                data_insert = modelMapper.map(data, AddNewReportDTO.class);
                data_insert.setWeek(week);
                data_insert.setYear(year);
                int insert_result = addNewReportAuto(data_insert);
                return insert_result;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
