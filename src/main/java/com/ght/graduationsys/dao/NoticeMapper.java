package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    public void addNotice(Notice notice);
    public List<Notice> getNoticesByTid(String tid);
    public Notice getNoticeById(int id);
    public void updateNoticeById(int id);
    public void deleteNoticeById(int id);
    public List<Notice> getAllNotices();
}
