package vn.ansv.management.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLdapDTO {
    private String fullname;
    private String username;
    private String description;
    private Integer used;

    public UserLdapDTO() {
    }

    public UserLdapDTO(String fullname, String username, String description, Integer used) {
        this.fullname = fullname;
        this.username = username;
        this.description = description;
        this.used = used;
    }

}
