package vn.ansv.management.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDefineDTO {
    private Long id;
    private String userRole;
    private String avatar;

    public UserDefineDTO(Long id, String userRole, String avatar) {
        this.id = id;
        this.userRole = userRole;
        this.avatar = avatar;
    }

}
