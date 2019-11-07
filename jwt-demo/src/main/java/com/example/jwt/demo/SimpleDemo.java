package com.example.jwt.demo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyPair;

/**
 * @Auther: wangzhendong
 * @Date: 2019/11/6 09:22
 * @Description: jwt的组成就三部分： header头，payload有效信息，signature签名
 */
public class SimpleDemo {

    public static void main(String[] args) {
    }

    /**
     * JWT 的生成十分简单，jjwt是对jwt的生成做了封装，可以直接使用，
     * 其中，jwt的加密部分共有12中签名算法，可参考github：
     * https://github.com/jwtk/jjwt#install-jdk-maven
     */
    public void simple() {
        //简单获取jwt
        //获取密钥
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //使用密钥加密生成jws
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        System.out.println(jws);
        //校验jws
        assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe"));

        //获取密钥
        SecretKey key1 = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
        String jws1 = Jwts.builder().setSubject("Joe").signWith(key1).compact();
        System.out.println(jws1);

        //用二进制生成密钥
        byte[] keyBytes = "getSigningKeyFromApplicationConfiguration".getBytes();
        SecretKey key2 = Keys.hmacShaKeyFor(keyBytes);
        String jws2 = Jwts.builder().setSubject("Joe").signWith(key2).compact();
        System.out.println(jws2);

        //非对称加密算法加密生成jws
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        String jws3 = Jwts.builder().setSubject("wang").signWith(keyPair.getPrivate()).compact();
        System.out.println(jws3);
        System.out.println(Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(jws3).getBody().getSubject().equals("wang"));

    }

    /**
     * 创建JWS步骤
     * 1.使用该Jwts.builder()方法创建JwtBuilder实例。
     * 2.调用JwtBuilder方法以根据需要添加标头参数和声明。
     * 3.指定要用于签名JWT的SecretKey或不对称PrivateKey。
     * 4.最后，调用该compact()方法进行压缩和签名，生成最终的jws。
     */
    public void getJWS() {
        //1.获取JwtBuilder实例
        JwtBuilder jwtBuilder = Jwts.builder();
        //2.调用方法设置标头参数和声明，对应了jws生成部分的header和payload
        //设置header
        //jwtBuilder.setHeaderParams();
        //Header header = Jwts.header();
        //jwtBuilder.setHeader(header);
        //设置payload
        //jwtBuilder.setClaims();
        //jwtBuilder.setClaims();
        //    .setIssuer("me")
        //    .setSubject("Bob")
        //    .setAudience("you")
        //    .setExpiration(expiration) //a java.util.Date
        //    .setNotBefore(notBefore) //a java.util.Date
        //    .setIssuedAt(new Date()) // for example, now
        //    .setId(UUID.randomUUID()) //just an example id
        //3.指定签名JWT
        //jwtBuilder.signWith(Key key);
        //jwtBuilder.signWith(Key key,SignatureAlgorithm)
    }


    /**
     * 解析JWS步骤：
     * <p>
     * 1.使用该Jwts.parser()方法创建JwtParser实例。
     * 2.指定要用于验证JWS签名的SecretKey或不对称PublicKey。1个
     * 3.最后，parseClaimsJws(String)用您的jws 调用该方法String，生成原始的JWS。
     * 4.整个调用都包装在try / catch块中，以防解析或签名验证失败。稍后我们将讨论异常和失败原因。
     */
    public void readJws(Key key, String jwt) {
        Jws<Claims> jws;
        try {
            //1.创建JwtParser实例
            JwtParser jwtParser = Jwts.parser();
            //2.指定验证签名的密钥
            JwtParser jjws = jwtParser.setSigningKey(key);
            //3.parseClaimsJws(String) 解析jws获取原始的Jws
            jws = jjws.parseClaimsJws(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            //4.解析失败处理异常
        }

        //对于步骤2中，如果用户使用复杂的密钥加密，不是简单的密钥或公私钥
        // 如果您的应用程序期望可以用不同的密钥签名的JWS，则不会调用该setSigningKey方法。
        // 相反，您将需要实现 SigningKeyResolver接口并JwtParser通过setSigningKeyResolver方法指定实例。
        /*SigningKeyResolver signingKeyResolver = getMySigningKeyResolver();
        Jwts.parser()
                .setSigningKeyResolver(signingKeyResolver)
                .parseClaimsJws(jwt);*/

        /*同样，对于jws的解析出来的最终的是Jws对象，也可以在解析式追加对断言cliams的筛选，来解析jws
            假设您要求正在解析的JWS具有特定的sub（主题）值，否则您可能不信任令牌。您可以通过使用以下各种require*方法之一来实现 JwtParser：
        try {
            Jwts.parser().requireSubject("jsmith").setSigningKey(key).parseClaimsJws(s);
        } catch(InvalidClaimException ice) {
            // the sub field was missing or did not have a 'jsmith' value
        }
            您还可以使用require(fieldName, requiredFieldValue)方法来要求自定义字段-例如：
            Jwts.parser().require("myfield", "myRequiredValue").setSigningKey(key).parseClaimsJws(s);

        * */

        /*
         * 同样解析jws时，可以考虑时钟的问题，和jwsstring的压缩传输，都可以通过设置实现，详细查看jws的文档
         * jws也提供了json和base64传输token
         */

    }


}
