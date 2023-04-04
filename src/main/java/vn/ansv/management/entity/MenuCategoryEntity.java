package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;

@NamedNativeQuery(name = "MenuCategoryEntity.findAllLayout", query = "SELECT "
        + "mc.id, mc.menu_category_name AS menuCategoryName "
        + "FROM menu_category AS mc", resultSetMapping = "Mapping.LayoutMenuCategoryDTO")
@SqlResultSetMapping(name = "Mapping.LayoutMenuCategoryDTO", classes = @ConstructorResult(targetClass = LayoutMenuCategoryDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "menuCategoryName", type = String.class) }))

@Entity
@Table(name = "menu_category")
public class MenuCategoryEntity extends BaseEntity {
    @Column(name = "menu_category_name", nullable = false)
    private String menuCategoryName;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ MenuCategoryEntity trong MenuEntity (menuCategory)"
     * )
     */
    @OneToMany(mappedBy = "menuCategory")
    /*
     * 1 'menu_category' nằm trong nhiều 'menu'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<MenuEntity> menus = new ArrayList<>();

    public String getMenuCategoryName() {
        return this.menuCategoryName;
    }

    public void setMenuCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<MenuEntity> getMenus() {
        return this.menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }

}
