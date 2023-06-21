package vn.ansv.management.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.Detail.ReportDetailTabHopDongDTO;

/* ===== HopDongRepository.findDetailTabHopDong() ===== */
@NamedNativeQuery(name = "HopDongEntity.findDetailTabHopDong", query = "SELECT "
        + "hd.id, hd.ngay_ky AS ngayKy, hd.ngay_hieu_luc AS ngayHieuLuc, hd.ngay_ket_thuc AS ngayKetThuc, "
        + "hd.ngay_mo_BL_THHD AS ngayMoBLTHHD, hd.ngay_het_han_BL_THHD AS ngayHetHanBLTHHD, hd.note_BL_THHD AS noteBLTHHD, "
        + "hd.ngay_mo_BL_TU AS ngayMoBLTU, hd.ngay_het_han_BL_TU AS ngayHetHanBLTU, hd.note_BL_TU AS noteBLTU, "
        + "hd.ngay_mo_BL_BH AS ngayMoBLBH, hd.ngay_het_han_BL_BH AS ngayHetHanBLBH, hd.note_BL_BH AS noteBLBH, "
        + "hd.project_id AS projectId "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN hop_dong AS hd ON p.id = hd.project_id "
        + "WHERE pr.id = :id AND pr.enabled = :enabled", resultSetMapping = "Mapping.ReportDetailTabHopDongDTO")

/* ===== Set mapping: ReportDetailTabHopDongDTO ===== */
@SqlResultSetMapping(name = "Mapping.ReportDetailTabHopDongDTO", classes = @ConstructorResult(targetClass = ReportDetailTabHopDongDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "ngayKy", type = String.class),
        @ColumnResult(name = "ngayHieuLuc", type = String.class),
        @ColumnResult(name = "ngayKetThuc", type = String.class),
        @ColumnResult(name = "ngayMoBLTHHD", type = String.class),
        @ColumnResult(name = "ngayHetHanBLTHHD", type = String.class),
        @ColumnResult(name = "noteBLTHHD", type = String.class),
        @ColumnResult(name = "ngayMoBLTU", type = String.class),
        @ColumnResult(name = "ngayHetHanBLTU", type = String.class),
        @ColumnResult(name = "noteBLTU", type = String.class),
        @ColumnResult(name = "ngayMoBLBH", type = String.class),
        @ColumnResult(name = "ngayHetHanBLBH", type = String.class),
        @ColumnResult(name = "noteBLBH", type = String.class),
        @ColumnResult(name = "projectId", type = Long.class) }))

@Entity
@Table(name = "hop_dong")
public class HopDongEntity extends BaseEntity {
    @Column(name = "ngay_ky")
    private String ngayKy;

    @Column(name = "ngay_hieu_luc")
    private String ngayHieuLuc;

    @Column(name = "ngay_ket_thuc")
    private String ngayKetThuc;

    @Column(name = "noi_theo_doi_BL_THHD")
    private String noiTheoDoiBLTHHD; // Nơi theo dõi bảo lãnh thực hiện hợp đồng

    @Column(name = "ngay_mo_BL_THHD")
    private String ngayMoBLTHHD; // Ngày mở bảo lãnh thực hiện hợp đồng

    @Column(name = "ngay_het_han_BL_THHD")
    private String ngayHetHanBLTHHD; // Ngày hết hạn bảo lãnh thực hiện hợp đồng

    @Column(name = "note_BL_THHD")
    private String noteBLTHHD; // Ghi chú bảo lãnh thực hiện hợp đồng

    @Column(name = "ngay_mo_BL_TU")
    private String ngayMoBLTU; // Ngày mở bảo lãnh tạm ứng

    @Column(name = "ngay_het_han_BL_TU")
    private String ngayHetHanBLTU; // Ngày hết hạn bảo lãnh tạm ứng

    @Column(name = "note_BL_TU")
    private String noteBLTU; // Ghi chú bảo lãnh tạm ứng

    @Column(name = "ngay_mo_BL_BH")
    private String ngayMoBLBH; // Ngày mở bảo lãnh bảo hành

    @Column(name = "ngay_het_han_BL_BH")
    private String ngayHetHanBLBH; // Ngày hết hạn bảo lãnh bảo hành

    @Column(name = "note_BL_BH")
    private String noteBLBH; // Ghi chú bảo lãnh bảo hành

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity project;

    public String getNgayKy() {
        return this.ngayKy;
    }

    public void setNgayKy(String ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getNgayHieuLuc() {
        return this.ngayHieuLuc;
    }

    public void setNgayHieuLuc(String ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public String getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getNoiTheoDoiBLTHHD() {
        return this.noiTheoDoiBLTHHD;
    }

    public void setNoiTheoDoiBLTHHD(String noiTheoDoiBLTHHD) {
        this.noiTheoDoiBLTHHD = noiTheoDoiBLTHHD;
    }

    public String getNgayMoBLTHHD() {
        return this.ngayMoBLTHHD;
    }

    public void setNgayMoBLTHHD(String ngayMoBLTHHD) {
        this.ngayMoBLTHHD = ngayMoBLTHHD;
    }

    public String getNgayHetHanBLTHHD() {
        return this.ngayHetHanBLTHHD;
    }

    public void setNgayHetHanBLTHHD(String ngayHetHanBLTHHD) {
        this.ngayHetHanBLTHHD = ngayHetHanBLTHHD;
    }

    public String getNoteBLTHHD() {
        return this.noteBLTHHD;
    }

    public void setNoteBLTHHD(String noteBLTHHD) {
        this.noteBLTHHD = noteBLTHHD;
    }

    public String getNgayMoBLTU() {
        return this.ngayMoBLTU;
    }

    public void setNgayMoBLTU(String ngayMoBLTU) {
        this.ngayMoBLTU = ngayMoBLTU;
    }

    public String getNgayHetHanBLTU() {
        return this.ngayHetHanBLTU;
    }

    public void setNgayHetHanBLTU(String ngayHetHanBLTU) {
        this.ngayHetHanBLTU = ngayHetHanBLTU;
    }

    public String getNoteBLTU() {
        return this.noteBLTU;
    }

    public void setNoteBLTU(String noteBLTU) {
        this.noteBLTU = noteBLTU;
    }

    public String getNgayMoBLBH() {
        return this.ngayMoBLBH;
    }

    public void setNgayMoBLBH(String ngayMoBLBH) {
        this.ngayMoBLBH = ngayMoBLBH;
    }

    public String getNgayHetHanBLBH() {
        return this.ngayHetHanBLBH;
    }

    public void setNgayHetHanBLBH(String ngayHetHanBLBH) {
        this.ngayHetHanBLBH = ngayHetHanBLBH;
    }

    public String getNoteBLBH() {
        return this.noteBLBH;
    }

    public void setNoteBLBH(String noteBLBH) {
        this.noteBLBH = noteBLBH;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

}
