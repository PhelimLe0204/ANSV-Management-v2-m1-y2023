package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;

public interface IMenuCategory {
    List<LayoutMenuCategoryDTO> findAllLayout();
}
