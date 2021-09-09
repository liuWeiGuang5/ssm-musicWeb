package com.lwg.service;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Music;
import com.lwg.domain.MusicView;
import com.lwg.domain.Musictype;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MusicService {
    PageInfo<Music> getMusicByPage(Integer pageNO,String musicName,String singername);
    int saveMusic(Music music, MultipartFile[] myFile,String singername) throws IOException;
    List<Musictype> getAllType();
    int upDateMusic(Music music, MultipartFile[] myFile,String singername);
    int deleteMusic(int musicId);
    List<MusicView> getData();
    List<Music> getMusicsBySingerId(int sid);
    PageInfo<Music> getHotMusicByPage(int pageNo);

    PageInfo<Music> getMusicByTypeId(int typeid,int pageNo);

    List<Music> searchMusic(String searchName);

    List<Music> getAll();
}
