package com.lwg.dao;

import com.lwg.domain.Music;
import com.lwg.domain.MusicExample;
import java.util.List;

import com.lwg.domain.MusicView;
import org.apache.ibatis.annotations.Param;

public interface MusicMapper {
    int countByExample(MusicExample example);

    int deleteByExample(MusicExample example);

    int deleteByPrimaryKey(Integer musicid);

    int insert(Music record);

    int insertSelective(Music record);

    List<Music> selectByExample(MusicExample example);

    Music selectByPrimaryKey(Integer musicid);

    int updateByExampleSelective(@Param("record") Music record, @Param("example") MusicExample example);

    int updateByExample(@Param("record") Music record, @Param("example") MusicExample example);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    List<MusicView> getData();

    List<Music> getMuiscs(@Param("sname") String sname,@Param("musicName") String musicName);
}