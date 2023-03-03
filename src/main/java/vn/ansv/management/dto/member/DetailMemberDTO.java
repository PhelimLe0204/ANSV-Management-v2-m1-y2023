package vn.ansv.management.dto.member;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailMemberDTO {
    private Long id; // User's id
    private String avatar;
    private String username;
    private String fullname;
    private String position;
    private String workCenter;
    private String jobAssigned;
    private String jobDetail;
    private String createdBy;
    private Date createdDate;

    public DetailMemberDTO(Long id, String avatar, String username, String fullname, String position, String workCenter,
            String jobAssigned, String jobDetail, String createdBy, Date createdDate) {
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.fullname = fullname;
        this.position = position;
        this.workCenter = workCenter;
        this.jobAssigned = jobAssigned;
        this.jobDetail = jobDetail;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

}
