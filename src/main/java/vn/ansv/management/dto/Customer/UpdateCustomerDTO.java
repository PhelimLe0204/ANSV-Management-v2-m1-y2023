package vn.ansv.management.dto.Customer;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerDTO {
    private Long id;
    private String customerName;
    private String avatarName;
    private MultipartFile avatarFile;
    private String modifiedBy;

    public UpdateCustomerDTO(Long id, String customerName, String avatarName,
            MultipartFile avatarFile, String modifiedBy) {
        this.id = id;
        this.customerName = customerName;
        this.avatarName = avatarName;
        this.avatarFile = avatarFile;
        this.modifiedBy = modifiedBy;
    }

}
