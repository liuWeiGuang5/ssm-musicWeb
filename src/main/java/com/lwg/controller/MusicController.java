package com.lwg.controller;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.*;
import com.lwg.service.MusicService;
import com.lwg.service.UserService;
import net.sf.json.JSONArray;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/music")
public class MusicController {

    @Autowired
    MusicService musicService;
    @Autowired
    UserService userService;

    @RequestMapping(value ="/toManager")
    public String toManager(Model model,@RequestParam(defaultValue = "1") int pageNO,
                            String musicName,String singername){
        PageInfo<Music> info = musicService.getMusicByPage(pageNO,musicName,singername);
        System.out.println(info);
        model.addAttribute("info", info);
        model.addAttribute("sname",musicName);
        model.addAttribute("singername",singername);
        model.addAttribute("mtlist",musicService.getAllType());
        return "admin/musicManager";
    }

    //保存音乐
    @RequestMapping(value = "/addMusic")
    public String addMusic(Model model, Music music, MultipartFile[] myFile,String singername) throws IOException{
        int i = musicService.saveMusic(music, myFile, singername);
        if (i > 0){
            return "redirect:/music/toManager";
        }
        return "404";
    }


    //修改歌曲信息
    @RequestMapping(value = "/editMusic")
    public String editMusic(Model model,Music music, MultipartFile[] myFile,String singername){
        int i = musicService.upDateMusic(music, myFile, singername);
        if (i > 0){
            return "redirect:/music/toManager";
        }
        return "404";
    }

    //删除
    @RequestMapping(value = "/delById")
    @ResponseBody
    public String delById(int musicId){
        int i = musicService.deleteMusic(musicId);
        if (i > 0){
            return "yes";
        }
        return "no";
    }


    //下载音乐信息
    @RequestMapping(value = "expExl")
    public ResponseEntity<byte[]> expExl (Model model) throws UnsupportedEncodingException {
        List<Music> mlist = musicService.getAll();
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("歌曲编号");
        headrow.createCell(1).setCellValue("歌曲名称");
        headrow.createCell(2).setCellValue("歌曲图片");
        headrow.createCell(3).setCellValue("热度");
        headrow.createCell(4).setCellValue("歌词地址");
        headrow.createCell(5).setCellValue("歌曲地址");
        headrow.createCell(6).setCellValue("歌手名称");
        headrow.createCell(7).setCellValue("歌曲类型");

        for (int i = 0; i < mlist.size(); i++) {
            Music music = mlist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(music.getMusicid());
            row.createCell(1).setCellValue(music.getMusicname());
            row.createCell(2).setCellValue(music.getMusicphotourl());
            row.createCell(3).setCellValue(music.getMusichot());
            row.createCell(4).setCellValue(music.getLyricurl());
            row.createCell(5).setCellValue(music.getSongurl());
            row.createCell(6).setCellValue(music.getSinger().getSingername());
            row.createCell(7).setCellValue(music.getMusictype().getMusictypename());
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = os.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("音乐数据.xls".getBytes("gbk"),
                "iso-8859-1"));
        return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
    }





    //歌曲统计
    @RequestMapping(value = "/getData")
    @ResponseBody
    public List<MusicView> getData(){
        List<MusicView> mvList = musicService.getData();
        return mvList;
    }

    ////////////////////////网站//////////////////////////
    @RequestMapping(value = "/getMusicsBySingerId")
    public String getMusicsBySingerId(Model model,int sid){
        List<Music> musicList = musicService.getMusicsBySingerId(sid);
        if (musicList!=null&&musicList.size()>0){
            JSONArray jsonMusicList = JSONArray.fromObject(musicList);
            model.addAttribute("mlist",jsonMusicList);
            model.addAttribute("message",musicList.get(0).getSinger().getSingername());
            //排行榜歌手
        }else {
            model.addAttribute("message","该歌手还没有歌曲");
        }
        List<Singer> topSinger = userService.getTopSinger();
        JSONArray jsonTopSingerList = JSONArray.fromObject(topSinger);
        model.addAttribute("topSingers",jsonTopSingerList);
        return "main/search";
    }

    //推荐
    @RequestMapping(value = "/getComendMusics")
    public String getCommend(Model model,@RequestParam(defaultValue = "1") int pageNO){
        PageInfo<Music> hotMusicList = musicService.getHotMusicByPage(pageNO);
        JSONArray json = JSONArray.fromObject(hotMusicList.getList());
        model.addAttribute("mlist",json);
        model.addAttribute("info",hotMusicList);
        return "main/comend";
    }

    @RequestMapping(value = "/getTypes")
    public String getTypes(Model model,@RequestParam(defaultValue = "0") int typeid,@RequestParam(defaultValue = "1") int pageNO){
        //查询所有的流派
        List<Musictype> allType = musicService.getAllType();
        model.addAttribute("tlist",allType);
        //查询所有流派下的每一页数据
        PageInfo<Music> info = musicService.getMusicByTypeId(typeid, pageNO);
        model.addAttribute("info",info);
        model.addAttribute("nowtype",typeid);
        model.addAttribute("json",JSONArray.fromObject(info.getList()));
        return "main/genres";
    }

    //搜索音乐
    @RequestMapping(value = "/searchMusic")
    public String searchMusic(Model model,String searchName){
        List<Music> searchMusicList = musicService.searchMusic(searchName);
        JSONArray json = JSONArray.fromObject(searchMusicList);
        model.addAttribute("info",searchMusicList);
        model.addAttribute("mlist",json);
        model.addAttribute("searchName",searchName);
        return "main/searchmusic";
    }


}
