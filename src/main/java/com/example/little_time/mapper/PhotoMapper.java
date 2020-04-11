package com.example.little_time.mapper;

import com.example.little_time.bean.Photo;
import org.apache.ibatis.annotations.Insert;

public interface PhotoMapper {

    @Insert("Insert into photo(pmid,uid,photoName,photoPath,num) values(#{pmid},#{uid},#{photoName},#{photoPath},#{num})")
    public Boolean uploadPhoto(Photo photo) throws Exception;
}
