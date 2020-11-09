package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s1mple.minischoolsys.dao.AuthorityMapper;
import com.s1mple.minischoolsys.dao.RoleMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.dto.AdminDto;
import com.s1mple.minischoolsys.domain.vo.MenuVo;
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
    public List<MenuVo> getPersonMenuTree() {
        AdminDto admin = (AdminDto) SecurityUtils.getSubject().getPrincipal();
        return listConverMenuTree(admin.getAuthoritys());
    }

    @Override
    public List<MenuVo> getMenuTree() {
        return authorityMapper.selectMenuTree();
    }

    @Override
    public void delMenu(Long authority_id) {
        List<MenuVo> menuVos = authorityMapper.selectChildrenByParentId(authority_id);
        if (!menuVos.isEmpty()){
            menuVos.forEach(menuVo -> {
                authorityMapper.delete(Wrappers.<Authority>lambdaQuery().eq(Authority::getParent_id,menuVo.getAuthority_id()));
                authorityMapper.deleteById(menuVo.getAuthority_id());
            });
        }
       authorityMapper.deleteById(authority_id);
    }

    private List<MenuVo> listConverMenuTree(List<Authority> authorities){
        List<MenuVo> menus = authorities.stream()
                .filter(authority -> authority.getType() == 1)
                .map(authority -> {
                    MenuVo menu = DozerUtils.map(authority, MenuVo.class);
//                    menu.setChildren(DozerUtils.mapList(authorities.stream().filter(it->it.getParent_id().equals(authority.getAuthority_id())).collect(Collectors.toList()),MenuVo.class));
                    menu.setChildren(authorityMapper.selectChildrenByParentId(authority.getAuthority_id()));
                    return menu;
                }).collect(Collectors.toList());
        return menus;
    }

}
