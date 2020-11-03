package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s1mple.minischoolsys.dao.AdminMapper;
import com.s1mple.minischoolsys.dao.AuthorityMapper;
import com.s1mple.minischoolsys.dao.RoleMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.dto.AdminDto;
import com.s1mple.minischoolsys.service.AdminService;
import com.s1mple.minischoolsys.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public boolean check(Admin admin) {

        List<Admin> admins = adminMapper.selectList(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, admin.getUsername()));
        if (admins.size()>0) {
            Admin checkAdmin = admins.get(0);
            if (checkAdmin.getPassword().equals(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Admin> getAdminPage() {
        return adminMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Admin addAdmin(Admin admin) {
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adminMapper.insert(admin);
        return admin;
    }

    @Override
    public AdminDto getAdminDtoByAid(Long aid) {
        Admin admin = adminMapper.selectById(aid);
        return Optional.ofNullable(admin).map(it->{
            AdminDto adminDto = DozerUtils.map(admin, AdminDto.class);
            adminDto.setRoles(roleMapper.selectRoleByAid(aid));
            adminDto.setAuthoritys(authorityMapper.selectAuthorityByRids(adminDto.getRoles()));
            return adminDto;
        }).get();

    }
}
