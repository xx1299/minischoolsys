package com.s1mple.minischoolsys.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.s1mple.minischoolsys.domain.Admin;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private  static final int keepTime = 1000 * 60 * 60;
    private static final String adminId = "adminId";



    public static String createToken(HttpServletRequest request){
        String auth = request.getHeader("auth");
        return auth;
    }

    public static String createToken(Admin admin){
        System.out.println(admin);
        long now=System.currentTimeMillis();
        Date date = new Date(now);
        long exp=now+keepTime;
        Date expdate = new Date(exp);
        Map<String,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ","jwt");
        headerMap.put("alg","HS256");
        String token = JWT.create().withHeader(headerMap)
                .withClaim(adminId,admin.getAdmin_id())
                .withIssuedAt(date)
                .withExpiresAt(expdate)
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }

    public static DecodedJWT getClaims(String token){
        DecodedJWT decode = JWT.decode(token);
        return decode;
    }

    /**
     * 验证token
     * @param token 凭证
     * @return token是否过期 false为过期，true为未过期
     */
    public static boolean verifyToken(String token){
        DecodedJWT claims = getClaims(token);
        Date exp = claims.getExpiresAt();
        if (exp.after(new Date())){
            return true;
        }
        return false;
    }

}
