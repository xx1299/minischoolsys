package com.s1mple.minischoolsys.web.controller;

import com.s1mple.minischoolsys.domain.vo.MenuVo;
import com.s1mple.minischoolsys.service.AuthorityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;


    @GetMapping("/person-menus")
    public List<MenuVo> getPersonMenuTree(){
        return authorityService.getPersonMenuTree();
    }

    @GetMapping("/menus")
    public List<MenuVo> getMenuTree(){
        return authorityService.getMenuTree();
    }

}
