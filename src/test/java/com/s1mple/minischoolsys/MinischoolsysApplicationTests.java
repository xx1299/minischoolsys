package com.s1mple.minischoolsys;

import com.s1mple.minischoolsys.dao.AdminMapper;
import com.s1mple.minischoolsys.domain.vo.AdminVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinischoolsysApplicationTests {

    @Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads() {
        AdminVo adminVo = adminMapper.selectAdminVoByAid(1L);
        System.out.println(adminVo);
    }

}
