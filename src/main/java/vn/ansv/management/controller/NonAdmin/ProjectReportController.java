package vn.ansv.management.controller.NonAdmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.ansv.management.service.ProjectReportService;

@Controller
@RequestMapping(path = "")
public class ProjectReportController extends BaseController {
    @Autowired // Inject "ProjectReportService" - Dependency Injection
    private ProjectReportService projectReportService;

    @RequestMapping(value = "/danh-sach/kd-vien-thong", method = RequestMethod.GET)
    public ModelAndView viewAllReport() {
        // List<ProjectDashboardDTO> telecomProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 1l, week, year);
        // List<ProjectDashboardDTO> digitalTransferProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 2l, week, year);
        // List<ProjectDashboardDTO> deploymentProject =
        // projectReportService.findAllDashboardProjectStep2(
        // 1, 3l, week, year);

        Init(); // Lấy dữ liệu cơ bản
        // _mvShare.addObject("telecomProject", telecomProject); // Du an kinh doanh
        // Vien thong
        // _mvShare.addObject("digitalTransferProject", digitalTransferProject); // Du
        // an kinh doanh Chuyen doi so
        // _mvShare.addObject("deploymentProject", deploymentProject); // Du an Trien
        // khai
        _mvShare.setViewName("non-admin/danh-sach/kd-vien-thong");
        return _mvShare;
    }
}