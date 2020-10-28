package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper extends BaseMapper<Authority> {
    List<Authority> selectAuthorityByRid(@Param("roles") List<Role> roles);
}
