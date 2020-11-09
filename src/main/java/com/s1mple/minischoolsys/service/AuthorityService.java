package com.s1mple.minischoolsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.vo.AuthorityVo;

import java.util.List;

public interface AuthorityService extends IService<Authority> {
    List<AuthorityVo> getPersonMenuTree();

    List<AuthorityVo> getMenuTree();

    void delMenu(Long authority_id);
}
