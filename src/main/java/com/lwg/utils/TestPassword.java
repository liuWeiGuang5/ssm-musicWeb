package com.lwg.utils;

import org.springframework.util.DigestUtils;

public class TestPassword {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
