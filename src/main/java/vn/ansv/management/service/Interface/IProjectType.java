package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.selectOption.OptionProjectTypeDTO;

public interface IProjectType {
    List<OptionProjectTypeDTO> findAllOption();
}
