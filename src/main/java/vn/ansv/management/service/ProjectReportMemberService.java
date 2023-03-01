package vn.ansv.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Detail.ReportDetailTabThanhVienDTO;
import vn.ansv.management.dto.member.AddMemberDTO;
import vn.ansv.management.repository.ProjectReportMemberRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.service.Interface.IProjectReportMember;

@Service
public class ProjectReportMemberService implements IProjectReportMember {

    @Autowired
    private ProjectReportMemberRepository projectReportMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ReportDetailTabThanhVienDTO> findAllMemberByReport(Long id) {
        List<ReportDetailTabThanhVienDTO> data = new ArrayList<>();
        try {
            Long projectId = projectRepository.findIdByReport(id);

            data = projectReportMemberRepository.findAllMemberByReport(projectId, id);
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportMemberService.findAllMemberByReport(): " + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Boolean addMember(AddMemberDTO dataInsert) {
        try {
            projectReportMemberRepository.addMember(dataInsert.getJobAssinged(), dataInsert.getProjectId(),
                    dataInsert.getFirstReportId(), dataInsert.getUserId());
            return true;
        } catch (Exception e) {
            System.out.println("----- Error ----- ProjectReportMemberService.addMember(): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
