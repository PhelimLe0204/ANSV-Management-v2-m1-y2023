package vn.ansv.management.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Liệt kê tên báo cáo dự án theo tên thành viên
public class ListReportLessByUserDTO {
    private Long id;
    private String customerAvatar;
    private String reportName;
    private String typeName;
    private String priorityName;
    private String statusName;

    public ListReportLessByUserDTO(Long id, String customerAvatar, String reportName, String typeName,
            String priorityName, String statusName) {
        this.id = id;
        this.customerAvatar = customerAvatar;
        this.reportName = reportName;
        this.typeName = typeName;
        this.priorityName = priorityName;
        this.statusName = statusName;
    }

}
