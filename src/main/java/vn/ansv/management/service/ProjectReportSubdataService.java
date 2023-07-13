package vn.ansv.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Detail.SupportCptgLessDTO;
import vn.ansv.management.repository.ProjectReportRepository;
import vn.ansv.management.repository.ProjectReportSubdataRepository;
import vn.ansv.management.service.Interface.IProjectReportSubdata;

@Service
public class ProjectReportSubdataService implements IProjectReportSubdata {

    @Autowired
    private ProjectReportRepository projectReportRepository;

    @Autowired
    private ProjectReportSubdataRepository projectReportSubdataRepository;

    @Override
    public SupportCptgLessDTO findSubdataLess(Long id, String target, Integer number) {
        SupportCptgLessDTO result = null;
        try {
            if (target.contains("DAC")) {
                switch (number) {
                    case 1:
                        result = projectReportRepository.findDataDAC(id);
                        return result;
                    case 2:
                        result = projectReportSubdataRepository.findSubdataDAC2(id);
                        return result;
                    case 3:
                        return projectReportSubdataRepository.findSubdataDAC3(id);
                    case 4:
                        return projectReportSubdataRepository.findSubdataDAC4(id);
                    case 5:
                        return projectReportSubdataRepository.findSubdataDAC5(id);
                }

            } else if (target.contains("PAC")) {
                switch (number) {
                    case 1:
                        return projectReportRepository.findDataPAC(id);
                    case 2:
                        return projectReportSubdataRepository.findSubdataPAC2(id);
                    case 3:
                        return projectReportSubdataRepository.findSubdataPAC3(id);
                    case 4:
                        return projectReportSubdataRepository.findSubdataPAC4(id);
                    case 5:
                        return projectReportSubdataRepository.findSubdataPAC5(id);
                }
            } else if (target.contains("FAC")) {
                switch (number) {
                    case 1:
                        return projectReportRepository.findDataFAC(id);
                    case 2:
                        return projectReportSubdataRepository.findSubdataFAC2(id);
                    case 3:
                        return projectReportSubdataRepository.findSubdataFAC3(id);
                    case 4:
                        return projectReportSubdataRepository.findSubdataFAC4(id);
                    case 5:
                        return projectReportSubdataRepository.findSubdataFAC5(id);
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("--- ProjectReportSubdataService - line 26: " + e.getMessage());
            return null;
        }
    }

}
