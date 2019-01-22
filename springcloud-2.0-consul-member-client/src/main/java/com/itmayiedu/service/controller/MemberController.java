package com.itmayiedu.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Description:
 * @author: ChenRuiQing.
 * Create Time:  2019-01-21 下午 9:10
 */
@RestController
public class MemberController {
    @Value("${server.port}")
    private  String serverPort;

    @RequestMapping("/getMember")
    public String getMember(){
        return "我是会员服务，兮兮哈哈呼呼啦啦咚咚咚，Duang。。。。!服务端口变魔术："+serverPort;
    }
}
