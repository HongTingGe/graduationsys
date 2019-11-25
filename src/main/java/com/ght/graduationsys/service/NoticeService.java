package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Notice;

import java.util.List;

public interface NoticeService {
    public void addNotice(Notice notice);
    public List<Notice> getNoticesByTid(String tid);
    public Notice getNoticeById(int id);
    public void updateNoticeById(int id);
    public void deleteNoticeById(int id);
    public List<Notice> getAllNotices();
}
