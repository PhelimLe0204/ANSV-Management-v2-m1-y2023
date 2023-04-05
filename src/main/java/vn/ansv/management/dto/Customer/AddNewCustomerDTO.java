package vn.ansv.management.dto.Customer;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewCustomerDTO {
    private String customerName;
    private MultipartFile file;
    private String createdBy;

    public AddNewCustomerDTO(String customerName, MultipartFile file, String createdBy) {
        this.customerName = customerName;
        this.file = file;
        this.createdBy = createdBy;
    }

}
