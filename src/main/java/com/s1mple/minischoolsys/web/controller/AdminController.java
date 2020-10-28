package com.s1mple.minischoolsys.web.controller;


import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.AjxsResponse;
import com.s1mple.minischoolsys.exception.CustomException;
import com.s1mple.minischoolsys.exception.ExceptionType;
import com.s1mple.minischoolsys.service.AdminService;
import com.s1mple.minischoolsys.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AdminController {


    @Autowired
    AdminService adminService;


    @GetMapping("/admins")
    public List<Admin> getAdminPage(){
        return adminService.getAdminPage();
    }

    @GetMapping("/admins/{admin_id}")
    public Admin getAdmin(@PathVariable("admin_id") Long admin_id){
        return adminService.getById(admin_id);
    }

    @PostMapping("/admins")
    public Admin addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }


}
