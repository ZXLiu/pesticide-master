package pers.geolo.pesticide.server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

public class JwtAuthUtils {//设置首次登录时的token，用于下次登录时验证；并且可以从token中解析出用户的userId

    private static String secret = UUID.randomUUID().toString();//自动生成密钥

    public static String createToken(int userId, long expireTime) {//创建token
        Date nowDate = new Date();//获取当前时间
        Date expireDate = new Date(nowDate.getTime() + expireTime);//设置token的过期时间
        String token = JWT.create()//生成token串
                .withClaim("userId", userId)//生成token的payLoad部分，通常是放一些用户信息进去，如id，名字，权限等，千万不要放密码
                .withIssuedAt(nowDate)//签发时间
                .withExpiresAt(expireDate)//过期时间
                .sign(Algorithm.HMAC256(secret));//签名的算法及密钥，其中secret是不能泄露的
        return token;
    }

    public static int parseId(String token) {//解析出token里面传入的对象的userId
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();//创建验证对象
        DecodedJWT jwt = verifier.verify(token);
        int userId = jwt.getClaim("userId").asInt();//解析出token里面传入的对象的userId
        return userId;
    }
}
