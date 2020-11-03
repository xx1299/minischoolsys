package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Role;
import com.s1mple.minischoolsys.domain.vo.RoleVo;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRoleByAid(Long admin_id);

    List<RoleVo> selectRoleVoByAid(Long admin_id);

}
