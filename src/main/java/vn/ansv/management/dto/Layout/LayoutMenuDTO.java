package vn.ansv.management.dto.Layout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LayoutMenuDTO {
    private Long id;
    private String menuName;
    private String icon;
    private Integer include;
    private Integer level;
    private String link;
    private Object menuChild;

    public LayoutMenuDTO(Long id, String menuName, String icon, Integer include, Integer level, String link) {
        this.id = id;
        this.menuName = menuName;
        this.icon = icon;
        this.include = include;
        this.level = level;
        this.link = link;
    }
}
