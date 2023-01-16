package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTab1DTO;

public interface IProjectReport {
    List<ProjectDashboardDTO> findAllDashboardProjectStep1(int enabled, Long type, int week, int year);

    List<ProjectDashboardDTO> findAllDashboardProjectStep2(int enabled, Long type, int week, int year);

    ReportDetailTab1DTO findDetailTabPhanLoai(Long id, int enabled);
}
