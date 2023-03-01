package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailTabThanhVienDTO {
    private Long id;
    private Long projectId;
    private Long firstReportId;
    private String avatar;
    private String fullname;
    private String position;

    public ReportDetailTabThanhVienDTO(Long id, Long projectId, Long firstReportId, String avatar, String fullname,
            String position) {
        this.id = id;
        this.projectId = projectId;
        this.firstReportId = firstReportId;
        this.avatar = avatar;
        this.fullname = fullname;
        this.position = position;
    }

}
