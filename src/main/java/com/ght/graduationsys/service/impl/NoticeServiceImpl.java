package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.NoticeMapper;
import com.ght.graduationsys.entity.Notice;
import com.ght.graduationsys.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.addNotice(notice);
    }

    @Override
    public List<Notice> getNoticesByTid(String tid) {
        return noticeMapper.getNoticesByTid(tid);
    }

    @Override
    public Notice getNoticeById(int id) {
        return noticeMapper.getNoticeById(id);
    }

    @Override
    public void updateNoticeById(int id) {
        noticeMapper.updateNoticeById(id);
    }

    @Override
    public void deleteNoticeById(int id) {
        noticeMapper.deleteNoticeById(id);
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeMapper.getAllNotices();
    }
}
