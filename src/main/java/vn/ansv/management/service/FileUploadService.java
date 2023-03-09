package vn.ansv.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.repository.FileUploadRepository;
import vn.ansv.management.service.Interface.IFileUpload;

@Service
public class FileUploadService implements IFileUpload {

    @Autowired
    FileUploadRepository fileUploadRepository;

    @Override
    public Integer deleteFileUploadByProjectReportId(Long project_report_id) {
        // 0 - Thất bại, 1 - Thành công, 2 - Bản ghi không tồn tại
        try {
            // Kiểm tra bản ghi có tồn tại không
            int count = fileUploadRepository.checkFileUploadByProjectReportId(project_report_id);

            if (count == 0) {
                return 2;
            }

            fileUploadRepository.deleteFileUploadByProjectReportId(project_report_id);
            return 1;
        } catch (Exception e) {
            System.out.println("----- Error ----- : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
