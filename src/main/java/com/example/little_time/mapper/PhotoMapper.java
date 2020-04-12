package com.example.little_time.mapper;

import com.example.little_time.bean.Photo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface PhotoMapper {

    @Insert("Insert into photo(pmid,uid,photoName,photoPath,num) values(#{pmid},#{uid},#{photoName},#{photoPath},#{num})")
    public Boolean uploadPhoto(Photo photo) throws Exception;

    @Select("select photoPath from photo where pmid=#{pmid} and num=#{num}")
    public String loadingPhoto(Integer pmid, Integer num) throws Exception;

    @Select("select count(*) from photo where pmid=#{pmid} and num=#{num}")
    public Boolean findPhoto(Integer pmid, Integer num) throws Exception;
}
