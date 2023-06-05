package vn.ansv.management.service.Interface;

import vn.ansv.management.dto.Detail.SupportCptgLessDTO;

public interface IProjectReportSubdata {
    SupportCptgLessDTO findSubdataLess(Long id, String target, Integer number);
}
