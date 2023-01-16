package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.selectOption.OptionProjectStatusDTO;

public interface IProjectStatus {
    List<OptionProjectStatusDTO> findAllOption();
}
