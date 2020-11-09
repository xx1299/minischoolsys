package com.s1mple.minischoolsys.web.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.Menu;
import com.s1mple.minischoolsys.domain.vo.MenuVo;
import com.s1mple.minischoolsys.service.AuthorityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;


    @GetMapping("/personMenus")
    public List<MenuVo> getPersonMenuTree(){
        return authorityService.getPersonMenuTree();
    }

    @GetMapping("/menus")
    public List<MenuVo> getMenuTree(){
        return authorityService.getMenuTree();
    }

    @GetMapping("/topMenus")
    public List<Authority> getTopMenus(){
        return authorityService.list(Wrappers.<Authority>lambdaQuery().eq(Authority::getType,1));
    }

    @PostMapping("/menus")
    public Authority addMenu(@RequestBody Authority authority){
        authority.setType(2);
        authority.setStatus(Boolean.TRUE);
       if (ObjectUtils.isEmpty(authority.getParent_id())){
           authority.setType(1);
       }
        authorityService.save(authority);
        System.out.println("添加menu");
       return authority;
    }

    @PutMapping("/menuStatus")
    public Authority updateMenuStatus(@RequestBody Authority authority){
        authorityService.updateById(authority);
        return authority;
    }

    @PutMapping("/menus")
    public Authority editMenu(@RequestBody Authority authority){
        System.out.println(authority);
        authorityService.updateById(authority);
        return authority;
    }

    @DeleteMapping("/menus/{authority_id}")
    public void delMenu(@PathVariable Long authority_id){
        authorityService.delMenu(authority_id);
    }

}
