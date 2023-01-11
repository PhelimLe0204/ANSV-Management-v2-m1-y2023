package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.Layout.LayoutMenuDTO;

@NamedNativeQuery(name = "MenuEntity.findAllLayoutLevel1", query = "SELECT "
        + "m.id, m.display AS menuName, m.icon, m.include, m.level, m.link "
        + "FROM menu AS m "
        + "WHERE m.menu_category_id = :menu_category_id AND m.level = :level AND m.enabled = 1", resultSetMapping = "Mapping.LayoutMenuDTO")

@NamedNativeQuery(name = "MenuEntity.findAllLayoutLevel2", query = "SELECT "
        + "m.id, m.display AS menuName, m.icon, m.include, m.level, m.link "
        + "FROM menu AS m "
        + "WHERE m.previous = :previous AND m.level = :level AND m.enabled = 1", resultSetMapping = "Mapping.LayoutMenuDTO")

@SqlResultSetMapping(name = "Mapping.LayoutMenuDTO", classes = @ConstructorResult(targetClass = LayoutMenuDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "menuName", type = String.class),
        @ColumnResult(name = "icon", type = String.class),
        @ColumnResult(name = "include", type = Integer.class),
        @ColumnResult(name = "level", type = Integer.class),
        @ColumnResult(name = "link", type = String.class) }))

@Entity
@Table(name = "menu")
public class MenuEntity extends BaseEntity {
    // @Column(name = "menu_category_id", nullable = false)
    // private Long menuCategoryId;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "previous")
    private Integer previous;

    @Column(name = "display", nullable = false)
    private String display;

    @Column(name = "icon")
    private String icon;

    @Column(name = "link")
    private String link;

    @Column(name = "include")
    private Integer include;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(4)")
    private Integer enabled;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'menu' kết nối với bảng 'menu_category' (menu_category_id)"
     * )
     */
    @JoinColumn(name = "menu_category_id")
    private MenuCategoryEntity menuCategory; // 1 'menu' sử dụng 1 'menu_category_id' => hứng 1 bản ghi

    // public Long getMenuCategoryId() {
    // return this.menuCategoryId;
    // }
    // public void setMenuCategoryId(Long menuCategoryId) {
    // this.menuCategoryId = menuCategoryId;
    // }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPrevious() {
        return this.previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getInclude() {
        return this.include;
    }

    public void setInclude(Integer include) {
        this.include = include;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public MenuCategoryEntity getMenuCategory() {
        return this.menuCategory;
    }

    public void setMenuCategory(MenuCategoryEntity menuCategory) {
        this.menuCategory = menuCategory;
    }

}
