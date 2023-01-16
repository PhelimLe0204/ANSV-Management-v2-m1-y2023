package vn.ansv.management.controller.NonAdmin;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTab1DTO;
import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;
import vn.ansv.management.dto.selectOption.OptionProjectTypeDTO;
import vn.ansv.management.service.ProjectPriorityService;
import vn.ansv.management.service.ProjectReportService;
import vn.ansv.management.service.ProjectTypeService;

@Controller
@RequestMapping(path = "")
// http:localhost:8083
public class HomeController extends BaseController {

    @Autowired // Inject "ProjectReportService" - Dependency Injection
    private ProjectReportService projectReportService;

    @Autowired // Inject "ProjectTypeService" - Dependency Injection
    private ProjectTypeService projectTypeService;

    @Autowired // Inject "ProjectPriorityService" - Dependency Injection
    private ProjectPriorityService projectPriorityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String firstPage() {
        Date trialTime = new Date();
        int current_week = getWeekOfYear(trialTime); // Gọi hàm lấy số tuần => Lấy số tuần hiện tại
        int current_year = Calendar.getInstance().get(Calendar.YEAR); // Get the curent year
        // return "index";

        return "redirect:/dashboard?week=" + current_week + "&year=" + current_year;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView viewDashboard(HttpServletRequest request, HttpSession session) {
        if (request.getParameter("week") == null || request.getParameter("year") == null) {
            return new ModelAndView("redirect:/");
        }
        int week = Integer.parseInt(request.getParameter("week"));
        int year = Integer.parseInt(request.getParameter("year"));
        session.setAttribute("thisWeek", week);
        session.setAttribute("thisYear", year);

        List<ProjectDashboardDTO> telecomProject = projectReportService.findAllDashboardProjectStep1(
                1, 1l, week, year);
        List<ProjectDashboardDTO> digitalTransferProject = projectReportService.findAllDashboardProjectStep1(
                1, 2l, week, year);
        List<ProjectDashboardDTO> deploymentProject = projectReportService.findAllDashboardProjectStep2(
                1, 3l, week, year);

        Init(); // Lấy dữ liệu cơ bản
        _mvShare.addObject("telecomProject", telecomProject); // Du an kinh doanh Vien thong
        _mvShare.addObject("digitalTransferProject", digitalTransferProject); // Du an kinh doanh Chuyen doi so
        _mvShare.addObject("deploymentProject", deploymentProject); // Du an Trien khai
        _mvShare.setViewName("non-admin/dashboard");
        return _mvShare;
    }

    @RequestMapping(value = "/chi-tiet", method = RequestMethod.GET)
    public ModelAndView viewDetail(HttpServletRequest request) {
        if (request.getParameter("id") == null) {
            return new ModelAndView("redirect:/");
        }
        Long project_id = Long.parseLong(request.getParameter("id"));
        ReportDetailTab1DTO projectDetailTab1 = projectReportService.findDetailTabPhanLoai(project_id, 1);
        List<OptionProjectTypeDTO> optionProjectTypeDTO = projectTypeService.findAllOption();
        List<OptionProjectPriorityDTO> optionProjectPriorityDTO = projectPriorityService.findAllOption();
        // List<ProjectStatus> projectStatus =
        // projectStatusService.findAll_detailProject();

        Init(); // Lấy dữ liệu cơ bản
        // _mvShare.addObject("projectDetail", projectDetail);
        _mvShare.addObject("detailTabPhanLoai", projectDetailTab1);

        // _mvShare.addObject("projectType", projectType);
        _mvShare.addObject("optionType", optionProjectTypeDTO);

        // _mvShare.addObject("projectPriority", projectPriority);
        _mvShare.addObject("optionPriority", optionProjectPriorityDTO);

        // _mvShare.addObject("projectStatus", projectStatus);

        _mvShare.setViewName("non-admin/project-detail/main-detail");
        return _mvShare;
    }
}
