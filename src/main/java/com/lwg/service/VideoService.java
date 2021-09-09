package com.lwg.service;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Message;
import com.lwg.domain.Music;
import com.lwg.domain.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    PageInfo<Video> getByPage(int pageNO,String videoname,String videoauthor);
    int saveVideo(Video video, MultipartFile[] myFile);
    List<Video> getAll();
    int upDateVideo(Video video,MultipartFile[] myFile);
    int deleteVideo(int videoId);
    int deleteByIdAll(int[] videos);



    List<Video> getRandVideo();
    PageInfo<Video> getVideoByPage(int pageNO);
    Video getVideoByVid(int vid);
    List<Video> getNoSelfRandVideo(int vid);

    //获取最新6条评论
    List<Message> getListMsgByVid(int vid);

    int saveMessage(Message message);
}
