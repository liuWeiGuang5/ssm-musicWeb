package com.lwg.service;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Music;
import com.lwg.domain.Singer;
import com.lwg.domain.User;
import com.lwg.domain.UserView;

import java.util.List;

public interface UserService {
    //登陆验证
    User getByUname(String uname);
    //分页
    PageInfo<User> getUlistByPage(int pageNO,String user_name);
    //添加用户
    int saveUser(User user);
    //更新用户
    int upDateUser(User user,String oldPwd);
    //删除用户
    int deleteUserById(Integer uid);
    //修改状态
    int upDateStatus(User user);
    //柱状图
    List<UserView> getData();
    //获取随机音乐
    List<Music> getRandMusic();
    //获取新歌
    List<Music> getNewMusic();
    //获取音乐排行榜
    List<Music> topMusic();
    //获取歌手排行榜
    List<Singer> getTopSinger();
    //忘记密码
    int selectForget(User user);
    //更新密码
    int upDatePwd(String uname,String password);
    //获取所有用户
    List<User> getAll();
    //注册用户
    int saveRegister(User user);
}
