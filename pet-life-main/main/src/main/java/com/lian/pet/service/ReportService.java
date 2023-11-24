package com.lian.pet.service;

import com.lian.pet.domain.dto.AddReportDTO;


public interface ReportService {

    /**
     * 添加举报
     * @param req
     */
    void addReport(AddReportDTO req);

}
