package vn.ansv.management.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.ansv.management.dto.Report.ListReport12DTO;
import vn.ansv.management.dto.Report.ListReport3DTO;
import vn.ansv.management.dto.Report.AddNewReportDTO;
import vn.ansv.management.service.ProjectReportService;

@Controller
@RequestMapping(path = "")
public class ProjectReportController extends BaseController {
    @Autowired // Inject "ProjectReportService" - Dependency Injection
    private ProjectReportService projectReportService;

    @RequestMapping(value = "/danh-sach/kd-vien-thong", method = RequestMethod.GET)
    public ModelAndView viewReportType1(HttpSession session, HttpServletRequest request) {
        Date trialTime = new Date();
        session.setAttribute("currentWeek", getWeekOfYear(trialTime));
        session.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        // List<ProjectDashboardDTO> telecomProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 1l, week, year);
        // List<ProjectDashboardDTO> digitalTransferProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 2l, week, year);
        // List<ProjectDashboardDTO> deploymentProject =
        // projectReportService.findAllDashboardProjectStep2(
        // 1, 3l, week, year);

        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);

        Init(); // Lấy dữ liệu cơ bản
        List<ListReport12DTO> dataType1 = projectReportService.findAllReportType12(1L);
        _mvShare.addObject("listReportType1", dataType1);
        _mvShare.setViewName("non-admin/report/kd-vien-thong");
        return _mvShare;
    }

    @RequestMapping(value = "/danh-sach/kd-chuyen-doi-so", method = RequestMethod.GET)
    public ModelAndView viewReportType2(HttpSession session, HttpServletRequest request) {
        Date trialTime = new Date();
        session.setAttribute("currentWeek", getWeekOfYear(trialTime));
        session.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        // List<ProjectDashboardDTO> telecomProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 1l, week, year);
        // List<ProjectDashboardDTO> digitalTransferProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 2l, week, year);
        // List<ProjectDashboardDTO> deploymentProject =
        // projectReportService.findAllDashboardProjectStep2(
        // 1, 3l, week, year);

        // Lấy url
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);
        Init(); // Lấy dữ liệu cơ bản
        List<ListReport12DTO> dataType2 = projectReportService.findAllReportType12(2L);
        _mvShare.addObject("listReportType2", dataType2);

        _mvShare.setViewName("non-admin/report/kd-chuyen-doi-so");
        return _mvShare;
    }

    @RequestMapping(value = "/danh-sach/trien-khai", method = RequestMethod.GET)
    public ModelAndView viewReportType3(HttpSession session, HttpServletRequest request) {
        Date trialTime = new Date();
        session.setAttribute("currentWeek", getWeekOfYear(trialTime));
        session.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));

        // Lấy last url
        String test = request.getRequestURI();
        String[] path = test.split("/");
        String lastPath = path[path.length - 1];
        _mvShare.addObject("url", lastPath);

        Init(); // Lấy dữ liệu cơ bản
        List<ListReport3DTO> dataType3 = projectReportService.findAllReportType3(3L);
        _mvShare.addObject("listReportType3", dataType3);
        _mvShare.setViewName("non-admin/report/trien-khai");
        return _mvShare;
    }

    @RequestMapping(value = "report/addNew/{type}", method = RequestMethod.POST)
    public String addNew(@ModelAttribute AddNewReportDTO dataUpdate, @PathVariable Integer type) {
        if (type == 3) {
            return "redirect:/danh-sach/trien-khai?addNew=" + projectReportService.addNewReport(dataUpdate);
        }
        if (type == 2) {
            return "redirect:/danh-sach/kd-chuyen-doi-so?addNew=" + projectReportService.addNewReport(dataUpdate);
        }
        if (type == 1) {
            return "redirect:/danh-sach/kd-vien-thong?addNew=" + projectReportService.addNewReport(dataUpdate);
        }

        return null;

    }
}
