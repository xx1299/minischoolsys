package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s1mple.minischoolsys.dao.MenuMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.Menu;
import com.s1mple.minischoolsys.domain.vo.MenuVo;
import com.s1mple.minischoolsys.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> getMenuTree() {
        Admin principal = (Admin) SecurityUtils.getSubject().getPrincipal();
        System.out.println(principal);
        return menuMapper.selectMenuTree();
    }

    @Override
    public void delMenu(Long menu_id) {
        Menu menu = menuMapper.selectById(menu_id);
        if (!ObjectUtils.isEmpty(menu)){
            if (ObjectUtils.isEmpty(menu.getParent_id())){
//                一级菜单先删除子菜单，再删除父菜单
                menuMapper.delete(Wrappers.<Menu>lambdaQuery().eq(Menu::getParent_id,menu_id));
                menuMapper.deleteById(menu_id);
            }else{
//              二级菜单直接删除
                menuMapper.deleteById(menu_id);
            }
        }
    }
}
