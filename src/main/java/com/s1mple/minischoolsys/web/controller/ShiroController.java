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

@RestController
public class ShiroController {

    @Autowired
    ShiroService shiroService;

    @PostMapping(value = "/login",produces= "application/json;charset=utf-8")
    public String login(@RequestBody Admin admin){
        Admin getAdmin = shiroService.getAdminByUsername(admin.getUsername());
        if (null == getAdmin || !getAdmin.getPassword().equals(admin.getPassword())){
            throw new CustomException(ExceptionType.User_INPUT_ERROR,"帐号或密码输入错误");
        }
        System.out.println(getAdmin);
        String token = shiroService.createToken(getAdmin);
        return token;
    }

    @DeleteMapping("/logout")
    public void logout(){
        shiroService.logout();
    }



}
