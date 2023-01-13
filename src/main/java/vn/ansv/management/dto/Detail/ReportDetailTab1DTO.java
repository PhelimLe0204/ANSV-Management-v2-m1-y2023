package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailTab1DTO {

    private Long id;
    private String jobName;

    private Long projectId;
    private String projectName; // Tên dự án

    private String customerName; // Khách hàng

    private Long typeId;
    private String typeDisplay;

    private Long priorityId;
    private String priorityDisplay; // Mức độ ưu tiên

    private Long statusId;
    private String statusColor; // Màu của trạng thái
    private String statusDisplay; // Trạng thái báo cáo

    private Integer week;
    private Integer year;
    private String maHopDong;
    private String maKeToan;

    private Long currencyUnitId;
    private String currencyUnitDisplay; // Đơn vị tiền tệ

    public ReportDetailTab1DTO(Long id, String jobName, Long projectId, String projectName, String customerName,
            Long typeId, String typeDisplay, Long priorityId, String priorityDisplay, Long statusId, String statusColor,
            String statusDisplay, Integer week, Integer year, String maHopDong, String maKeToan, Long currencyUnitId,
            String currencyUnitDisplay) {
        this.id = id;
        this.jobName = jobName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.customerName = customerName;
        this.typeId = typeId;
        this.typeDisplay = typeDisplay;
        this.priorityId = priorityId;
        this.priorityDisplay = priorityDisplay;
        this.statusId = statusId;
        this.statusColor = statusColor;
        this.statusDisplay = statusDisplay;
        this.week = week;
        this.year = year;
        this.maHopDong = maHopDong;
        this.maKeToan = maKeToan;
        this.currencyUnitId = currencyUnitId;
        this.currencyUnitDisplay = currencyUnitDisplay;
    }

}
