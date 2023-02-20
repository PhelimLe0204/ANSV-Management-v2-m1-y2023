package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabQuaTrinhDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabCptgDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabPhanLoaiDTO;
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
}
