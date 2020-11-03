package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.s1mple.minischoolsys.dao.AdminMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.service.ShiroService;
import com.s1mple.minischoolsys.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, username));
    }

    @Override
    public String createToken(Admin admin) {
        String token = JwtUtils.createToken(admin);
        redisTemplate.opsForValue().set(admin.getAdmin_id().toString(),token);
        admin.setLast_login_time(new Date());
        adminMapper.updateById(admin);
        return token;
    }

    @Override
    public void logout() {
        Admin currentAdmin = (Admin)SecurityUtils.getSubject().getPrincipal();
        System.out.println(currentAdmin.getAdmin_id());
        redisTemplate.delete(currentAdmin.getAdmin_id().toString());
    }
}
