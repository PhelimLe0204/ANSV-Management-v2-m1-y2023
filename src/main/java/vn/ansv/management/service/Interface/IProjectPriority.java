package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;

public interface IProjectPriority {
    List<OptionProjectPriorityDTO> findAllOption();
}
