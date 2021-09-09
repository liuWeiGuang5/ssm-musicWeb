package com.lwg.controller;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Message;
import com.lwg.domain.User;
import com.lwg.domain.Video;
import com.lwg.service.VideoService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "toVideoManager")
    public String toVideoManager (Model model,@RequestParam(defaultValue = "1") int pageNO,
                                  String videoname,String videoauthor){
        PageInfo<Video> info = videoService.getByPage(pageNO,videoname,videoauthor);
        System.out.println(info.getSize());
        model.addAttribute("info",info);
        model.addAttribute("videoname",videoname);
        model.addAttribute("videoauthor",videoauthor);
        return "admin/videoManager";
    }

    //保存视频
    @RequestMapping(value = "saveVideo")
    public String saveVideo (Model model, Video video, MultipartFile[] myFile){
        int i = videoService.saveVideo(video, myFile);
        if (i>0){
            return "redirect:/video/toVideoManager";
        }
        return "404";
    }

    //下载视频信息
    @RequestMapping(value = "expExl")
    public ResponseEntity<byte[]> expExl (Model model) throws UnsupportedEncodingException {
        List<Video> vlist = videoService.getAll();
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("编号id");
        headrow.createCell(1).setCellValue("视频名称");
        headrow.createCell(2).setCellValue("视频主角");
        headrow.createCell(3).setCellValue("视频图片路径");
        headrow.createCell(4).setCellValue("视频路径");

        for (int i = 0; i < vlist.size(); i++) {
            Video video = vlist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(video.getVideoid());
            row.createCell(1).setCellValue(video.getVideoname());
            row.createCell(2).setCellValue(video.getVideoauthor());
            row.createCell(3).setCellValue(video.getVideophoto());
            row.createCell(4).setCellValue(video.getVideourl());
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = os.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("视频数据.xls".getBytes("gbk"),
                "iso-8859-1"));
        return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
    }

    //修改视频信息
    @RequestMapping(value = "/editVideo")
    public String editVideo(Model model,Video video,MultipartFile[] myFile){
        int i = videoService.upDateVideo(video, myFile);
        if (i>0){
            return "redirect:/video/toVideoManager";
        }
        return "404";
    }

    //删除视频
    @RequestMapping(value = "/delVideoById")
    public String delVideoById(int videoId){
        int i = videoService.deleteVideo(videoId);
        if (i>0){
            return "redirect:/video/toVideoManager";
        }
        return "404";
    }

    @RequestMapping(value = "/delByIdAll")
    public String delByIdAll(@RequestParam(value = "videos[]") int[] videos){
        int i = videoService.deleteByIdAll(videos);
        if (i>0){
            return "redirect:/video/toVideoManager";
        }
        return "404";
    }


    /////////////////////网站///////////////////////////
    @RequestMapping(value = "/show")
    public String show(Model model,@RequestParam(defaultValue = "1") int pageNO){
        List<Video> videoList = videoService.getRandVideo();
        model.addAttribute("vlist",videoList);

        PageInfo<Video> info = videoService.getVideoByPage(pageNO);
        model.addAttribute("info",info);
        return "main/video";
    }

    @RequestMapping(value = "/play")
    public String play(Model model,int videoid){
        //根据id查询某个视频
        Video videoByVid = videoService.getVideoByVid(videoid);
        model.addAttribute("video",videoByVid);

        List<Video> videoList = videoService.getNoSelfRandVideo(videoid);
        model.addAttribute("vlist",videoList);

        List<Message> listMsgByVid = videoService.getListMsgByVid(videoid);
        model.addAttribute("messages",listMsgByVid);

        return "main/video-detail";
    }

    //保存评论
    @RequestMapping(value = "/fbMsg")
    public String fbMsg(Model model, Message message, HttpSession session){
        message.setMessagetime(new Date());
        User user = (User) session.getAttribute("user");
        message.setUid(user.getUid());
        int i = videoService.saveMessage(message);
        if (i>0){
            model.addAttribute("videoid",message.getVideoid());
            return "forward:/video/play";
        }
        return "404";
    }

}
