package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_position")
public class UserPositionEntity {
    @Id // Primary key + not null
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column(name = "assigned_by")
    private String assignedBy;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'user_position' kết nối với bảng 'user' (user_id)"
     * )
     */
    @JoinColumn(name = "user_id")
    private UserEntity user; // 1 'user_position' sử dụng 1 'user' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'user_position' kết nối với bảng 'position' (position_id)"
     * )
     */
    @JoinColumn(name = "position_id")
    private PositionEntity position; // 1 'user_position' sử dụng 1 'position' => hứng 1 bản ghi

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignedBy() {
        return this.assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PositionEntity getPosition() {
        return this.position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

}
