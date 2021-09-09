package com.lwg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lwg.dao.MessageMapper;
import com.lwg.dao.VideoMapper;
import com.lwg.domain.Message;
import com.lwg.domain.MessageExample;
import com.lwg.domain.Video;
import com.lwg.domain.VideoExample;
import com.lwg.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    MessageMapper messageMapper;

    @Override
    public PageInfo<Video> getByPage(int pageNO,String videoname,String videoauthor) {
        PageHelper.startPage(pageNO,5);
        VideoExample videoExample = new VideoExample();
        VideoExample.Criteria criteria = videoExample.createCriteria();
        if (videoname!=null&&videoname!=""){
            criteria.andVideonameLike("%"+videoname+"%");
        } if (videoauthor!=null&&videoauthor!=""){
            criteria.andVideoauthorLike("%"+videoauthor+"%");
        }
        List<Video> videoList = videoMapper.selectByExample(videoExample);
        PageInfo<Video> info = new PageInfo<>(videoList);
        return info;
    }

    //保存视频
    @Override
    public int saveVideo(Video video, MultipartFile[] myFile) {
        try {
            for(MultipartFile f : myFile){
                if (f.getSize()<=0){
                    continue;
                }
                String newfname = UUID.randomUUID().toString()+f.getOriginalFilename();
                if (f.getOriginalFilename().endsWith(".mp4")){
                    //File targetFile = new File("E:/music/video",newfname);
                    File targetFile = new File("/music/video",newfname);
                    f.transferTo(targetFile);
                    video.setVideourl("video/"+newfname);
                }else {
                    //File targetFile = new File("E:/music/videoImages",newfname);
                    File targetFile = new File("/music/videoImages",newfname);
                    f.transferTo(targetFile);
                    video.setVideophoto("videoImages/"+newfname);
                }
            }
            video.setVideotime(new Date());
            return videoMapper.insertSelective(video);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Video> getAll() {
        return videoMapper.selectByExample(null);
    }

    //修改视频
    @Override
    public int upDateVideo(Video video, MultipartFile[] myFile) {
        Video oldVideo = videoMapper.selectByPrimaryKey(video.getVideoid());
        for (MultipartFile file : myFile){
            if (file.getSize()<=0){
                continue;
            }
            String newFileName = UUID.randomUUID().toString()+file.getOriginalFilename();
            if (file.getOriginalFilename().endsWith(".mp4")){
                //File f = new File("E:/music/video",newFileName);
                File f = new File("/music/video",newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                video.setVideourl("video/"+newFileName);
                //File oldf = new File("E:/music/video",oldVideo.getVideourl());
                File oldf = new File("/music/video",oldVideo.getVideourl());
                if (oldf.exists()){
                    oldf.delete();
                }
            }else {
                //File f = new File("E:/music/videoImages", newFileName);
                File f = new File("/music/videoImages", newFileName);
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                video.setVideophoto("videoImages/"+newFileName);
                //File oldf = new File("E:/music",oldVideo.getVideophoto());
                File oldf = new File("/music",oldVideo.getVideophoto());
                if (oldf.exists()){
                    oldf.delete();
                }
            }
        }
        return videoMapper.updateByPrimaryKeySelective(video);
    }

    //删除视频

    @Override
    public int deleteVideo(int videoId) {
        Video video = videoMapper.selectByPrimaryKey(videoId);
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andVideoidEqualTo(videoId);
        messageMapper.deleteByExample(messageExample);
        //File oldPhoto = new File("E:/music",video.getVideophoto());
        File oldPhoto = new File("/music",video.getVideophoto());
        if (oldPhoto.exists()){
            oldPhoto.delete();
        }


        //File oldVideo = new File("E:/music",video.getVideourl());
        File oldVideo = new File("/music",video.getVideourl());
        if (oldVideo.exists()){
            oldVideo.delete();
        }
        VideoExample videoExample = new VideoExample();
        videoExample.createCriteria().andVideoidNotEqualTo(videoId);
        return videoMapper.deleteByExample(videoExample);
    }


    //批量删除
    @Override
    public int deleteByIdAll(int[] videos) {
        int res = 0;
        for (int i=0;i<videos.length;i++){
            Video video = videoMapper.selectByPrimaryKey(videos[i]);
            MessageExample messageExample = new MessageExample();
            messageExample.createCriteria().andVideoidEqualTo(videos[i]);
            messageMapper.deleteByExample(messageExample);
            //File oldPhoto = new File("E:/music",video.getVideophoto());
            File oldPhoto = new File("/music",video.getVideophoto());
            if (oldPhoto.exists()){
                oldPhoto.delete();
            }

            //File oldVideo = new File("E:/music",video.getVideourl());
            File oldVideo = new File("/music",video.getVideourl());
            if (oldVideo.exists()){
                oldVideo.delete();
            }
            res = videoMapper.deleteByPrimaryKey(videos[i]);
        }
        return res;
    }




    @Override
    public List<Video> getRandVideo() {
        VideoExample videoExample = new VideoExample();
        videoExample.setOrderByClause("rand() limit 6");
        List<Video> videoList = videoMapper.selectByExample(videoExample);
        return videoList;
    }

    @Override
    public PageInfo<Video> getVideoByPage(int pageNO) {
        PageHelper.startPage(pageNO,8);
        List<Video> videoList = videoMapper.selectByExample(null);
        PageInfo<Video> info = new PageInfo<>(videoList);
        return info;
    }

    @Override
    public Video getVideoByVid(int vid) {
        return videoMapper.selectByPrimaryKey(vid);
    }

    @Override
    public List<Video> getNoSelfRandVideo(int vid) {
        VideoExample videoExample = new VideoExample();
        videoExample.createCriteria().andVideoidNotEqualTo(vid);
        videoExample.setOrderByClause("rand() limit 5");
        List<Video> videoList = videoMapper.selectByExample(videoExample);
        return videoList;
    }

    @Override
    public List<Message> getListMsgByVid(int vid) {
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andVideoidEqualTo(vid);
        messageExample.setOrderByClause("messageTime desc limit 6");
        List<Message> messageList = messageMapper.selectByExample(messageExample);
        return messageList;
    }


    @Override
    public int saveMessage(Message message) {
        return messageMapper.insertSelective(message);
    }
}
