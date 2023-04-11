package com.chen.sbbus.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chen.sbbus.Exception.MyException;
import com.chen.sbbus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Component
public class JWTUtils {

    @Autowired
    private DriverService driverService;

    public static String getToken(LoginRequest u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("account", u.getAccount());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
    }

    public static String getMToken(ManagerLoginRequest u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("account", u.getAccount());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws MyException {
        if(StringUtils.isEmpty(token)){
            throw new MyException("token不能为空");
        }

        DecodedJWT jwt = JWT.decode(token);
        String account = jwt.getClaim("account").asString();
        //String password = driverService.getDriverPasswordByAccount(account);
        String password = "123456";
        // 根据获取到的密码，使用密钥创建 JWTVerifier 对象，并验证令牌
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);
    }

}
