package com.athome.service;

import com.athome.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {
    /**
     * 添加公告
     * @param announcement
     */
    void add(Announcement announcement);

    /**
     * 获取公告列表
     * @return
     */
    List<Announcement> list();

    /**
     * 更新公告
     * @param announcement
     * @return
     */
    void update(Announcement announcement);

    /**
     * 删除公告
     * @param id
     * @return
     */
    void delete(Integer id);
}
