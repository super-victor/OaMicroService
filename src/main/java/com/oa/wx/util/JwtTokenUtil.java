package com.oa.wx.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Description jwt-token的工具类，用于签发token，验证token等
 * @Author JohnTang
 * @LastChangeDate 2020/12/15
 * @Version v2.0
 */

@Component
public class JwtTokenUtil implements Serializable {

    // 密钥
    @Value("${jwt.secret}")
    private String secret;

    // 过期时间
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @MethodName getUsername
     * @param token
     * @Description 从token中获取用户名
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/15
     */
    public String getUsername(String token){
        return parseToken(token).getSubject();
    }

    /**
     * @MethodName calculateExpirationDate
     * @param createdDate
     * @Description 计算过期时间
     * @Author JohnTang
     * @Return java.util.Date
     * @LastChangeDate 2020/12/15
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    /**
     * @MethodName generateToken
     * @param subject 一般是用户名
     * @Description 生成token
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/15
     */
    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @MethodName parseToken
     * @param token
     * @Description 解析token
     * @Author JohnTang
     * @Return io.jsonwebtoken.Claims
     * @LastChangeDate 2020/12/15
     */
    private Claims parseToken(String token) throws ExpiredJwtException, SignatureException {
        Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        return claims;
    }

    /**
     * @MethodName isTokenExpired
     * @param claims
     * @param passwordChangeDate 用户最后一次修改密码的时间
     * @Description token是否过期
     * @Author JohnTang
     * @Return java.lang.Boolean
     * @LastChangeDate 2020/12/15
     */
    private Boolean isCreateAfterLastPasswordReset(Claims claims, Date passwordChangeDate) {
        Date createDate = getCreateDate(claims);
        // 如果签发的token未过期并且token在最后一次密码之后签发
        return createDate.after(passwordChangeDate);
    }

    /**
     * @MethodName refreshToken
     * @param token
     * @Description 刷新toekn
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/15
     */
    public String refreshToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = parseToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Date getCreateDate(Claims claims){
        return new Date(claims.getExpiration().getTime() - expiration * 1000);
    }

}
