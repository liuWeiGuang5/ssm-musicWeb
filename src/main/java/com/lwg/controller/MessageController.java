package com.lwg.controller;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Message;
import com.lwg.domain.Video;
import com.lwg.service.MessageService;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/toMessageManager")
    public String toMessageManager(Model model,@RequestParam(defaultValue = "1") int pageNO,
                                   String messagecomment,String videoname){
        PageInfo<Message> info = messageService.getMessageByPage(pageNO,messagecomment,videoname);
        model.addAttribute("info", info);
        model.addAttribute("videoname",videoname);
        model.addAttribute("messagecomment",messagecomment);
        return "admin/messageManager";
    }


    @RequestMapping(value = "/delById")
    @ResponseBody
    public String delById(int messageid){
        int i = messageService.deleteById(messageid);
        if (i>0){
            return "yes";
        }
        return "no";
    }

    //下载评论信息
    @RequestMapping(value = "expExl")
    public ResponseEntity<byte[]> expExl (Model model) throws UnsupportedEncodingException {
        List<Message> mlist = messageService.getAll();
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("评论编号");
        headrow.createCell(1).setCellValue("评论信息");
        headrow.createCell(2).setCellValue("评论时间");
        headrow.createCell(3).setCellValue("视频名称");
        headrow.createCell(4).setCellValue("发表用户");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < mlist.size(); i++) {
            Message message = mlist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(message.getMessageid());
            row.createCell(1).setCellValue(message.getMessagecomment());
            row.createCell(2).setCellValue(sdf.format(message.getMessagetime()));
            row.createCell(3).setCellValue(message.getVideo().getVideoname());
            row.createCell(4).setCellValue(message.getUser().getUserName());
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = os.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("评论数据.xls".getBytes("gbk"),
                "iso-8859-1"));
        return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
    }
}
