package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s1mple.minischoolsys.dao.AuthorityMapper;
import com.s1mple.minischoolsys.dao.RoleMapper;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.dto.AdminDto;
import com.s1mple.minischoolsys.domain.vo.AuthorityVo;
import com.s1mple.minischoolsys.service.AuthorityService;
import com.s1mple.minischoolsys.utils.DozerUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<AuthorityVo> getPersonMenuTree() {
        AdminDto admin = (AdminDto) SecurityUtils.getSubject().getPrincipal();
        return listConverMenuTree(admin.getAuthoritys());
    }

    @Override
    public List<AuthorityVo> getMenuTree() {
        return authorityMapper.selectMenuTree();
    }

    @Override
    public void delMenu(Long authority_id) {
        List<AuthorityVo> authorityVos = authorityMapper.selectChildrenByParentId(authority_id);
        if (!authorityVos.isEmpty()){
            authorityVos.forEach(authorityVo -> {
                authorityMapper.delete(Wrappers.<Authority>lambdaQuery().eq(Authority::getParent_id,authorityVo.getAuthority_id()));
                authorityMapper.deleteById(authorityVo.getAuthority_id());
            });
        }
       authorityMapper.deleteById(authority_id);
    }

    private List<AuthorityVo> listConverMenuTree(List<Authority> authorities){
        List<AuthorityVo> authorityVos = authorities.stream()
                .filter(authority -> authority.getType() == 1)
                .map(authority -> {
                    AuthorityVo authorityVo = DozerUtils.map(authority, AuthorityVo.class);
                    authorityVo.setChildren(authorityMapper.selectChildrenByParentId(authority.getAuthority_id()));
                    return authorityVo;
                }).collect(Collectors.toList());
        return authorityVos;
    }

    private void recursionDelAuthority(Long authority_id){
        List<AuthorityVo> authorityVos = authorityMapper.selectChildrenByParentId(authority_id);
        if (!authorityVos.isEmpty()){
            authorityVos.forEach(authorityVo -> {
                recursionDelAuthority(authorityVo.getAuthority_id());
            });
        }
        authorityMapper.deleteById(authority_id);
    }
}
