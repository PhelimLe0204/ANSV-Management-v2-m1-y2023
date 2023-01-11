package vn.ansv.management.dto.Layout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LayoutMenuCategoryDTO {
    private Long id;
    private String menuCategoryName;
    private Object menuItem;

    public LayoutMenuCategoryDTO(Long id, String menuCategoryName) {
        this.id = id;
        this.menuCategoryName = menuCategoryName;
    }
}
