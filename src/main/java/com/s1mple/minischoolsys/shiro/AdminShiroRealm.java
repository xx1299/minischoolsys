package com.s1mple.minischoolsys.shiro;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.s1mple.minischoolsys.dao.AdminMapper;
import com.s1mple.minischoolsys.dao.AuthorityMapper;
import com.s1mple.minischoolsys.dao.RoleMapper;
import com.s1mple.minischoolsys.domain.Admin;
import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.Role;
import com.s1mple.minischoolsys.domain.dto.AdminDto;
import com.s1mple.minischoolsys.domain.vo.AdminVo;
import com.s1mple.minischoolsys.domain.vo.RoleVo;
import com.s1mple.minischoolsys.exception.CustomException;
import com.s1mple.minischoolsys.exception.ExceptionType;
import com.s1mple.minischoolsys.service.AdminService;
import com.s1mple.minischoolsys.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public class AdminShiroRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    AdminMapper adminMapper;

    @Lazy
    @Autowired
    AuthorityMapper authorityMapper;

    @Lazy
    @Autowired
    RoleMapper roleMapper;

    @Lazy
    @Autowired
    AdminService adminService;

    @Lazy
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

//    用户授权添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminDto admin = (AdminDto)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        添加角色
        for (Role role : admin.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getName());
        }
//        添加权限
        for (Authority authority : admin.getAuthoritys()) {
            simpleAuthorizationInfo.addStringPermission(authority.getPath());
        }
        return simpleAuthorizationInfo;
    }

//    用户登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("登录验证");
        String token = (String) authenticationToken.getPrincipal();
        Claim adminId = JwtUtils.getClaims(token).getClaim("adminId");
        AdminDto currentAdmin = adminService.getAdminDtoByAid(adminId.asLong());
        if (null == currentAdmin){
            throw new CustomException(ExceptionType.User_INPUT_ERROR,"账户不存在");
        }
        String saveToken = (String) redisTemplate.opsForValue().get(currentAdmin.getAdmin_id().toString());
        if (null == saveToken || !JwtUtils.verifyToken(token)){
            throw new CustomException(ExceptionType.User_INPUT_ERROR,"token失效，请重新登录");
        }
//        第一个参数可以是admin对象，第二个参数是token，第三个参数是加密用的盐可以不加，第四个参数是该realm的名字
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(currentAdmin,saveToken, getName());
        return simpleAuthenticationInfo;
    }
}
