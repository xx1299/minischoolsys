package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.s1mple.minischoolsys.dao.AdminMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.service.ShiroService;
import com.s1mple.minischoolsys.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RedisTemplate redisTemplate;
//    @Resource(name="redisTemplate")
//    ValueOperations valueOperations;


    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, username));
    }

    @Override
    public String createToken(Admin admin) {
        String token = JwtUtils.createToken(admin);
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(admin.getUsername(),token);
        return token;
    }
}
