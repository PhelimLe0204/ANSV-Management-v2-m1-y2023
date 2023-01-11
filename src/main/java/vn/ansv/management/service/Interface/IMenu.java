package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.entity.MenuEntity;

public interface IMenu {
    List<MenuEntity> findALLByCategory(int category);
}
