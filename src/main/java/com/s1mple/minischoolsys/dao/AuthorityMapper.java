package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.Menu;
import com.s1mple.minischoolsys.domain.Role;
import com.s1mple.minischoolsys.domain.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper extends BaseMapper<Authority> {
    List<Authority> selectAuthorityByRids(@Param("roles") List<Role> roles);

    List<Authority> selectAuthorityByRid(Long role_id);

    List<MenuVo> selectMenuTree();

    List<MenuVo> selectChildrenByParentId(Long authority_id);
}
