package vn.ansv.management.controller.NonAdmin;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "")
// http:localhost:8083
public class HomeController extends BaseController {

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

        // List<Dashboard_ProjectDto> telecomProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 1, week, year);
        // List<Dashboard_ProjectDto> digitalTransferProject =
        // projectReportService.findAllDashboardProjectStep1(
        // 1, 2, week, year);
        // List<Dashboard_ProjectDto> deploymentProject =
        // projectReportService.findAllDashboardProjectStep2(
        // 1, 3, week, year);

        Init(); // Lấy dữ liệu cơ bản
        _mvShare.addObject("telecomProject", null); // Du an kinh doanh Vien thong
        _mvShare.addObject("digitalTransferProject", null); // Du an kinh doanh Chuyen doi so
        _mvShare.addObject("deploymentProject", null); // Du an Trien khai
        _mvShare.setViewName("non-admin/dashboard");
        return _mvShare;
    }
}
