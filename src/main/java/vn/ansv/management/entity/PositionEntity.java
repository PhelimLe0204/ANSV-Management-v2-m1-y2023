package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
