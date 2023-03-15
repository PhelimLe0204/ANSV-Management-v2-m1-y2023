package vn.ansv.management.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDefineDTO {
    private Long id;
    private String userRole;

    public UserDefineDTO(Long id, String userRole) {
        this.id = id;
        this.userRole = userRole;
    }

}
