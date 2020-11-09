package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.Role;
import com.s1mple.minischoolsys.domain.vo.AuthorityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper extends BaseMapper<Authority> {
    List<Authority> selectAuthorityByRids(@Param("roles") List<Role> roles);

    List<Authority> selectAuthorityByRid(Long role_id);

    List<AuthorityVo> selectMenuTree();

    List<AuthorityVo> selectChildrenByParentId(Long authority_id);
}
