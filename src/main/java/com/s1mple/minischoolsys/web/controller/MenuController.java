package com.s1mple.minischoolsys.web.controller;


import com.s1mple.minischoolsys.domain.Menu;
import com.s1mple.minischoolsys.domain.vo.MenuVo;
import com.s1mple.minischoolsys.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menus")
    @RequiresPermissions("/menus")
    public List<MenuVo> getMenuTree(){
        return menuService.getMenuTree();
    }

    @GetMapping("/menus/{menu_id}")
    public Menu getMenu(@PathVariable("menu_id") Long menu_id){
        return menuService.getById(menu_id);
    }


    @PostMapping("/menus")
    public Menu addMenu(@RequestBody Menu menu){
          menuService.save(menu);
          return menu;
    }

    @DeleteMapping("/menus/{menu_id}")
    public void delMenu(@PathVariable("menu_id") Long menu_id){
        menuService.delMenu(menu_id);
    }


    @PutMapping("/menus")
    public Menu editMenu(@RequestBody Menu menu){
        menuService.updateById(menu);
        return menu;
    }
}
