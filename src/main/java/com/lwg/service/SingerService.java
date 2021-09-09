package com.lwg.service;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Music;
import com.lwg.domain.Singer;
import com.lwg.domain.Singertype;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SingerService {
    PageInfo<Singer> getSingerByPage(int pageNO,String singername);
    List<Singertype> getAllType();
    int saveSinger(Singer singer, MultipartFile[] myFile);
    int upDateSinger(Singer singer, MultipartFile[] myFile);
    int deleteSinger(int singerid);
    int deleteAllSinger(int[] singers);
    List<Singer> getAll();
}
