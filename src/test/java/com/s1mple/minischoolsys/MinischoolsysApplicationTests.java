package com.s1mple.minischoolsys;

import com.s1mple.minischoolsys.dao.AdminMapper;
import com.s1mple.minischoolsys.domain.vo.AuthorityVo;
import com.s1mple.minischoolsys.service.AdminService;
import com.s1mple.minischoolsys.service.AuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MinischoolsysApplicationTests {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminService adminService;

    @Autowired
    AuthorityService authorityService;

    @Test
    void contextLoads() {
        List<AuthorityVo> menuTree = authorityService.getMenuTree();
        System.out.println(menuTree);
    }

}
