package vn.ansv.management.controller.NonAdmin;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabQuaTrinhDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabThanhVienDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabCptgDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.UpdateDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.member.AddMemberDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;
import vn.ansv.management.dto.selectOption.OptionProjectStatusDTO;
import vn.ansv.management.dto.selectOption.OptionProjectTypeDTO;
import vn.ansv.management.service.ProjectPriorityService;
import vn.ansv.management.service.ProjectReportMemberService;
import vn.ansv.management.service.ProjectReportService;
import vn.ansv.management.service.ProjectStatusService;
import vn.ansv.management.service.ProjectTypeService;
import vn.ansv.management.service.UserService;

@Controller
@RequestMapping(path = "")
// http:localhost:8083
public class HomeController extends BaseController {

    @Autowired // Inject "ProjectReportService" - Dependency Injection
    private ProjectReportService projectReportService;

    @Autowired // Inject "ProjectReportMemberService" - Dependency Injection
    private ProjectReportMemberService projectReportMemberService;

    @Autowired // Inject "ProjectTypeService" - Dependency Injection
    private ProjectTypeService projectTypeService;

    @Autowired // Inject "ProjectPriorityService" - Dependency Injection
    private ProjectPriorityService projectPriorityService;

    @Autowired // Inject "ProjectStatusService" - Dependency Injection
    private ProjectStatusService projectStatusService;

    @Autowired // Inject "UserService" - Dependency Injection
    private UserService userService;

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
        Long project_report_id = Long.parseLong(request.getParameter("id"));

        ReportDetailTabPhanLoaiDTO projectDetailTabPhanLoai = projectReportService
                .findDetailTabPhanLoai(project_report_id, 1);
        ReportDetailTabDuThauDTO projectDetailTabDuThau = projectReportService
                .findDetailTabDuThau(project_report_id, 1);
        ReportDetailTabCptgDTO projectDetailTabCptg = projectReportService
                .findDetailTabChiPhiThoiGian(project_report_id, 1);
        ReportDetailTabQuaTrinhDTO projectDetailTabQuaTrinh = projectReportService
                .findDetailTabQuaTrinh(project_report_id, 1);
        List<ReportDetailTabThanhVienDTO> projectDetailTabThanhVien = projectReportMemberService
                .findAllMemberByReport(project_report_id);

        List<OptionProjectTypeDTO> optionProjectTypeDTO = projectTypeService.findAllOption();
        List<OptionProjectPriorityDTO> optionProjectPriorityDTO = projectPriorityService.findAllOption();
        List<OptionProjectStatusDTO> optionProjectStatusDTO = projectStatusService.findAllOption();

        Init(); // Lấy dữ liệu cơ bản

        _mvShare.addObject("detailTabPhanLoai", projectDetailTabPhanLoai);
        _mvShare.addObject("detailTabDuThau", projectDetailTabDuThau);
        _mvShare.addObject("detailTabCptg", projectDetailTabCptg);
        _mvShare.addObject("detailTabQuaTrinh", projectDetailTabQuaTrinh);
        _mvShare.addObject("detailTabThanhVien", projectDetailTabThanhVien);

        _mvShare.addObject("optionType", optionProjectTypeDTO);
        _mvShare.addObject("optionPriority", optionProjectPriorityDTO);
        _mvShare.addObject("optionStatus", optionProjectStatusDTO);

        _mvShare.setViewName("non-admin/project-detail/main-detail");
        return _mvShare;
    }

    @PostMapping("/chi-tiet/update/1/{id}")
    public String updateDetailTabPhanLoai(@PathVariable Long id,
            @ModelAttribute UpdateDetailTabPhanLoaiDTO dataUpdate, Model model, HttpServletRequest request) {
        System.out.println("----- HomeController.updateDetailTabPhanLoai - id: " + dataUpdate.getId());
        if (projectReportService.updateDetailTabPhanLoai(id, dataUpdate)) {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=true&tab=" + 1;
        } else {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=false&tab=" + 1;
        }
    }

    @PostMapping("/chi-tiet/update/2/{id}")
    public String updateDetailTabDuThau(@PathVariable Long id,
            @ModelAttribute UpdateDetailTabDuThauDTO dataUpdate, Model model, HttpServletRequest request) {
        System.out.println("----- HomeController.updateDetailTabDuThau - id: " + dataUpdate.getId());
        if (projectReportService.updateDetailTabDuThau(id, dataUpdate)) {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=true&tab=" + 2;
        } else {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=false&tab=" + 2;
        }
    }

    @PostMapping("/chi-tiet/update/3/{id}")
    public String updateDetailTabCPTT(@PathVariable Long id,
            @ModelAttribute ReportDetailTabCptgDTO dataUpdate, Model model, HttpServletRequest request) {
        System.out.println("----- HomeController.updateDetailTabCPTT - id: " + dataUpdate.getId());
        if (projectReportService.updateDetailTabCptg(id, dataUpdate)) {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=true&tab=" + 3;
        } else {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=false&tab=" + 3;
        }
    }

    @PostMapping("/chi-tiet/update/4/{id}")
    public String updateDetailTabQuaTrinh(@PathVariable Long id,
            @ModelAttribute ReportDetailTabQuaTrinhDTO dataUpdate, Model model, HttpServletRequest request) {
        if (projectReportService.updateDetailTabQuaTrinh(id, dataUpdate)) {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=true&tab=" + 4;
        } else {
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=false&tab=" + 4;
        }
    }

    @RequestMapping(value = "thanh-vien/bdc", method = RequestMethod.GET)
    public ModelAndView membersBDC() {
        Init(); // Lấy dữ liệu cơ bản
        List<ListAllMemberDTO> data = userService.findAllByWorkCenter(1L);

        _mvShare.addObject("listUserBDC", data);
        _mvShare.setViewName("non-admin/members/bdc");
        return _mvShare;
    }

    @RequestMapping(value = "thanh-vien/do", method = RequestMethod.GET)
    public ModelAndView membersDO() {

        Init(); // Lấy dữ liệu cơ bản
        List<ListAllMemberDTO> data = userService.findAllByWorkCenter(2L);

        _mvShare.addObject("listUserDO", data);
        _mvShare.setViewName("non-admin/members/do");
        return _mvShare;
    }

    // Tab "Thành viên": Thêm thành viên vào báo cáo
    @PostMapping("/chi-tiet/add-member/{id}")
    public String addMember(@PathVariable Long id, @ModelAttribute AddMemberDTO addMemberDTO) {
        // 0 - Thất bại, 1 - Thành công, 2 - Đã tồn tại thành viên
        int count = projectReportMemberService.addMember(addMemberDTO);

        if (count == 0) {
            // Thêm thành viên thất bại
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=false&tab=" + 5;
        }
        if (count == 1) {
            // Thêm thành viên thành công
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=true&tab=" + 5;
        }
        if (count == 2) {
            // Thành viên đã tồn tại
            return "redirect:/chi-tiet?id=" + id + "&updateSuccess=false&status=2&tab=" + 5;
        }
        return "redirect:/chi-tiet?id=" + id;
    }
}
