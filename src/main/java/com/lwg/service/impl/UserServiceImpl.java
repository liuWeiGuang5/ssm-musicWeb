package com.lwg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lwg.dao.MusicMapper;
import com.lwg.dao.SingerMapper;
import com.lwg.dao.UserMapper;
import com.lwg.domain.*;
import com.lwg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
//@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    SingerMapper singerMapper;

    //登陆检查
    @Override
    public User getByUname(String uname) {
        UserExample ue = new UserExample();
        UserExample.Criteria cc = ue.createCriteria();
        cc.andUserNameEqualTo(uname);
        List<User> userList = userMapper.selectByExample(ue);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    //分页
    @Override
    public PageInfo<User> getUlistByPage(int pageNO,String user_name) {
        PageHelper.startPage(pageNO,6);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (user_name!=null&&user_name!=""){
            criteria.andUserNameLike("%"+user_name+"%");
        }
        criteria.andTypeEqualTo(0);
        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> info = new PageInfo<User>(userList);
        return info;
    }

    //添加用户
    @Override
    public int saveUser(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(1);// 启动状态
        user.setType(0);//状态
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int i = userMapper.insertSelective(user);
        return i;
    }




    //更新用户信息
    @Override
    public int upDateUser(User user,String oldPwd) {
        user.setUpdateTime(new Date());
        if (!user.getPassword().equals(oldPwd)){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    //删除用户
    @Override
    public int deleteUserById(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    //设置用户状态
    @Override
    public int upDateStatus(User user) {
        if (user.getStatus()==1){
            user.setStatus(0);
        }else{
            user.setStatus(1);
        }
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    //柱状图
    @Override
    public List<UserView> getData() {
        return userMapper.getData();
    }

    //查找所有用户
    @Override
    public List<User> getAll() {
        return userMapper.selectByExample(null);
    }

    //忘记密码
    @Override
    public int selectForget(User user) {
        String userName = user.getUserName();
        String email = user.getEmail();
        String phone = user.getPhone();
        String address = user.getAddress();
        if (userName!=null&&userName!="" && email!=null&&email!="" &&
                phone!=null&&phone!="" && address!=null&&address!="" ){
            User f = userMapper.getForget(user);
            if (f!=null){
                return 1;
            }
        }
        return 0;
    }

    //更新密码
    @Override
    public int upDatePwd(String uname, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(uname);
        List<User> users = userMapper.selectByExample(userExample);
        int i = 0;
        for (User u : users){
            u.setUpdateTime(new Date());
            u.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            i = userMapper.updateByPrimaryKeySelective(u);
        }
        return i;
    }

    //检查重复用户名
    @Override
    public int saveRegister(User user) {
        String userName = user.getUserName();
        User targetUser = userMapper.getUser(userName);
        if (targetUser!=null){
            return 0;
        }else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(1);// 启动状态
            user.setType(0);//状态
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userMapper.insertSelective(user);
        }
        return 1;
    }

    //=====================网站==============================


    //获取随机6个音乐
    @Override
    public List<Music> getRandMusic() {
        MusicExample musicExample = new MusicExample();
        musicExample.setOrderByClause("rand() limit 6");
        List<Music> musicList = musicMapper.selectByExample(musicExample);
        return musicList;
    }

    //获取新歌
    @Override
    public List<Music> getNewMusic() {
        MusicExample musicExample = new MusicExample();
        musicExample.setOrderByClause("time desc limit 8");
        List<Music> musicList = musicMapper.selectByExample(musicExample);
        return musicList;
    }

    //音乐排行
    @Override
    public List<Music> topMusic() {
        MusicExample musicExample = new MusicExample();
        musicExample.setOrderByClause("musicHot desc limit 6");
        return musicMapper.selectByExample(musicExample);
    }

    //歌手排行
    @Override
    public List<Singer> getTopSinger() {
        SingerExample singerExample = new SingerExample();
        singerExample.setOrderByClause("singerHot desc limit 12");
        return singerMapper.selectByExample(singerExample);
    }


}
