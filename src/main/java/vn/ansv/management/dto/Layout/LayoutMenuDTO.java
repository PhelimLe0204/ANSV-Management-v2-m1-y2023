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
    private String linkSub;
    private Integer dataCount;
    private String note;
    private Object menuChild;

    public LayoutMenuDTO(Long id, String menuName, String icon, Integer include, Integer level, String link,
            String linkSub, Integer dataCount, String note) {
        this.id = id;
        this.menuName = menuName;
        this.icon = icon;
        this.include = include;
        this.level = level;
        this.link = link;
        this.linkSub = linkSub;
        this.dataCount = dataCount;
        // this.note = note;
        if (note != null && note.contains("NONE")) {
            this.note = "link-none";
        } else {
            this.note = null;
        }
    }
}
