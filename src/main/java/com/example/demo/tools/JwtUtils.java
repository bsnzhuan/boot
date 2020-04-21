package com.example.demo.tools;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * JWT工具类
 * 生成JWT加密信息及验证
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 1800L;//单位为秒

    public static String createToken(Map<String,String> map){
        //过期时间
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(System.currentTimeMillis()+EXPIRATION*1000),
                TimeZone.getDefault().toZoneId());
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        String token = "";
        token = JWT.create()
                .withHeader(headerMap)
                .withClaim("id",map.get("id"))
                .withClaim("userName",map.get("userName"))
                .withClaim("usercode",map.get("usercode"))
                .withExpiresAt(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            //JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            //jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("token解码异常");
            //解码异常则抛出异常
            return null;
        }
        return jwt.getClaims();
    }
}
