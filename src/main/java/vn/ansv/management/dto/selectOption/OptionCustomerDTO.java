package vn.ansv.management.dto.selectOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionCustomerDTO {
    private Long id;
    private String avatar;
    private String customerName;

    public OptionCustomerDTO(Long id, String avatar, String customerName) {
        this.id = id;
        this.avatar = avatar;
        this.customerName = customerName;
    }
    
}
