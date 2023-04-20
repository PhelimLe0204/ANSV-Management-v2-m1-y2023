package vn.ansv.management.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    private String avatar;
    private String fullname;
    private String username;
    private String employeeCode;
    private String userRole;
    private String workCenter;

    public UserProfileDTO(Long id, String avatar, String fullname, String username, String employeeCode,
            String userRole, String workCenter) {
        this.id = id;
        this.avatar = avatar;
        this.fullname = fullname;
        this.username = username;
        this.employeeCode = employeeCode;
        this.userRole = userRole;
        this.workCenter = workCenter;
    }

}
