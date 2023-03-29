package vn.ansv.management.service.Interface;

import java.util.List;
import java.util.Map;

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

public interface IProjectReport {
    List<ProjectDashboardDTO> findAllDashboardProjectStep1(int enabled, Long type, int week, int year);

    List<ProjectDashboardDTO> findAllDashboardProjectStep2(int enabled, Long type, int week, int year);

    List<ListReport12DTO> findAllReportType12(Long type);

    List<ListReport3DTO> findAllReportType3(Long type);

    ReportDetailTabPhanLoaiDTO findDetailTabPhanLoai(Long id, int enabled);

    ReportDetailTabDuThauDTO findDetailTabDuThau(Long id, int enabled);

    ReportDetailTabCptgDTO findDetailTabChiPhiThoiGian(Long id, int enabled);

    ReportDetailTabQuaTrinhDTO findDetailTabQuaTrinh(Long id, int enabled);

    Boolean updateDetailTabPhanLoai(Long id, UpdateDetailTabPhanLoaiDTO dataUpdate);

    Boolean updateDetailTabDuThau(Long id, UpdateDetailTabDuThauDTO dataUpdate);

    Boolean updateDetailTabCptg(Long id, ReportDetailTabCptgDTO dataUpdate);

    Boolean updateDetailTabQuaTrinh(Long id, ReportDetailTabQuaTrinhDTO dataUpdate);

    Integer deleteReportById(Long first_report_id);

    Integer addNewReport(AddNewReportDTO dataInsert);

    List<ShowDashboardDTO> modalShowDashboard(int enabled, int week, int year, Long status, Long type);

    List<Map<String, String>> processingImportReport(
            MultipartFile excelDataFile, String username, Long type, Integer week, Integer year);

    List<ExportVienThongDTO> findAllExportVienThong(Integer type, Integer week, Integer year);

    List<ExportChuyenDoiSoDTO> findAllExportChuyenDoiSo(Integer type, Integer week, Integer year);

    List<ExportTrienKhaiDTO> findAllExportTrienKhai(Integer type, Integer week, Integer year);

}
