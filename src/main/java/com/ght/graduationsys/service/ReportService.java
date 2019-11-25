package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Report;

import java.util.List;

public interface ReportService {
    public void addReport(Report report);
    public List<Report> getReportsByTid(String tid);
    public List<Report> getCheckedReportsByTid(String tid);
    public List<Report> getUncheckedReportsByTid(String tid);
    public List<Report> getReportsBySid(String sid);
    public List<Report> getCheckedReportsBySid(String sid);
    public List<Report> getUncheckedReportsBySid(String sid);
    public void updateReportById(String id,String msg);
}
