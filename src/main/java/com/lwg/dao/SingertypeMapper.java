package com.lwg.dao;

import com.lwg.domain.Singertype;
import com.lwg.domain.SingertypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SingertypeMapper {
    int countByExample(SingertypeExample example);

    int deleteByExample(SingertypeExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(Singertype record);

    int insertSelective(Singertype record);

    List<Singertype> selectByExample(SingertypeExample example);

    Singertype selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") Singertype record, @Param("example") SingertypeExample example);

    int updateByExample(@Param("record") Singertype record, @Param("example") SingertypeExample example);

    int updateByPrimaryKeySelective(Singertype record);

    int updateByPrimaryKey(Singertype record);
}