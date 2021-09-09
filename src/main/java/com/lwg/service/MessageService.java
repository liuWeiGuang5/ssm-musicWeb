package com.lwg.service;

import com.github.pagehelper.PageInfo;
import com.lwg.domain.Message;

import java.util.List;

public interface MessageService {
    PageInfo<Message> getMessageByPage(int pageNO,String messagecomment,String videoname);
    int deleteById(int messageid);
    List<Message> getAll();
}
