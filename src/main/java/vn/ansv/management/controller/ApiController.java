package vn.ansv.management.controller;

import java.util.List;

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
import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;
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
            // new ResponseObject("success", "D??? li???u b???ng project", dataDto));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "D??? li???u b???ng Menu", menuCategoryLayout));
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
                    new ResponseObject("success", "Danh s??ch d??? ??n s??? d???ng cho select option", data));
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
                    new ResponseObject("success", "Danh s??ch kh??ch h??ng s??? d???ng cho select option", data));
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
                    new ResponseObject("success", "Danh s??ch ????n v??? ti???n t??? d??ng cho select option", data));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("success", "null", ""));
        }
    }

    @GetMapping("/getUserSelectOption")
    public ResponseEntity<ResponseObject> getUserSelectOption() {
        List<OptionUserDTO> data = userService.findAllUserOption();
        String message = "Danh s??ch user d??ng cho select option";

        if (data.isEmpty()) {
            message = "null";
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", message, data));
    }

    @GetMapping("/updateEnabled/{id}/{enabled}")
    public ResponseEntity<ResponseObject> updateEnabled(@PathVariable Long id, @PathVariable Integer enabled) {
        if (userService.updateUserEnabled(id, enabled)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "C???p nh???t tr???ng th??i th??nh c??ng", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "C???p nh???t tr???ng th??i th???t b???i", ""));
        }
    }

    @GetMapping("/deleteProject/{id}")
    public ResponseEntity<ResponseObject> deleteProject(@PathVariable Long id) {

        if (fileUploadService.deleteFileUploadByProjectReportId(id) == 1
                && projectReportMemberService.deleteMemberByReportId(id) == 1
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "???? x??a report", ""));
        } else if (fileUploadService.deleteFileUploadByProjectReportId(id) == 2
                && projectReportMemberService.deleteMemberByReportId(id) == 1
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "???? x??a report", ""));

        } else if (fileUploadService.deleteFileUploadByProjectReportId(id) == 2
                && projectReportMemberService.deleteMemberByReportId(id) == 2
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "???? x??a report", ""));

        } else if (fileUploadService.deleteFileUploadByProjectReportId(id) == 1
                && projectReportMemberService.deleteMemberByReportId(id) == 2
                && projectReportService.deleteReportById(id) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "???? x??a report", ""));

        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "L???i h??? th???ng! Vui l??ng th??? l???i sau", ""));
    }

    // @PostMapping("/addMemberIntoReport")
    // public ResponseEntity<ResponseObject> addMemberIntoReport(@RequestBody
    // AddMemberDTO dataInsert) {
    // System.out.println("================== OK OK");

    // if (projectReportMemberService.addMember(dataInsert)) {
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("success", "Th??m th??nh vi??n th??nh c??ng.", ""));
    // } else {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    // new ResponseObject("failed", "Th??m th??nh vi??n th???t b???i.", ""));
    // }
    // }

    @GetMapping("/deleteMember/{memberId}")
    public ResponseEntity<ResponseObject> deleteMember(@PathVariable Long memberId) {
        if (projectReportMemberService.deleteMember(memberId) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "???? x??a th??nh vi??n kh???i d??? ??n", ""));
        }
        if (projectReportMemberService.deleteMember(memberId) == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Th???t b???i! Vui l??ng th??? l???i sau", ""));
        }
        if (projectReportMemberService.deleteMember(memberId) == 2) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "?????i t?????ng kh??ng x??c ?????nh", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "L???i h??? th???ng! Vui l??ng th??? l???i sau", ""));
    }

    @GetMapping("/detailMember/{memberId}")
    public ResponseEntity<ResponseObject> detailMember(@PathVariable Long memberId) {
        DetailMemberDTO data = projectReportMemberService.detailMemberReport(memberId);
        if (data.getId() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Th??ng tin 1 th??nh vi??n thu???c b??o c??o", data));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "?????i t?????ng kh??ng x??c ?????nh", ""));
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
}
