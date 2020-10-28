package com.s1mple.minischoolsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s1mple.minischoolsys.domain.Menu;
import com.s1mple.minischoolsys.domain.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<MenuVo> getMenuTree();

    void delMenu(Long menu_id);
}
