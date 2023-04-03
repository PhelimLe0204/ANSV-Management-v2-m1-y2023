package vn.ansv.management.dto.Customer;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCustomerDTO {
    private Long id;
    private String avatar;
    private String customerName;
    private Integer enabled;
    private String createdBy;
    private Date createdDate;
    private String moddifiedBy;
    private Date moddifiedDate;

    public ListCustomerDTO(Long id, String avatar, String customerName, Integer enabled, String createdBy,
            Date createdDate, String moddifiedBy, Date moddifiedDate) {
        this.id = id;
        this.avatar = avatar;
        this.customerName = customerName;
        this.enabled = enabled;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.moddifiedBy = moddifiedBy;
        this.moddifiedDate = moddifiedDate;
    }

}
