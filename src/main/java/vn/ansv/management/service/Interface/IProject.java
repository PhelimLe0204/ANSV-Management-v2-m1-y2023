package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.AddNewProjectDTO;
import vn.ansv.management.dto.selectOption.OptionProjectDTO;

public interface IProject {
    List<OptionProjectDTO> findAllSelectOption();

    Long getCustomerIdById(Long id);

    String addNewProject(AddNewProjectDTO dataInsert);
}
