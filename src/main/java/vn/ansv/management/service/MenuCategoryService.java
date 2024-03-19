package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;
import vn.ansv.management.dto.Layout.LayoutMenuDTO;
import vn.ansv.management.repository.MenuCategoryRepository;
import vn.ansv.management.repository.MenuRepository;
import vn.ansv.management.repository.UserRepository;
import vn.ansv.management.service.Interface.IMenuCategory;

@Service
public class MenuCategoryService implements IMenuCategory {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<LayoutMenuCategoryDTO> findAllLayout(String roleName, int week, int year) {
        List<LayoutMenuCategoryDTO> category = menuCategoryRepository.findAllLayout();

        category.forEach((categoryItem) -> {
            try {
                if (!categoryItem.getMenuCategoryName().equals("PIC")) {
                    // Menu cấp 1
                    List<LayoutMenuDTO> menuLayoutByCategory1 = menuRepository.findAllLayoutLevel1ByRole(
                            categoryItem.getId(), 1, roleName);
                    menuLayoutByCategory1.forEach((menuItem1) -> {
                        if (menuItem1.getLevel() == 1) {
                            try {
                                // Menu cấp 2 với menu cấp 1 tương ứng
                                List<LayoutMenuDTO> menuLayoutByCategory2 = menuRepository.findAllLayoutLevel2ByRole(
                                        menuItem1.getId(), 2, roleName);
                                menuItem1.setMenuChild(menuLayoutByCategory2);
                            } catch (NullPointerException e) {
                                System.out.println("");
                                System.out.println(
                                        "NullPointerException (MenuCategoryService - line 40) ===== Menu (level 1) with id "
                                                + menuItem1.getId() + " has no include.");
                                System.out.println("");
                            }
                        }
                    });
                    categoryItem.setMenuItem(menuLayoutByCategory1);
                } else {
                    // Menu cấp 1: PIC and count report
                    List<LayoutMenuDTO> menuPicLayout = userRepository.findAllPicLayout(week, year);
                    // menuPicLayout.forEach((menuItem1) -> {
                    //     try {
                    //         List<LayoutMenuDTO> menuPicReportLayout = menuRepository.findAllLayoutLevel2ByRole(
                    //                 menuItem1.getId(), 2, roleName);
                    //         menuItem1.setMenuChild(menuPicReportLayout);
                    //     } catch (NullPointerException e) {
                    //         System.out.println("");
                    //         System.out.println(
                    //                 "NullPointerException (MenuCategoryService - line 40) ===== Menu (level 1) with id "
                    //                         + menuItem1.getId() + " has no include.");
                    //         System.out.println("");
                    //     }
                    // });
                    categoryItem.setMenuItem(menuPicLayout);
                }
            } catch (NullPointerException e) {
                System.out.println("");
                System.out.println("NullPointerException (MenuCategoryService - line 47) ===== Category with id "
                        + categoryItem.getId()
                        + " has no include menu.");
                System.out.println("");
            }
        });

        return category;
    }
}
