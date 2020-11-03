package com.s1mple.minischoolsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.vo.AdminVo;

public interface AdminMapper extends BaseMapper<Admin> {

    public AdminVo selectAdminVoByAid(Long aid);

}
