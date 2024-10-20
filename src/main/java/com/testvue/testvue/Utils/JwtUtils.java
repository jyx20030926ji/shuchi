package com.testvue.testvue.Utils;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@Configuration
public class JwtUtils {


    //创建默认的密钥与加密算法，提供给空参构造器调用
    private static final String defaultBase64EncodingSecretKey = "eb^29*be";
    private static final SignatureAlgorithm defaultSignatureAlgorithm = SignatureAlgorithm.HS256;

    //空参构造器，内部调用有参构造器传入默认的密钥、加密算法
    public JwtUtils(){
        this(defaultBase64EncodingSecretKey, defaultSignatureAlgorithm);
    }

    private final String base64EncodingSecretKey;//密钥
    private final SignatureAlgorithm signatureAlgorithm;//加密算法

    //有参构造器，创建对象并传入参数为成员变量base64EncodingSecretKey与signatureAlgorithm赋值
    public JwtUtils(String base64EncodingSecretKey, SignatureAlgorithm signatureAlgorithm){
        this.base64EncodingSecretKey = Base64.encodeBase64String(base64EncodingSecretKey.getBytes());;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    /**
     * 生成jwtToken的方法，jwtToken中包含了三部分：Header、PayLoad、Signature
     * - Header：
     *      当前字符串的类型，一般是"JWT"
     *      使用的加密算法，可以是"HS256"或其他算法
     * - Payload：
     *      一般有四种常见的标准字段：
     *          jat：签发时间，即jwt的生成时间
     *          jti：JWT的唯一标识
     *          iss：签发人，一般是username或userId
     *          exp：过期时间
     * - Signature：签名
     * @param issuer
     * @param ttlMillis
     * @param claims
     * @return
     */
    public String encoding(String issuer, long ttlMillis, Map<String, Object> claims){
        // iss签发人，ttlMillis生存时间，claims是指还想要在jwt中存储的一些非隐私信息
        if(claims == null){
            claims = new HashMap<>();
        }
        long nowMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                //JWT唯一标识
                .setId(UUID.randomUUID().toString())
                //签发人，也就是JWT是给谁的
                .setSubject(issuer)
                //载荷部分
                .setClaims(claims)
                //签发时间
                .setIssuedAt(new Date(nowMillis))
                //生成jwt使用的加密算法、密钥
                .signWith(signatureAlgorithm, base64EncodingSecretKey);
        //如果生存时间大于0，过期时间就是当前时间+生存时间
        if(ttlMillis > 0){
            long exp = nowMillis + ttlMillis; //过期时间
            Date date = new Date(exp);        //封装为Date对象
            builder.setExpiration(date);      //设置jwt的过期时间
        }
        return builder.compact(); //这个方法将三部分用符号'.'连接，生成jwt，
    }

    /**
     * 解密jwtToken，并获取jwt载荷内容的方法。
     * Claims就是一个map，里面包含了jwt载荷部分的所有键值对
     * @param jwtToken
     * @return
     */
    public Claims decoding(String jwtToken){
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(base64EncodingSecretKey).parseClaimsJws(jwtToken);
        return claimsJws.getBody();
    }

    // 判断jwtToken是否合法
  /*  public boolean isVerify(String jwtToken) {
        // 这个是官方的校验规则，这里只写了一个”校验算法“，可以自己加
        Algorithm algorithm = null;
        switch (signatureAlgorithm) {
            case HS256:
                algorithm = Algorithm.HMAC256(Base64.decodeBase64(base64EncodingSecretKey));
                break;
            default:
                throw new RuntimeException("不支持该算法");
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(jwtToken);
        // 校验不通过会抛出异常
        // 判断合法的标准：1. 头部和荷载部分没有篡改过。2. 没有过期
        return true;
    } */



}
