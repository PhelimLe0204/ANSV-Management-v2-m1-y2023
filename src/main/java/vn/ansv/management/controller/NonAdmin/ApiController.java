package vn.ansv.management.controller.NonAdmin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ansv.management.dto.ProjectDTO;
import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;
// import vn.ansv.management.dto.Layout.LayoutMenuDTO;
import vn.ansv.management.dto.selectOption.OptionCurrencyUnitDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionProjectDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;
import vn.ansv.management.entity.ProjectEntity;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.MenuCategoryRepository;
// import vn.ansv.management.repository.MenuRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.service.CurrencyUnitService;
import vn.ansv.management.service.CustomerService;
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
    private ProjectService projectService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CurrencyUnitService currencyUnitService;

    @Autowired
    private UserService userService;

    @GetMapping("/project")
    ResponseEntity<ResponseObject> getAllProject() {
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
    ResponseEntity<ResponseObject> getProjectSelectOption() {
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
    ResponseEntity<ResponseObject> getCustomerSelectOption() {
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
    ResponseEntity<ResponseObject> getCurrencyUnitSelectOption() {
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
    ResponseEntity<ResponseObject> getUserSelectOption() {
        List<OptionUserDTO> data = userService.findAllUserOption();
        String message = "Danh sách user dùng cho select option";

        if (data.isEmpty()) {
            message = "null";
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", message, data));
    }

    @GetMapping("/updateEnabled/{id}/{enabled}")
    ResponseEntity<ResponseObject> updateEnabled(@PathVariable Long id, @PathVariable Integer enabled) {
        if (userService.updateUserEnabled(id, enabled)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cập nhật trạng thái thành công", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cập nhật trạng thái thất bại", ""));
        }
    }
}
