package com.villageroad.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.villageroad.storage.db.shijia.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * 用于生成和校验token
 */
@Slf4j
public class JwtUtil {
    /**
     * 秘钥
     */
    private static final String SECRET = "my_secret";

    public final static String KEY_USERNAME = "preferred_username";
    public final static String KEY_AUTH = "authorization";
    public final static String KEY_RESOURCE = "resource_access";
    public final static String KEY_TOOLCHAIN = "toolchain-client";
    public final static String KEY_ROLES = "roles";

    public static String getUserName() {
        String preferredUsername = null;
        try {
            String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(KEY_AUTH).substring(7);
            DecodedJWT decode = JWT.decode(token);
            preferredUsername = decode.getClaim(KEY_USERNAME).asString();
        } catch (JWTVerificationException | NullPointerException e) {
            log.error("decode username is null or jwt is not legal");
            throw e;
        }
        return preferredUsername;
    }


    /**
     * 过期时间
     * 单位为秒
     **/
    private static final long EXPIRATION = 1800L;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                //添加头部
                .withHeader(map)
                //可以把数据存在claim中 userId
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("userName", user.getUserName())
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setUserName("test");
        String token = createToken(user);
        System.out.println(token);
    }

    /**
     * 检验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("解析编码异常");
        }

        return jwt.getClaims();
    }
}