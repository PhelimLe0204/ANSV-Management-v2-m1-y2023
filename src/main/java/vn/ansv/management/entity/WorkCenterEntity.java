package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "work_center")
public class WorkCenterEntity extends BaseEntity {
    @Column(name = "center_name", nullable = false)
    private String centerName;

    @Column(name = "name_display", nullable = false)
    private String nameDisplay;

    @Column(name = "description", nullable = false)
    private String description;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ bảng hiện tại (WorkCenterEntity) trong UserEntity (workCenter)"
     * )
     */
    @OneToMany(mappedBy = "workCenter")
    /*
     * 1 'work_center' nằm trong nhiều 'user'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<UserEntity> users = new ArrayList<>();
}
