package com.lwg.controller;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Music;
import com.lwg.domain.Singer;
import com.lwg.service.SingerService;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/singer")
public class SingerController {
    @Autowired
    SingerService singerService;
    @RequestMapping(value = "/toSingerManager")
    public String toSingerManager(Model model,@RequestParam(defaultValue = "1") int pageNO,
                                  String singername){
        PageInfo<Singer> info = singerService.getSingerByPage(pageNO,singername);
        model.addAttribute("info",info);
        model.addAttribute("singername",singername);
        model.addAttribute("stlist",singerService.getAllType());
        return "admin/singerManager";
    }

    //添加歌手
    @RequestMapping(value = "/addSinger")
    public String addSinger(Model model, Singer singer, MultipartFile[] myFile){
        int i = singerService.saveSinger(singer, myFile);
        if (i>0){
            return "redirect:/singer/toSingerManager";
        }
        return "404";
    }

    //修改
    @RequestMapping(value = "/editSinger")
    public String editSing(Model model, Singer singer, MultipartFile[] myFile){
        int i = singerService.upDateSinger(singer, myFile);
        if (i>0){
            return "redirect:/singer/toSingerManager";
        }
        return "404";
    }

    //删除
    @RequestMapping(value = "/delById")
    public String delById(int singerid){
        int i = singerService.deleteSinger(singerid);
        if (i>0){
            return "redirect:/singer/toSingerManager";
        }
        return "404";

    }

    //批量删除歌手
    @RequestMapping(value = "/delByIdAll")
    public String delByIdAll(@RequestParam(value = "singers[]") int[] singers){
        int i = singerService.deleteAllSinger(singers);
        if (i>0){
            return "redirect:/singer/toSingerManager";
        }
        return "404";
    }

    //下载歌手信息
    @RequestMapping(value = "expExl")
    public ResponseEntity<byte[]> expExl (Model model) throws UnsupportedEncodingException {
        List<Singer> slist = singerService.getAll();
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("歌手编号");
        headrow.createCell(1).setCellValue("歌手姓名");
        headrow.createCell(2).setCellValue("歌手图片");
        headrow.createCell(3).setCellValue("热度");
        headrow.createCell(4).setCellValue("歌手地址");
        headrow.createCell(5).setCellValue("歌手类型");


        for (int i = 0; i < slist.size(); i++) {
            Singer singer = slist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(singer.getSingerid());
            row.createCell(1).setCellValue(singer.getSingername());
            row.createCell(2).setCellValue(singer.getSingerphotourl());
            row.createCell(3).setCellValue(singer.getSingerhot());
            row.createCell(4).setCellValue(singer.getAddress());
            row.createCell(5).setCellValue(singer.getSingertype().getTypename());
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = os.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("歌手数据.xls".getBytes("gbk"),
                "iso-8859-1"));
        return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
    }


}
