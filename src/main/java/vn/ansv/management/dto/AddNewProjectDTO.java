package vn.ansv.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewProjectDTO {
    private String createdBy;
    private String uid;
    private Long projectCustomer;
    private String projectName;
    private String projectDescription;

    public AddNewProjectDTO() {
    }

    public AddNewProjectDTO(String createdBy, String uid, Long projectCustomer, String projectName,
            String projectDescription) {
        this.createdBy = createdBy;
        this.uid = uid;
        this.projectCustomer = projectCustomer;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

}
