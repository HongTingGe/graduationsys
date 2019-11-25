package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportMapper {
    public void addReport(Report report);
    public List<Report> getReportsByTid(String tid);
    public List<Report> getCheckedReportsByTid(String tid);
    public List<Report> getUncheckedReportsByTid(String tid);
    public List<Report> getReportsBySid(String sid);
    public List<Report> getCheckedReportsBySid(String sid);
    public List<Report> getUncheckedReportsBySid(String sid);
    public void updateReportById(@Param("id") String id,@Param("msg") String msg);
}
