package com.lwg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lwg.dao.MusicMapper;
import com.lwg.dao.SingerMapper;
import com.lwg.dao.SingertypeMapper;
import com.lwg.domain.*;
import com.lwg.service.MusicService;
import com.lwg.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerMapper singerMapper;
    @Autowired
    SingertypeMapper singertypeMapper;
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    MusicService musicService;

    @Override
    public PageInfo<Singer> getSingerByPage(int pageNO,String singername) {
        PageHelper.startPage(pageNO,7);
        SingerExample singerExample = new SingerExample();
        if (singername!=null&&singername!=""){
            singerExample.createCriteria().andSingernameLike("%"+singername+"%");
        }
        List<Singer> singerList = singerMapper.selectByExample(singerExample);
        PageInfo<Singer> info = new PageInfo<>(singerList);
        return info;
    }

    @Override
    public List<Singertype> getAllType() {
        return singertypeMapper.selectByExample(null);
    }

    //添加歌手
    @Override
    public int saveSinger(Singer singer, MultipartFile[] myFile) {
        for (MultipartFile file : myFile){
            if (file.getSize()<=0){
                continue;
            }
            String newFileName = file.getOriginalFilename();
            if (file.getOriginalFilename().endsWith(".jpg")){
                //File f = new File("E:/music/singerImages",newFileName);
                File f = new File("/music/singerImages",newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                singer.setSingerphotourl("singerImages/"+newFileName);
            }
        }
        return  singerMapper.insertSelective(singer);
    }

    //更改歌手
    @Override
    public int upDateSinger(Singer singer, MultipartFile[] myFile) {
        Singer oldSinger = singerMapper.selectByPrimaryKey(singer.getSingerid());
        for (MultipartFile file : myFile){
            if (file.getSize()<=0){
                continue;
            }
            String newFileName = file.getOriginalFilename();
            if (file.getOriginalFilename().endsWith(".jpg")){
                //File f = new File("E:/music/singerImages",newFileName);
                File f = new File("/music/singerImages",newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                singer.setSingerphotourl("singerImages/"+newFileName);
                //File oldf = new File("E:/music",oldSinger.getSingerphotourl());
                File oldf = new File("/music",oldSinger.getSingerphotourl());
                if (oldf.exists()){
                    oldf.delete();
                }
            }
        }

        return singerMapper.updateByPrimaryKeySelective(singer);
    }


    //删除歌手
    @Override
    public int deleteSinger(int singerid) {
        MusicExample musicExample = new MusicExample();
        musicExample.createCriteria().andSingeridEqualTo(singerid);
        List<Music> musicList = musicMapper.selectByExample(musicExample);
        for (Music m : musicList){
           musicService.deleteMusic(m.getMusicid());
        }
        Singer singer = singerMapper.selectByPrimaryKey(singerid);
        //File olePhoto = new File("E:/music",singer.getSingerphotourl());
        File olePhoto = new File("/music",singer.getSingerphotourl());
        if (olePhoto.exists()){
            olePhoto.delete();
        }
        return singerMapper.deleteByPrimaryKey(singerid);
    }

    //批量删除
    @Override
    public int deleteAllSinger(int[] singers) {
       /* int res=0;
        for (int i=0;i<=singers.length;i++){
            int singerId = singers[i];
            Singer singer = singerMapper.selectByPrimaryKey(singerId);
            File olePhoto = new File("E:\\music",singer.getSingerphotourl());
            if (olePhoto.exists()){
                olePhoto.delete();
            }
            res = singerMapper.deleteByPrimaryKey(singerId);
        }
        return res;*/
        int res = 0;
        for (int i=0;i<singers.length;i++){
            Singer singer = singerMapper.selectByPrimaryKey(singers[i]);
            //File olePhoto = new File("E:/music",singer.getSingerphotourl());
            File olePhoto = new File("/music",singer.getSingerphotourl());
            if (olePhoto.exists()){
                olePhoto.delete();
            }
            MusicExample musicExample = new MusicExample();
            musicExample.createCriteria().andSingeridEqualTo(singers[i]);
            List<Music> musicList = musicMapper.selectByExample(musicExample);
            for (Music m : musicList){
                musicService.deleteMusic(m.getMusicid());
            }
            res = singerMapper.deleteByPrimaryKey(singers[i]);
        }
        return res;
    }

    //下载歌手信息

    @Override
    public List<Singer> getAll() {
        return singerMapper.selectByExample(null);
    }
}
