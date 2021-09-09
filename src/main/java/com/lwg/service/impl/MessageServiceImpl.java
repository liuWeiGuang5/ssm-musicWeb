package com.lwg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lwg.dao.MessageMapper;
import com.lwg.domain.Message;
import com.lwg.domain.MessageExample;
import com.lwg.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public PageInfo<Message> getMessageByPage(int pageNO,String messagecomment,String videoname) {
        PageHelper.startPage(pageNO,8);
        List<Message> messages = messageMapper.getMessage(messagecomment, videoname);
        PageInfo<Message> info = new PageInfo<>(messages);
        return info;
    }

    @Override
    public int deleteById(int messageid) {
        int i = messageMapper.deleteByPrimaryKey(messageid);
        return i;
    }

    @Override
    public List<Message> getAll() {
        return messageMapper.selectByExample(null);
    }
}
