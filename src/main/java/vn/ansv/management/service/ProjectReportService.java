package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.repository.ProjectReportRepository;
import vn.ansv.management.service.Interface.IProjectReport;

@Service
public class ProjectReportService implements IProjectReport {
    @Autowired
    private ProjectReportRepository projectReportRepository;

    @Override
    public List<ProjectDashboardDTO> findAllDashboardProjectStep1(int enabled, Long type, int week, int year) {
        return projectReportRepository.findAllDashboardProjectStep1(enabled, type, week, year);
    }

    @Override
    public List<ProjectDashboardDTO> findAllDashboardProjectStep2(int enabled, Long type, int week, int year) {
        return projectReportRepository.findAllDashboardProjectStep2(enabled, type, week, year);
    }

    @Override
    public ReportDetailTabPhanLoaiDTO findDetailTabPhanLoai(Long id, int enabled) {
        return projectReportRepository.findDetailTabPhanLoai(id, enabled);
    }
}
