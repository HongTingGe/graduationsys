package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.ReportMapper;
import com.ght.graduationsys.entity.Report;
import com.ght.graduationsys.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public void addReport(Report report) {
        reportMapper.addReport(report);
    }

    @Override
    public List<Report> getReportsByTid(String tid) {
        return reportMapper.getReportsByTid(tid);
    }

    @Override
    public List<Report> getCheckedReportsByTid(String tid) {
        return reportMapper.getCheckedReportsByTid(tid);
    }

    @Override
    public List<Report> getUncheckedReportsByTid(String tid) {
        return reportMapper.getUncheckedReportsByTid(tid);
    }

    @Override
    public List<Report> getReportsBySid(String sid) {
        return reportMapper.getReportsBySid(sid);
    }

    @Override
    public List<Report> getCheckedReportsBySid(String sid) {
        return reportMapper.getCheckedReportsBySid(sid);
    }

    @Override
    public List<Report> getUncheckedReportsBySid(String sid) {
        return reportMapper.getUncheckedReportsBySid(sid);
    }

    @Override
    public void updateReportById(String id,String msg) {
        reportMapper.updateReportById(id,msg);
    }
}
