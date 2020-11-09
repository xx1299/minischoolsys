package com.s1mple.minischoolsys.web.controller;

import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.exception.CustomException;
import com.s1mple.minischoolsys.exception.ExceptionType;
import com.s1mple.minischoolsys.service.ShiroService;
import com.s1mple.minischoolsys.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShiroController {

    @Autowired
    ShiroService shiroService;

    @PostMapping(value = "/login",produces= "application/json;charset=utf-8")
    public Map<String,String> login(@RequestBody Admin admin){
        Admin getAdmin = shiroService.getAdminByUsername(admin.getUsername());
        if (null == getAdmin || !getAdmin.getPassword().equals(admin.getPassword())){
            throw new CustomException(ExceptionType.User_INPUT_ERROR,"帐号或密码输入错误");
        }
        String token = shiroService.createToken(getAdmin);
        Map<String,String> adminMap = new HashMap<>();
        adminMap.put("token",token);
        adminMap.put("username",getAdmin.getUsername());
        adminMap.put("avatar",getAdmin.getAvatar());
        return adminMap;
    }

    @DeleteMapping("/logout")
    public void logout(){
        shiroService.logout();
    }



}
