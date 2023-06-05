package vn.ansv.management.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import vn.ansv.management.dto.Detail.SupportCptgLessDTO;

/* ===== ProjectReportSubdataEntity.findSubdataDAC2() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataDAC2", query = "SELECT "
        + "prs.id, prs.so_tien_dac_2 AS soTien, prs.hop_dong_dac_2 AS hopDong, "
        + "prs.muc_tieu_dac_2 AS mucTieu, prs.thuc_te_dac_2 AS thucTe, prs.note_dac_2 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataDAC3() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataDAC3", query = "SELECT "
        + "prs.id, prs.so_tien_dac_3 AS soTien, prs.hop_dong_dac_3 AS hopDong, "
        + "prs.muc_tieu_dac_3 AS mucTieu, prs.thuc_te_dac_3 AS thucTe, prs.note_dac_3 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataDAC4() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataDAC4", query = "SELECT "
        + "prs.id, prs.so_tien_dac_4 AS soTien, prs.hop_dong_dac_4 AS hopDong, "
        + "prs.muc_tieu_dac_4 AS mucTieu, prs.thuc_te_dac_4 AS thucTe, prs.note_dac_4 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataDAC5() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataDAC5", query = "SELECT "
        + "prs.id, prs.so_tien_dac_5 AS soTien, prs.hop_dong_dac_5 AS hopDong, "
        + "prs.muc_tieu_dac_5 AS mucTieu, prs.thuc_te_dac_5 AS thucTe, prs.note_dac_5 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataPAC2() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataPAC2", query = "SELECT "
        + "prs.id, prs.so_tien_pac_2 AS soTien, prs.hop_dong_pac_2 AS hopDong, "
        + "prs.muc_tieu_pac_2 AS mucTieu, prs.thuc_te_pac_2 AS thucTe, prs.note_pac_2 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataPAC3() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataPAC3", query = "SELECT "
        + "prs.id, prs.so_tien_pac_3 AS soTien, prs.hop_dong_pac_3 AS hopDong, "
        + "prs.muc_tieu_pac_3 AS mucTieu, prs.thuc_te_pac_3 AS thucTe, prs.note_pac_3 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataPAC4() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataPAC4", query = "SELECT "
        + "prs.id, prs.so_tien_pac_4 AS soTien, prs.hop_dong_pac_4 AS hopDong, "
        + "prs.muc_tieu_pac_4 AS mucTieu, prs.thuc_te_pac_4 AS thucTe, prs.note_pac_4 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataPAC5() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataPAC5", query = "SELECT "
        + "prs.id, prs.so_tien_pac_5 AS soTien, prs.hop_dong_pac_5 AS hopDong, "
        + "prs.muc_tieu_pac_5 AS mucTieu, prs.thuc_te_pac_5 AS thucTe, prs.note_pac_5 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataFAC2() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataFAC2", query = "SELECT "
        + "prs.id, prs.so_tien_fac_2 AS soTien, prs.hop_dong_fac_2 AS hopDong, "
        + "prs.muc_tieu_fac_2 AS mucTieu, prs.thuc_te_fac_2 AS thucTe, prs.note_fac_2 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataFAC3() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataFAC3", query = "SELECT "
        + "prs.id, prs.so_tien_fac_3 AS soTien, prs.hop_dong_fac_3 AS hopDong, "
        + "prs.muc_tieu_fac_3 AS mucTieu, prs.thuc_te_fac_3 AS thucTe, prs.note_fac_3 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataFAC4() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataFAC4", query = "SELECT "
        + "prs.id, prs.so_tien_fac_4 AS soTien, prs.hop_dong_fac_4 AS hopDong, "
        + "prs.muc_tieu_fac_4 AS mucTieu, prs.thuc_te_fac_4 AS thucTe, prs.note_fac_4 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== ProjectReportSubdataEntity.findSubdataFAC5() ===== */
@NamedNativeQuery(name = "ProjectReportSubdataEntity.findSubdataFAC5", query = "SELECT "
        + "prs.id, prs.so_tien_fac_5 AS soTien, prs.hop_dong_fac_5 AS hopDong, "
        + "prs.muc_tieu_fac_5 AS mucTieu, prs.thuc_te_fac_5 AS thucTe, prs.note_fac_5 AS note "
        + "FROM project_report_subdata AS prs "
        + "WHERE prs.project_report_id = :projectReportId", resultSetMapping = "Mapping.SupportCptgLessDTO")

/* ===== Set mapping: SupportCptgLessDTO ===== */
@SqlResultSetMapping(name = "Mapping.SupportCptgLessDTO", classes = @ConstructorResult(targetClass = SupportCptgLessDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "soTien", type = String.class),
        @ColumnResult(name = "hopDong", type = String.class),
        @ColumnResult(name = "mucTieu", type = String.class),
        @ColumnResult(name = "thucTe", type = String.class),
        @ColumnResult(name = "note", type = String.class) }))

@Entity
@Table(name = "project_report_subdata")
public class ProjectReportSubdataEntity extends BaseEntity {

    // Số tiền
    @Column(name = "so_tien_dac_2")
    private String soTienDac2;
    @Column(name = "so_tien_dac_3")
    private String soTienDac3;
    @Column(name = "so_tien_dac_4")
    private String soTienDac4;
    @Column(name = "so_tien_dac_5")
    private String soTienDac5;

    @Column(name = "so_tien_pac_2")
    private String soTienPac2;
    @Column(name = "so_tien_pac_3")
    private String soTienPac3;
    @Column(name = "so_tien_pac_4")
    private String soTienPac4;
    @Column(name = "so_tien_pac_5")
    private String soTienPac5;

    @Column(name = "so_tien_fac_2")
    private String soTienFac2;
    @Column(name = "so_tien_fac_3")
    private String soTienFac3;
    @Column(name = "so_tien_fac_4")
    private String soTienFac4;
    @Column(name = "so_tien_fac_5")
    private String soTienFac5;
    // Hợp đồng
    @Column(name = "hop_dong_dac_2")
    private String hopDongDac2;
    @Column(name = "hop_dong_dac_3")
    private String hopDongDac3;
    @Column(name = "hop_dong_dac_4")
    private String hopDongDac4;
    @Column(name = "hop_dong_dac_5")
    private String hopDongDac5;

    @Column(name = "hop_dong_pac_2")
    private String hopDongPac2;
    @Column(name = "hop_dong_pac_3")
    private String hopDongPac3;
    @Column(name = "hop_dong_pac_4")
    private String hopDongPac4;
    @Column(name = "hop_dong_pac_5")
    private String hopDongPac5;

    @Column(name = "hop_dong_fac_2")
    private String hopDongFac2;
    @Column(name = "hop_dong_fac_3")
    private String hopDongFac3;
    @Column(name = "hop_dong_fac_4")
    private String hopDongFac4;
    @Column(name = "hop_dong_fac_5")
    private String hopDongFac5;
    // Mục tiêu
    @Column(name = "muc_tieu_dac_2")
    private String mucTieuDac2;
    @Column(name = "muc_tieu_dac_3")
    private String mucTieuDac3;
    @Column(name = "muc_tieu_dac_4")
    private String mucTieuDac4;
    @Column(name = "muc_tieu_dac_5")
    private String mucTieuDac5;

    @Column(name = "muc_tieu_pac_2")
    private String mucTieuPac2;
    @Column(name = "muc_tieu_pac_3")
    private String mucTieuPac3;
    @Column(name = "muc_tieu_pac_4")
    private String mucTieuPac4;
    @Column(name = "muc_tieu_pac_5")
    private String mucTieuPac5;

    @Column(name = "muc_tieu_fac_2")
    private String mucTieuFac2;
    @Column(name = "muc_tieu_fac_3")
    private String mucTieuFac3;
    @Column(name = "muc_tieu_fac_4")
    private String mucTieuFac4;
    @Column(name = "muc_tieu_fac_5")
    private String mucTieuFac5;
    // Thực tế
    @Column(name = "thuc_te_dac_2")
    private String thucTeDac2;
    @Column(name = "thuc_te_dac_3")
    private String thucTeDac3;
    @Column(name = "thuc_te_dac_4")
    private String thucTeDac4;
    @Column(name = "thuc_te_dac_5")
    private String thucTeDac5;

    @Column(name = "thuc_te_pac_2")
    private String thucTePac2;
    @Column(name = "thuc_te_pac_3")
    private String thucTePac3;
    @Column(name = "thuc_te_pac_4")
    private String thucTePac4;
    @Column(name = "thuc_te_pac_5")
    private String thucTePac5;

    @Column(name = "thuc_te_fac_2")
    private String thucTeFac2;
    @Column(name = "thuc_te_fac_3")
    private String thucTeFac3;
    @Column(name = "thuc_te_fac_4")
    private String thucTeFac4;
    @Column(name = "thuc_te_fac_5")
    private String thucTeFac5;
    // Note
    @Column(name = "note_dac_2")
    private String noteDac2;
    @Column(name = "note_dac_3")
    private String noteDac3;
    @Column(name = "note_dac_4")
    private String noteDac4;
    @Column(name = "note_dac_5")
    private String noteDac5;

    @Column(name = "note_pac_2")
    private String notePac2;
    @Column(name = "note_pac_3")
    private String notePac3;
    @Column(name = "note_pac_4")
    private String notePac4;
    @Column(name = "note_pac_5")
    private String notePac5;

    @Column(name = "note_fac_2")
    private String noteFac2;
    @Column(name = "note_fac_3")
    private String noteFac3;
    @Column(name = "note_fac_4")
    private String noteFac4;
    @Column(name = "note_fac_5")
    private String noteFac5;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_report_id", referencedColumnName = "id")
    private ProjectReportEntity projectReport;

    public String getSoTienDac2() {
        return this.soTienDac2;
    }

    public void setSoTienDac2(String soTienDac2) {
        this.soTienDac2 = soTienDac2;
    }

    public String getSoTienDac3() {
        return this.soTienDac3;
    }

    public void setSoTienDac3(String soTienDac3) {
        this.soTienDac3 = soTienDac3;
    }

    public String getSoTienDac4() {
        return this.soTienDac4;
    }

    public void setSoTienDac4(String soTienDac4) {
        this.soTienDac4 = soTienDac4;
    }

    public String getSoTienDac5() {
        return this.soTienDac5;
    }

    public void setSoTienDac5(String soTienDac5) {
        this.soTienDac5 = soTienDac5;
    }

    public String getSoTienPac2() {
        return this.soTienPac2;
    }

    public void setSoTienPac2(String soTienPac2) {
        this.soTienPac2 = soTienPac2;
    }

    public String getSoTienPac3() {
        return this.soTienPac3;
    }

    public void setSoTienPac3(String soTienPac3) {
        this.soTienPac3 = soTienPac3;
    }

    public String getSoTienPac4() {
        return this.soTienPac4;
    }

    public void setSoTienPac4(String soTienPac4) {
        this.soTienPac4 = soTienPac4;
    }

    public String getSoTienPac5() {
        return this.soTienPac5;
    }

    public void setSoTienPac5(String soTienPac5) {
        this.soTienPac5 = soTienPac5;
    }

    public String getSoTienFac2() {
        return this.soTienFac2;
    }

    public void setSoTienFac2(String soTienFac2) {
        this.soTienFac2 = soTienFac2;
    }

    public String getSoTienFac3() {
        return this.soTienFac3;
    }

    public void setSoTienFac3(String soTienFac3) {
        this.soTienFac3 = soTienFac3;
    }

    public String getSoTienFac4() {
        return this.soTienFac4;
    }

    public void setSoTienFac4(String soTienFac4) {
        this.soTienFac4 = soTienFac4;
    }

    public String getSoTienFac5() {
        return this.soTienFac5;
    }

    public void setSoTienFac5(String soTienFac5) {
        this.soTienFac5 = soTienFac5;
    }

    public String getHopDongDac2() {
        return this.hopDongDac2;
    }

    public void setHopDongDac2(String hopDongDac2) {
        this.hopDongDac2 = hopDongDac2;
    }

    public String getHopDongDac3() {
        return this.hopDongDac3;
    }

    public void setHopDongDac3(String hopDongDac3) {
        this.hopDongDac3 = hopDongDac3;
    }

    public String getHopDongDac4() {
        return this.hopDongDac4;
    }

    public void setHopDongDac4(String hopDongDac4) {
        this.hopDongDac4 = hopDongDac4;
    }

    public String getHopDongDac5() {
        return this.hopDongDac5;
    }

    public void setHopDongDac5(String hopDongDac5) {
        this.hopDongDac5 = hopDongDac5;
    }

    public String getHopDongPac2() {
        return this.hopDongPac2;
    }

    public void setHopDongPac2(String hopDongPac2) {
        this.hopDongPac2 = hopDongPac2;
    }

    public String getHopDongPac3() {
        return this.hopDongPac3;
    }

    public void setHopDongPac3(String hopDongPac3) {
        this.hopDongPac3 = hopDongPac3;
    }

    public String getHopDongPac4() {
        return this.hopDongPac4;
    }

    public void setHopDongPac4(String hopDongPac4) {
        this.hopDongPac4 = hopDongPac4;
    }

    public String getHopDongPac5() {
        return this.hopDongPac5;
    }

    public void setHopDongPac5(String hopDongPac5) {
        this.hopDongPac5 = hopDongPac5;
    }

    public String getHopDongFac2() {
        return this.hopDongFac2;
    }

    public void setHopDongFac2(String hopDongFac2) {
        this.hopDongFac2 = hopDongFac2;
    }

    public String getHopDongFac3() {
        return this.hopDongFac3;
    }

    public void setHopDongFac3(String hopDongFac3) {
        this.hopDongFac3 = hopDongFac3;
    }

    public String getHopDongFac4() {
        return this.hopDongFac4;
    }

    public void setHopDongFac4(String hopDongFac4) {
        this.hopDongFac4 = hopDongFac4;
    }

    public String getHopDongFac5() {
        return this.hopDongFac5;
    }

    public void setHopDongFac5(String hopDongFac5) {
        this.hopDongFac5 = hopDongFac5;
    }

    public String getMucTieuDac2() {
        return this.mucTieuDac2;
    }

    public void setMucTieuDac2(String mucTieuDac2) {
        this.mucTieuDac2 = mucTieuDac2;
    }

    public String getMucTieuDac3() {
        return this.mucTieuDac3;
    }

    public void setMucTieuDac3(String mucTieuDac3) {
        this.mucTieuDac3 = mucTieuDac3;
    }

    public String getMucTieuDac4() {
        return this.mucTieuDac4;
    }

    public void setMucTieuDac4(String mucTieuDac4) {
        this.mucTieuDac4 = mucTieuDac4;
    }

    public String getMucTieuDac5() {
        return this.mucTieuDac5;
    }

    public void setMucTieuDac5(String mucTieuDac5) {
        this.mucTieuDac5 = mucTieuDac5;
    }

    public String getMucTieuPac2() {
        return this.mucTieuPac2;
    }

    public void setMucTieuPac2(String mucTieuPac2) {
        this.mucTieuPac2 = mucTieuPac2;
    }

    public String getMucTieuPac3() {
        return this.mucTieuPac3;
    }

    public void setMucTieuPac3(String mucTieuPac3) {
        this.mucTieuPac3 = mucTieuPac3;
    }

    public String getMucTieuPac4() {
        return this.mucTieuPac4;
    }

    public void setMucTieuPac4(String mucTieuPac4) {
        this.mucTieuPac4 = mucTieuPac4;
    }

    public String getMucTieuPac5() {
        return this.mucTieuPac5;
    }

    public void setMucTieuPac5(String mucTieuPac5) {
        this.mucTieuPac5 = mucTieuPac5;
    }

    public String getMucTieuFac2() {
        return this.mucTieuFac2;
    }

    public void setMucTieuFac2(String mucTieuFac2) {
        this.mucTieuFac2 = mucTieuFac2;
    }

    public String getMucTieuFac3() {
        return this.mucTieuFac3;
    }

    public void setMucTieuFac3(String mucTieuFac3) {
        this.mucTieuFac3 = mucTieuFac3;
    }

    public String getMucTieuFac4() {
        return this.mucTieuFac4;
    }

    public void setMucTieuFac4(String mucTieuFac4) {
        this.mucTieuFac4 = mucTieuFac4;
    }

    public String getMucTieuFac5() {
        return this.mucTieuFac5;
    }

    public void setMucTieuFac5(String mucTieuFac5) {
        this.mucTieuFac5 = mucTieuFac5;
    }

    public String getThucTeDac2() {
        return this.thucTeDac2;
    }

    public void setThucTeDac2(String thucTeDac2) {
        this.thucTeDac2 = thucTeDac2;
    }

    public String getThucTeDac3() {
        return this.thucTeDac3;
    }

    public void setThucTeDac3(String thucTeDac3) {
        this.thucTeDac3 = thucTeDac3;
    }

    public String getThucTeDac4() {
        return this.thucTeDac4;
    }

    public void setThucTeDac4(String thucTeDac4) {
        this.thucTeDac4 = thucTeDac4;
    }

    public String getThucTeDac5() {
        return this.thucTeDac5;
    }

    public void setThucTeDac5(String thucTeDac5) {
        this.thucTeDac5 = thucTeDac5;
    }

    public String getThucTePac2() {
        return this.thucTePac2;
    }

    public void setThucTePac2(String thucTePac2) {
        this.thucTePac2 = thucTePac2;
    }

    public String getThucTePac3() {
        return this.thucTePac3;
    }

    public void setThucTePac3(String thucTePac3) {
        this.thucTePac3 = thucTePac3;
    }

    public String getThucTePac4() {
        return this.thucTePac4;
    }

    public void setThucTePac4(String thucTePac4) {
        this.thucTePac4 = thucTePac4;
    }

    public String getThucTePac5() {
        return this.thucTePac5;
    }

    public void setThucTePac5(String thucTePac5) {
        this.thucTePac5 = thucTePac5;
    }

    public String getThucTeFac2() {
        return this.thucTeFac2;
    }

    public void setThucTeFac2(String thucTeFac2) {
        this.thucTeFac2 = thucTeFac2;
    }

    public String getThucTeFac3() {
        return this.thucTeFac3;
    }

    public void setThucTeFac3(String thucTeFac3) {
        this.thucTeFac3 = thucTeFac3;
    }

    public String getThucTeFac4() {
        return this.thucTeFac4;
    }

    public void setThucTeFac4(String thucTeFac4) {
        this.thucTeFac4 = thucTeFac4;
    }

    public String getThucTeFac5() {
        return this.thucTeFac5;
    }

    public void setThucTeFac5(String thucTeFac5) {
        this.thucTeFac5 = thucTeFac5;
    }

    public String getNoteDac2() {
        return this.noteDac2;
    }

    public void setNoteDac2(String noteDac2) {
        this.noteDac2 = noteDac2;
    }

    public String getNoteDac3() {
        return this.noteDac3;
    }

    public void setNoteDac3(String noteDac3) {
        this.noteDac3 = noteDac3;
    }

    public String getNoteDac4() {
        return this.noteDac4;
    }

    public void setNoteDac4(String noteDac4) {
        this.noteDac4 = noteDac4;
    }

    public String getNoteDac5() {
        return this.noteDac5;
    }

    public void setNoteDac5(String noteDac5) {
        this.noteDac5 = noteDac5;
    }

    public String getNotePac2() {
        return this.notePac2;
    }

    public void setNotePac2(String notePac2) {
        this.notePac2 = notePac2;
    }

    public String getNotePac3() {
        return this.notePac3;
    }

    public void setNotePac3(String notePac3) {
        this.notePac3 = notePac3;
    }

    public String getNotePac4() {
        return this.notePac4;
    }

    public void setNotePac4(String notePac4) {
        this.notePac4 = notePac4;
    }

    public String getNotePac5() {
        return this.notePac5;
    }

    public void setNotePac5(String notePac5) {
        this.notePac5 = notePac5;
    }

    public String getNoteFac2() {
        return this.noteFac2;
    }

    public void setNoteFac2(String noteFac2) {
        this.noteFac2 = noteFac2;
    }

    public String getNoteFac3() {
        return this.noteFac3;
    }

    public void setNoteFac3(String noteFac3) {
        this.noteFac3 = noteFac3;
    }

    public String getNoteFac4() {
        return this.noteFac4;
    }

    public void setNoteFac4(String noteFac4) {
        this.noteFac4 = noteFac4;
    }

    public String getNoteFac5() {
        return this.noteFac5;
    }

    public void setNoteFac5(String noteFac5) {
        this.noteFac5 = noteFac5;
    }

    public ProjectReportEntity getProjectReport() {
        return this.projectReport;
    }

    public void setProjectReport(ProjectReportEntity projectReport) {
        this.projectReport = projectReport;
    }

}
