package com.athome.mapper;

import com.athome.pojo.Announcement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    //添加公告
    @Insert("insert into announcement (title, content, admin_id) values (#{title},#{content},#{adminId});")
    void add(Announcement announcement);

    @Select("select * from announcement;")
    List<Announcement> list();

    @Update("update announcement set title = #{title},content = #{content} where id=#{id};")
    void update(Announcement announcement);

    @Delete("delete from announcement where id=#{id};")
    void delete(Integer id);
}
