package com.s1mple.minischoolsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.dto.AdminDto;

import java.util.List;

public interface AdminService extends IService<Admin> {
    boolean check(Admin admin);

    List<Admin> getAdminPage();

    Admin addAdmin(Admin admin);

    AdminDto getAdminDtoByAid(Long aid);
}
