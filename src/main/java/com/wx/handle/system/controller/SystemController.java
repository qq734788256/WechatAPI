package com.wx.handle.system.controller;

import com.wx.base.param.system.LoginParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SystemController {

    @RequestMapping(value = "/login")
    public void doLogin(@RequestBody LoginParam param){
        
    }
}
