package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Menu;
import com.s1mple.minischoolsys.domain.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuVo> selectMenuTree();

    List<Menu> selectChildrenMenu(@Param("parent_id") Long parent_id);
}
