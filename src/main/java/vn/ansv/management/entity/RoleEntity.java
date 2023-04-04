package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "note")
    private String note;

    @Column(name = "level")
    private Integer level;

    /*
     * @ManyToMany(mappedBy =
     * "tên biến hứng dữ liệu từ RoleEntity trong UserEntity (roles)")
     */
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>(); // 1 'role' thuộc nhiều 'user' => dùng List để hứng mảng dữ liệu

    @ManyToMany
    /*
     * @JoinTable(name = "tên bảng trung gian", joinColumns = @JoinColumn(name =
     * "khóa ngoại của bảng trung gian kết nối đến bảng hiện tại (role)"),
     * inverseJoinColumns = @JoinColumn(name =
     * "khóa ngoại của bảng trung gian kết nối đến bảng còn lại (menu)"))
     */
    @JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    // 1 'role' có thể truy cập nhiều 'menu' => dùng List để hứng mảng dữ liệu
    private List<MenuEntity> menus = new ArrayList<>();

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public List<UserEntity> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
