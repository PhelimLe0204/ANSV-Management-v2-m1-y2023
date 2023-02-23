package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class PositionEntity extends BaseEntity {
    @Column(name = "position_name", nullable = false)
    private String positionName;

    @Column(name = "position_explain")
    private String positionExplain;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "level")
    private Integer level;

    /*
     * @ManyToMany(mappedBy =
     * "tên biến hứng dữ liệu từ PositionEntity trong UserEntity (positions)")
     */
    @ManyToMany(mappedBy = "positions")
    // 1 'position' gồm nhiều 'user' => dùng List để hứng mảng dữ liệu
    private List<UserEntity> users = new ArrayList<>();

    public String getPositionName() {
        return this.positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionExplain() {
        return this.positionExplain;
    }

    public void setPositionExplain(String positionExplain) {
        this.positionExplain = positionExplain;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
