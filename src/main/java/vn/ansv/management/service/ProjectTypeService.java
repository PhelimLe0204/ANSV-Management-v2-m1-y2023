package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.selectOption.OptionProjectTypeDTO;
import vn.ansv.management.repository.ProjectTypeRepository;
import vn.ansv.management.service.Interface.IProjectType;

@Service
public class ProjectTypeService implements IProjectType {

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Override
    public List<OptionProjectTypeDTO> findAllOption() {
        return projectTypeRepository.findAllOption();
    }

}
