package com.lwg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lwg.dao.MusicMapper;
import com.lwg.dao.MusictypeMapper;
import com.lwg.dao.SingerMapper;
import com.lwg.domain.*;
import com.lwg.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    SingerMapper singerMapper;
    @Autowired
    MusictypeMapper musictypeMapper;


    //音乐分页
    @Override
    public PageInfo<Music> getMusicByPage(Integer pageNO,String musicName,String singername) {
        PageHelper.startPage(pageNO, 5);
        /*usicExample musicExample = new MusicExample();
        MusicExample.Criteria criteria = musicExample.createCriteria();*/
        /*if (musicName!=null && musicName!=""){
            criteria.andMusicnameLike("%"+musicName+"%");
        }*/
        /*if (singername!=null && singername!=""){
            criteria.andMusicnameLike("%"+singername+"%");
        }*/
        //List<Music> musicList = musicMapper.selectByExample(musicExample);
        List<Music> musicList = musicMapper.getMuiscs(singername,musicName);
        PageInfo<Music> info = new PageInfo<>(musicList);
        return info;
    }

    //保存音乐
    @Override
    public int saveMusic(Music music, MultipartFile[] myFile, String singername) {
        //根据上传文件后缀确认上传文件类型，并上传数据
        for (MultipartFile file : myFile) {
            String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            if(file.getSize()<=0){
                continue;
            }
            if (file.getOriginalFilename().endsWith(".mp3")) {
                //File f = new File("E:/music/musics", newFileName);
                File f = new File("/music/musics", newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                music.setSongurl("musics/" + newFileName);
            } else {
                //File f = new File("E:/music/musicImages", newFileName);
                File f = new File("/music/musicImages", newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                music.setMusicphotourl("musicImages/" + newFileName);
            }

        }
        //将音乐对象保存到数据库中
        music.setTime(new Date());
        //List<Singer> allSingerList = singerMapper.selectByExample(null);

        SingerExample singerExample = new SingerExample();
        singerExample.createCriteria().andSingernameEqualTo(singername);
        List<Singer> singerList = singerMapper.selectByExample(singerExample);
        if (singerList != null && singerList.size() > 0) {
            music.setSingerid(singerList.get(0).getSingerid());
        }else {
            music.setSingerid(126);
        }
        int i = musicMapper.insertSelective(music);
        return i;
    }

    //查询所有音乐类型
    @Override
    public List<Musictype> getAllType() {
        return musictypeMapper.selectByExample(null);
    }


    //更新歌曲信息
    @Override
    public int upDateMusic(Music music, MultipartFile[] myFile, String singername) {
        Music oldMusic = musicMapper.selectByPrimaryKey(music.getMusicid());
        for (MultipartFile file : myFile){
            if (file.getSize()<=0){
                continue;
            }
            String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            if (file.getOriginalFilename().endsWith(".mp3")) {
                //File f = new File("E:/music/musics", newFileName);
                File f = new File("/music/musics", newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                music.setSongurl("musics/" + newFileName);
                //File oldf = new File("E:/music/musics",oldMusic.getSongurl());
                File oldf = new File("/music/musics",oldMusic.getSongurl());
                if (oldf.exists()){
                    oldf.delete();
                }
            } else {
                //File f = new File("E:/music/musicImages", newFileName);
                File f = new File("/music/musicImages", newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                music.setMusicphotourl("musicImages/" + newFileName);
                //File oldf = new File("E:/music",oldMusic.getMusicphotourl());
                File oldf = new File("/music",oldMusic.getMusicphotourl());
                if (oldf.exists()){
                    oldf.delete();
                }
            }
        }
        //更新操作
        //将音乐对象保存到数据库中
        SingerExample singerExample = new SingerExample();
        singerExample.createCriteria().andSingernameEqualTo(singername);
        List<Singer> singerList = singerMapper.selectByExample(singerExample);
        if (singerList != null && singerList.size() > 0) {
            music.setSingerid(singerList.get(0).getSingerid());
            music.setTime(new Date());
        }
        int i = musicMapper.updateByPrimaryKeySelective(music);
        return i;
    }


    //删除歌曲
    @Override
    public int deleteMusic(int musicId) {
        Music oldMusic = musicMapper.selectByPrimaryKey(musicId);
        //File photof = new File("E:/music",oldMusic.getMusicphotourl());
        File photof = new File("/music",oldMusic.getMusicphotourl());
        if (photof.exists()){
            photof.delete();
        }
        //File musicf = new File("E:/music",oldMusic.getSongurl());
        File musicf = new File("/music",oldMusic.getSongurl());
        if (musicf.exists()){
            musicf.delete();
        }
        return musicMapper.deleteByPrimaryKey(musicId);
    }

    //歌曲统计
    @Override
    public List<MusicView> getData() {
        return musicMapper.getData();
    }

    //////////////////////网站/////////////////////
    @Override
    public List<Music> getMusicsBySingerId(int sid) {
        MusicExample musicExample = new MusicExample();
        musicExample.createCriteria().andSingeridEqualTo(sid);
        return musicMapper.selectByExample(musicExample);
    }

    @Override
    public PageInfo<Music> getHotMusicByPage(int pageNo) {
        PageHelper.startPage(pageNo,12);
        MusicExample musicExample = new MusicExample();
        musicExample.setOrderByClause("musicHot desc");
        List<Music> musicList = musicMapper.selectByExample(musicExample);
        PageInfo info = new PageInfo(musicList);
        return info;
    }

    @Override
    public PageInfo<Music> getMusicByTypeId(int typeid, int pageNo) {
        PageHelper.startPage(pageNo,12);
        MusicExample musicExample = new MusicExample();
        if (typeid!=0){
            //根据某个流派查询
            musicExample.createCriteria().andMusictypeidEqualTo(typeid);
        }
        List<Music> musicList = musicMapper.selectByExample(musicExample);
        PageInfo<Music> info = new PageInfo<>(musicList);
        return info;
    }

    //搜索音乐
    @Override
    public List<Music> searchMusic(String searchName) {
        MusicExample musicExample = new MusicExample();
        musicExample.createCriteria().andMusicnameLike("%"+searchName+"%");
        List<Music> searchMusicList = musicMapper.selectByExample(musicExample);
        return searchMusicList;
    }

    //导出音乐数据

    @Override
    public List<Music> getAll() {
        return musicMapper.selectByExample(null);
    }
}







