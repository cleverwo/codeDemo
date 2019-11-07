package com.example.jwt.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * @Auther: wangzhendong
 * @Date: 2019/11/6 15:28
 * @Description:
 */
public class MySigningKeyResolver extends SigningKeyResolverAdapter {

    @Override
    public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
        // implement me 可以从jwsheader和claims中获取值，对这些值操作数据库或者其他来查找密钥，最终返回密钥
        //继承该方法就是对密钥的管理
        String keyId = jwsHeader.getKeyId();
        return testDatabaseKeyById(keyId);
    }

    public Key testDatabaseKeyById(String keyId){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return key;
    }
}
