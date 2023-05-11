package vn.ansv.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ansv.management.dto.ProjectDTO;
import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;
import vn.ansv.management.dto.Statistic.DashboardChartDTO;
import vn.ansv.management.dto.User.UserProfileDTO;
import vn.ansv.management.dto.member.DetailMemberDTO;
import vn.ansv.management.dto.selectOption.OptionCurrencyUnitDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionProjectDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;
import vn.ansv.management.entity.ProjectEntity;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.MenuCategoryRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.service.CurrencyUnitService;
import vn.ansv.management.service.CustomerService;
import vn.ansv.management.service.FileUploadService;
import vn.ansv.management.service.ProjectReportMemberService;
import vn.ansv.management.service.ProjectReportService;
import vn.ansv.management.service.ProjectService;
import vn.ansv.management.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
    // DI = Dependency Injection
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    // @Autowired
    // private MenuRepository menuRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ProjectReportService projectReportService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectReportMemberService projectReportMemberService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CurrencyUnitService currencyUnitService;

    @Autowired
    private UserService userService;

    @GetMapping("/project")
    public ResponseEntity<ResponseObject> getAllProject() {
        Iterable<ProjectEntity> data = projectRepository.findAll();
        // List<LayoutMenuCategoryDTO> menuCategoryTest =
        // menuCategoryRepository.findAllLayout();
        // List<LayoutMenuDTO> menuTest = menuRepository.findAllLayoutLevel1(1l, 1);
        List<LayoutMenuCategoryDTO> menuCategoryLayout = menuCategoryRepository.findAllLayout();
        // System.out.println("--- menuCategoryTest: " + menuCategoryTest);
        // System.out.println("--- menuTest: " + menuTest);

        if (data.iterator().hasNext()) {
            ModelMapper mapper = new ModelMapper();
            List<ProjectDTO> dataDto = mapper.map(data, new TypeToken<List<ProjectDTO>>() {
            }.getType());
            System.out.println("--- dataDto: " + dataDto);
            // return ResponseEntity.status(HttpStatus.OK).body(
            // new ResponseObject("success", "Dữ liệu bảng project", dataDto));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Dữ liệu bảng Menu", menuCategoryLayout));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("success", "null", ""));
        }
    }

    @GetMapping("/getProjectSelectOption")
    public ResponseEntity<ResponseObject> getProjectSelectOption() {
        List<OptionProjectDTO> data = projectService.findAllSelectOption();

        if (data.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Danh sách dự án sử dụng cho select option", data));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("success", "null", ""));
        }
    }

    @GetMapping("/getCustomerSelectOption")
    public ResponseEntity<ResponseObject> getCustomerSelectOption() {
        List<OptionCustomerDTO> data = customerService.findAllSelectOption();

        if (data.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Danh sách khách hàng sử dụng cho select option", data));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("success", "null", ""));
        }
    }

    @GetMapping("/getCurrencyUnitSelectOption")
    public ResponseEntity<ResponseObject> getCurrencyUnitSelectOption() {
        Iterable<OptionCurrencyUnitDTO> data = currencyUnitService.findAllSelectOption();
        // data.forEach((dataItem) -> {
        // System.out.println("--------------------------------------------");
        // System.out.println("id: " + dataItem.getId());
        // System.out.println("Currency unit: " + dataItem.getCurrencyUnit());
        // System.out.println("Description: " + dataItem.getDescription());
        // });

        if (data.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Danh sách đơn vị tiền tệ dùng cho select option", data));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("success", "null", ""));
        }
    }

    @GetMapping("/getUserSelectOption")
    public ResponseEntity<ResponseObject> getUserSelectOption() {
        List<OptionUserDTO> data = userService.findAllUserOption();
        String message = "Danh sách user dùng cho select option";

        if (data.isEmpty()) {
            message = "null";
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", message, data));
    }

    @GetMapping("/member/updateEnabled/{id}/{enabled}")
    public ResponseEntity<ResponseObject> updateMemberEnabled(@PathVariable Long id, @PathVariable Integer enabled) {
        if (userService.updateUserEnabled(id, enabled)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cập nhật trạng thái thành viên thành công", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cập nhật trạng thái thành viên thất bại", ""));
        }
    }

    @GetMapping("/customer/updateEnabled/{id}/{enabled}")
    public ResponseEntity<ResponseObject> updateCustomerEnabled(@PathVariable Long id, @PathVariable Integer enabled) {
        if (customerService.updateCustomerEnabled(id, enabled)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cập nhật trạng thái khách hàng thành công", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cập nhật trạng thái khách hàng thất bại", ""));
        }
    }

    @GetMapping("/deleteProject/{id}")
    public ResponseEntity<ResponseObject> deleteProject(@PathVariable Long id) {

        if (fileUploadService.deleteFileUploadByProjectReportId(id) == 1
                && projectReportMemberService.deleteMemberByReportId(id) == 1
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Đã xóa report", ""));
        } else if (fileUploadService.deleteFileUploadByProjectReportId(id) == 2
                && projectReportMemberService.deleteMemberByReportId(id) == 1
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Đã xóa report", ""));

        } else if (fileUploadService.deleteFileUploadByProjectReportId(id) == 2
                && projectReportMemberService.deleteMemberByReportId(id) == 2
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Đã xóa report", ""));

        } else if (fileUploadService.deleteFileUploadByProjectReportId(id) == 1
                && projectReportMemberService.deleteMemberByReportId(id) == 2
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Đã xóa report", ""));

        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Lỗi hệ thống! Vui lòng thử lại sau", ""));
    }

    // @PostMapping("/addMemberIntoReport")
    // public ResponseEntity<ResponseObject> addMemberIntoReport(@RequestBody
    // AddMemberDTO dataInsert) {
    // System.out.println("================== OK OK");

    // if (projectReportMemberService.addMember(dataInsert)) {
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("success", "Thêm thành viên thành công.", ""));
    // } else {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    // new ResponseObject("failed", "Thêm thành viên thất bại.", ""));
    // }
    // }

    @GetMapping("/deleteMember/{memberId}")
    public ResponseEntity<ResponseObject> deleteMember(@PathVariable Long memberId) {
        if (projectReportMemberService.deleteMember(memberId) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Đã xóa thành viên khỏi dự án", ""));
        }
        if (projectReportMemberService.deleteMember(memberId) == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Thất bại! Vui lòng thử lại sau", ""));
        }
        if (projectReportMemberService.deleteMember(memberId) == 2) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Đối tượng không xác định", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Lỗi hệ thống! Vui lòng thử lại sau", ""));
    }

    @GetMapping("/detailMember/{memberId}")
    public ResponseEntity<ResponseObject> detailMember(@PathVariable Long memberId) {
        DetailMemberDTO data = projectReportMemberService.detailMemberReport(memberId);
        if (data.getId() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Thông tin 1 thành viên thuộc báo cáo", data));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Đối tượng không xác định", ""));
        }
    }

    @GetMapping("/getCustomerIdByProjectId/{id}")
    public ResponseEntity<ResponseObject> getCustomerIdByProjectId(@PathVariable Long id) {
        Long customerId = projectService.getCustomerIdById(id);
        String message = null;

        if (customerId > 0) {
            message = "Customer by project.";
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", message, customerId));
    }

    @GetMapping("/getCustomerDetail/{id}")
    public ResponseEntity<ResponseObject> getCustomerDetail(@PathVariable Long id) {
        ListCustomerDTO data = customerService.findDetailById(id);

        if (data.getId() == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Failed", "Đối tượng không xác định.", null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Chi tiết khách hàng", data));
    }

    @GetMapping("/getUserProfile/{userId}")
    public ResponseEntity<ResponseObject> userProfile(@PathVariable Long userId) {
        UserProfileDTO data = userService.findUserProfileById(userId);
        if (data.getId() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Thông tin cá nhân", data));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Đối tượng không xác định", ""));
        }
    }

    @GetMapping("/getDashboardChart/{type}")
    public ResponseEntity<ResponseObject> getDashboardChart(HttpSession session, @PathVariable Long type) {
        int week = (int) session.getAttribute("thisWeek");
        int year = (int) session.getAttribute("thisYear");
        DashboardChartDTO data = projectReportService.dashboardChart(week, year, type, 1);
        if (data != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Thống kê báo cáo Dashboard", data));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Đối tượng không xác định", ""));
        }
    }

    @GetMapping("/danh-sach/trien-khai")
    public ResponseEntity<ResponseObject> getList3Paging(HttpSession session, HttpServletRequest request) {
        try {
            int size = Integer.parseInt(request.getParameter("size"));
            int page = Integer.parseInt(request.getParameter("page"));
            String msg = "Dữ liệu báo cáo Triển khai trang thứ " + page;
            if (request.getParameter("week") != null && request.getParameter("year") != null) {
                int week = Integer.parseInt(request.getParameter("week"));
                int year = Integer.parseInt(request.getParameter("year"));
                session.setAttribute("thisWeek", week);
                session.setAttribute("thisYear", year);
                msg = "WeekAndYear";
            }

            String userRole = (String) session.getAttribute("userRole");
            userRole = userRole.substring(0, userRole.indexOf("___"));
            if (userRole.contains("DOC_BDC") || userRole.contains("Manager_AM")
                    || userRole.contains("Main_AM") || userRole.contains("Member_AM")) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Bạn chưa đủ điều kiện tiếp nhận dữ liệu!", ""));
            }
            String username = (String) session.getAttribute("username");
            int week = (int) session.getAttribute("thisWeek");
            if (userRole.contains("Admin") || userRole.contains("CEO")
                    || userRole.contains("DGD") || userRole.contains("Manager_PM")) {
                ResponseObject dataType3Week = projectReportService.findListReportType3(
                        week, null, 3L, page, size);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", msg, dataType3Week));
            } else {
                ResponseObject dataType3Week = projectReportService.findListReportType3(
                        week, username, 3L, page, size);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", msg, dataType3Week));
            }
        } catch (NumberFormatException nfe) {
            System.out.println("----- HomeController.viewDashboard() ----- " + nfe);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Dữ liệu yêu cầu không thỏa mãn", ""));
        } catch (Exception e) {
            System.out.println("----- HomeController.viewDashboard() ----- " + e);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Lỗi xử lý! Vui lòng thử lại sau", ""));
        }
    }

    @GetMapping("/danh-sach/kd-vien-thong")
    public ResponseEntity<ResponseObject> getList1paging(HttpSession session, HttpServletRequest request) {
        try {
            int size = Integer.parseInt(request.getParameter("size"));
            int page = Integer.parseInt(request.getParameter("page"));

            String userRole = (String) session.getAttribute("userRole");
            userRole = userRole.substring(0, userRole.indexOf("___"));
            if (userRole.contains("DOC_DO") || userRole.contains("Manager_PM")
                    || userRole.contains("Main_PM") || userRole.contains("Member_PM")) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Bạn chưa đủ điều kiện tiếp nhận dữ liệu!", ""));

            }
            String username = (String) session.getAttribute("username");
            int week = (int) session.getAttribute("thisWeek");
            String msg = "Dữ liệu báo cáo Viễn thông trang thứ " + page;
            if (userRole.contains("Admin") || userRole.contains("CEO")
                    || userRole.contains("DGD") || userRole.contains("Manager_PM")) {
                ResponseObject dataType3Week = projectReportService.findListReportType3(
                        week, null, 3L, page, size);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", msg, dataType3Week));
            } else {
                ResponseObject dataType3Week = projectReportService.findListReportType3(
                        week, username, 3L, page, size);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", msg, dataType3Week));
            }
        } catch (NumberFormatException nfe) {
            System.out.println("----- HomeController.viewDashboard() ----- " + nfe);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Dữ liệu yêu cầu không thỏa mãn", ""));
        } catch (Exception e) {
            System.out.println("----- HomeController.viewDashboard() ----- " + e);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Lỗi xử lý! Vui lòng thử lại sau", ""));
        }
    }
}
