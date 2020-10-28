package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRoleByAid(Long admin_id);
}
