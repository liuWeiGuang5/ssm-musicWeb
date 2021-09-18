package com.lwg.controller;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.*;
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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/toIndex")
    public String toIndex(Model model){
        //发现音乐
        List<Music> randMusic = userService.getRandMusic();
        JSONArray jsonRandMusicList = JSONArray.fromObject(randMusic);
        model.addAttribute("list",jsonRandMusicList);
        //新歌
        List<Music> newMusic = userService.getNewMusic();
        JSONArray jsonNewMusicList = JSONArray.fromObject(newMusic);
        model.addAttribute("newSongs",jsonNewMusicList);
        //音乐排行榜
        List<Music> musicList = userService.topMusic();
        JSONArray josnMusicList = JSONArray.fromObject(musicList);
        model.addAttribute("topSongs",josnMusicList);
        //排行榜歌手
        List<Singer> topSinger = userService.getTopSinger();
        JSONArray jsonTopSingerList = JSONArray.fromObject(topSinger);
        model.addAttribute("topSingers",jsonTopSingerList);
        return "main/index";
    }

    @RequestMapping(value = "/toAdminLogin")
    public String toAdminLogin(Model model){
        return "admin/AdminLogin";
    }

    //管理员登陆
    @RequestMapping(value = "/adminLogin")
    public String adminLogin(Model model,User user,HttpSession session ){
        if(user.getUserName()!=null && user.getPassword()!=null &&user.getPassword()!=""){
            User loginUser = userService.getByUname(user.getUserName());
            if (loginUser!=null){
                if (DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(loginUser.getPassword())){
                    if (loginUser.getStatus()==1){
                        if (loginUser.getType()==1){
                            session.setAttribute("user",loginUser);
                            return "admin/main";
                        }else {
                            model.addAttribute("msg","没有管理员权限");
                        }
                    }else {
                        model.addAttribute("msg","用户名被禁");
                    }
                }else {
                    model.addAttribute("msg","密码不不正确");
                }
            }else {
                model.addAttribute("msg","用户名不存在,请检查");
            }
        }else {
            model.addAttribute("msg","用户名或者密码错误");
        }
        return "admin/AdminLogin";
    }

    //普通用户登录
    @RequestMapping(value = "/userLogin")
    public String userLogin(Model model,User user,HttpSession session){
        if(user.getUserName()!=null && user.getPassword()!=null &&user.getPassword()!=""){
            User loginUser = userService.getByUname(user.getUserName());
            if (loginUser!=null){
                if (DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(loginUser.getPassword())){
                    if (loginUser.getStatus()==1){
                        session.setAttribute("user",loginUser);
                        return "redirect:/user/toIndex";
                    }else {
                        model.addAttribute("msg","用户名被禁");
                    }
                }else {
                    model.addAttribute("msg","密码不不正确");
                }
            }else {
                model.addAttribute("msg","用户名不存在,请检查");
            }
        }else {
            model.addAttribute("msg","用户名或者密码错误");
        }
        return "../login";
    }


    //用户注册
    @RequestMapping(value = "/register")
    public String signup(User user){
        int i = userService.saveRegister(user);
        if (i>0){
            return "../login";
        }
        return "other";
    }




    //查询所有用户
    @RequestMapping(value = "/toUserManager")
    public String toUserManager(Model model,@RequestParam(defaultValue = "1") int pageNO,
                                String user_name){
        PageInfo<User> info = userService.getUlistByPage(pageNO,user_name);
        System.out.println(info.getSize());
        model.addAttribute("info",info);
        model.addAttribute("user_name",user_name);
        return "admin/userManager";
    }


    //添加用户
    @RequestMapping(value = "/saveUser")
    public String saveUser(Model model,User user){
        int result = userService.saveUser(user);
        if (result > 0){
            return "redirect:/user/toUserManager";
        }
        return "404";
    }


    //更新用户信息
    @RequestMapping(value = "/editUser")
    public String editUser(Model model,User user,String oldPwd){
        int result = userService.upDateUser(user,oldPwd);
        if (result > 0){
            return "redirect:/user/toUserManager";
        }
        return "404";
    }

    //通过id删除用户
    @RequestMapping(value = "/delById")
    @ResponseBody
    public String delById(Model model,Integer userId){
       int result = userService.deleteUserById(userId);
        if (result > 0){
            return "yes";
        }
        return "404";
    }

    //修改用户状态
    @RequestMapping(value = "/editType")
    @ResponseBody
    public String editType(Model model,User user){
        int i = userService.upDateStatus(user);
        if (i > 0){
            return "yes";
        }
        return "404";
    }

    //下载客户信息
    @RequestMapping(value = "expExl")
    public ResponseEntity<byte[]> expExl (Model model) throws UnsupportedEncodingException {
        List<User> ulist = userService.getAll();
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("客户编号");
        headrow.createCell(1).setCellValue("客户账号");
        headrow.createCell(2).setCellValue("客户邮箱");
        headrow.createCell(3).setCellValue("客户密码");
        headrow.createCell(4).setCellValue("客户电话");
        headrow.createCell(5).setCellValue("客户地址");
        headrow.createCell(6).setCellValue("用户状态");
        headrow.createCell(7).setCellValue("用户类型");

        for (int i = 0; i < ulist.size(); i++) {
            User user = ulist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(user.getUid());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPassword());
            row.createCell(4).setCellValue(user.getPhone());
            row.createCell(5).setCellValue(user.getAddress());
            String status = "";
            if(user.getStatus()==1){
                status="已启用";
            }else {
                status="已禁用";
            }
            row.createCell(6).setCellValue(status);
            String type = "";
            if (user.getType()==1){
                type="管理员";
            }else {
                type="普通用户";
            }
            row.createCell(7).setCellValue(type);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = os.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("客户数据.xls".getBytes("gbk"),
                "iso-8859-1"));
        return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
    }





    //用户统计
    @RequestMapping(value = "/getData")
    @ResponseBody
    public List<UserView> getData(){
        return userService.getData();
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        User loginUser = (User) session.getAttribute("user");
        session.invalidate();
        if (loginUser.getType()==0){
            return "redirect:/user/toIndex";
        }else {
            return "redirect:/user/toAdminLogin";
        }
        //return "redirect:/user/toIndex";
    }

    //返回后台首页
    @RequestMapping(value = "/backIndex")
    public String backIndex(Model model){
        return "admin/main";
    }

    //忘记密码
    @RequestMapping(value = "/forget")
    @ResponseBody
    public String forget(User user){
        System.out.println(user);
        int i = userService.selectForget(user);
        if (i>0){
            return "yes";
        }
        return "no";
    }

    @RequestMapping(value = "/updatePwd")
    public String updatePwd(String uname,String password){
        int i = userService.upDatePwd(uname, password);
        if (i>0){
            return "../login";
        }
        return "404";

    }

}
