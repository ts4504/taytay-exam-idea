package com.athome.service.impl;

import com.athome.mapper.AnnouncementMapper;
import com.athome.pojo.Announcement;
import com.athome.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public void add(Announcement announcement) {
        announcementMapper.add(announcement);
    }

    @Override
    public List<Announcement> list() {
        return announcementMapper.list();
    }

    @Override
    public void update(Announcement announcement) {
        announcementMapper.update(announcement);
    }

    @Override
    public void delete(Integer id) {
        announcementMapper.delete(id);
    }
}
