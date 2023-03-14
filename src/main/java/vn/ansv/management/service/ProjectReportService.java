package vn.ansv.management.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import vn.ansv.management.repository.ProjectReportRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.service.Interface.IProjectReport;

@Service
public class ProjectReportService implements IProjectReport {
    @Autowired
    private ProjectReportRepository projectReportRepository;

    @Autowired
    private ProjectRepository projectRepository;

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
            dataInsert.setAmId(1L); // Test mã AM
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
}
