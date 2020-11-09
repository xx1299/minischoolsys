package com.s1mple.minischoolsys.web.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.vo.AuthorityVo;
import com.s1mple.minischoolsys.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    /**
     * 根据角色权限获取个人菜单
     * @return
     */
    @GetMapping("/personMenus")
    public List<AuthorityVo> getPersonMenuTree(){
        return authorityService.getPersonMenuTree();
    }

    /**
     * 获得菜单列表，实际是类型值为1、2的权限
     * @return
     */
    @GetMapping("/menus")
    public List<AuthorityVo> getMenuTree(){
        return authorityService.getMenuTree();
    }

    /**
     * 获得所有一级菜单
     * @return
     */
    @GetMapping("/topMenus")
    public List<Authority> getTopMenus(){
        return authorityService.list(Wrappers.<Authority>lambdaQuery().eq(Authority::getType,1));
    }

    /**
     * 添加菜单，实际是添加类型值为1、2的权限
     * @param authority
     * @return
     */
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

    /**
     * 修改菜单状态
     * @param authority
     * @return
     */
    @PutMapping("/menuStatus")
    public Authority updateMenuStatus(@RequestBody Authority authority){
        authorityService.updateById(authority);
        return authority;
    }

    /**
     * 编辑菜单
     * @param authority
     * @return
     */
    @PutMapping("/menus")
    public Authority editMenu(@RequestBody Authority authority){
        System.out.println(authority);
        authorityService.updateById(authority);
        return authority;
    }

    /**
     * 删除菜单 会将该菜单下的子权限一起删除
     * @param authority_id
     */
    @DeleteMapping("/menus/{authority_id}")
    public void delMenu(@PathVariable Long authority_id){
        authorityService.delMenu(authority_id);
    }

}
