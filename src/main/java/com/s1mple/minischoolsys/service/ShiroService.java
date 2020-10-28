package com.s1mple.minischoolsys.service;

import com.s1mple.minischoolsys.domain.Admin;

public interface ShiroService {
    Admin getAdminByUsername(String username);

    String createToken(Admin admin);
}
