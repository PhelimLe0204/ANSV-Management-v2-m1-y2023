package vn.ansv.management.dto.Customer;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewCustomerDTO {
    private String uid;
    private Integer enabled;
    private String customerName;
    private String avatarName;
    private MultipartFile avatarFile;
    private String createdBy;

    public AddNewCustomerDTO(String customerName, String avatarName, MultipartFile avatarFile, String createdBy) {
        this.customerName = customerName;
        this.avatarName = avatarName;
        this.avatarFile = avatarFile;
        this.createdBy = createdBy;
    }

}
