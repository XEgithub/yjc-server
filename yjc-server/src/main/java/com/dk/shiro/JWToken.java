package com.dk.shiro;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dk.config.Constant;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * trip
 *
 * @author zhanghaowei
 * @date 2018/4/10
 */
public class JWToken {

    /**
     * 公用秘钥
     */
    public static String SECRET = "yjc";

    /**
     * 生成微信openid token
     * @param openid
     *      微信openid
     * @return
     *      token
     */
    public static String createWxToken(String openid){
        return createToken(Constant.WX_TOKEN, openid);
    }

    /**
     * 生成token
     * @param key
     *      键
     * @param value
     *      值
     * @return
     *      token
     */
    public static String createToken(String key, String value) {
        try {
            //签发时间
            Date now = new Date();

            //过期时间
            Calendar expireDateTmp = Calendar.getInstance();
            expireDateTmp.add(Calendar.HOUR, 24);
            Date expireDate = expireDateTmp.getTime();

            Map<String,Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim(key, value)
                    .withExpiresAt(expireDate)
                    .withIssuedAt(now)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取微信openid
     * @param token
     *      token
     * @return
     *      openid
     */
    public static String getOpenid(String token){
        return get(token, Constant.WX_TOKEN);
    }

    public static String getUuid(String token){
        return get(token, Constant.WX_TOKEN);
    }

    /**
     * 根据key获取value
     * @param token
     *      token
     * @param key
     *      键
     * @return
     *      值
     */
    public static String get(String token, String key){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(key).asString();
    }

    /**
     * 验证token有效性
     * @param token
     *      待验证token
     * @param key
     *      键
     * @param value
     *      值
     * @return
     *      是否成功
     */
    public static boolean verify(String token, String key, String value){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(key, value)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static void main(String[] args) {

        String key = "a",val="b";
        String token = JWToken.createToken(key, val);
        System.out.println(token);
        String value = JWToken.get(token, key);
        System.out.println(value);
        boolean verify = JWToken.verify(token, key, val);
        System.out.println("校验结果："+verify);

    }

}
