package vn.ansv.management.dto.member;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
 * Liệt kê tên thành viên +
 * số lượng báo cáo tương ứng +
 * liệt kê báo cáo dự án theo tên thành viên
 */
public class TotalReportByUserDTO {
    private Long id;
    private int week;
    private int year;
    private String username;
    private String fullname;
    private int reportTotal;
    private List<ListReportLessByUserDTO> listReport;

    public TotalReportByUserDTO(Long id, int week, int year, String username, String fullname, int reportTotal) {
        this.id = id;
        this.week = week;
        this.year = year;
        this.username = username;
        this.fullname = fullname;
        this.reportTotal = reportTotal;
    }

}
