package com.lwg.dao;

import com.lwg.domain.Musictype;
import com.lwg.domain.MusictypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusictypeMapper {
    int countByExample(MusictypeExample example);

    int deleteByExample(MusictypeExample example);

    int deleteByPrimaryKey(Integer musictypeid);

    int insert(Musictype record);

    int insertSelective(Musictype record);

    List<Musictype> selectByExample(MusictypeExample example);

    Musictype selectByPrimaryKey(Integer musictypeid);

    int updateByExampleSelective(@Param("record") Musictype record, @Param("example") MusictypeExample example);

    int updateByExample(@Param("record") Musictype record, @Param("example") MusictypeExample example);

    int updateByPrimaryKeySelective(Musictype record);

    int updateByPrimaryKey(Musictype record);
}