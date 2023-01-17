package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.selectOption.OptionProjectDTO;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.service.Interface.IProject;

@Service
public class ProjectService implements IProject {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<OptionProjectDTO> findAllSelectOption() {
        return projectRepository.findAllSelectOption();
    }
}
