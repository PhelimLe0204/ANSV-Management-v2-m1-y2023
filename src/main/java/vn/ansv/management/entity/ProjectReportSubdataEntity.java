package vn.ansv.management.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
}
