package com.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.common.empty.EmptyUtil;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * create token
 * Title：JWTUtil.java
 * Package：com.haunto.core.jwt
 * Project：haunto-common
 * Company: 瀚途科技（深圳）服务有限公司
 * Date：2018年9月28日
 * @author frank-fs
 */

public class JWTUtil {

	/**
	 * Create the secret key of token
	 */
	public static final String JWT_TOKEN_SECRET = "VAnWwRRheImhFsNafy9NRcFz6ZJfPqlG9ap8gTYxbsdk9j2NVHNhko70SYg3n2GK";
	/**
	 * The effective time of token
	 */
	public static final int JWT_TOKEN_PRESCRIPTION = 1 * 24 * 60 * 60;
	/**
	 * The time unit of token
	 */
	public static final int JWT_TOKEN_TIME_UNIT = Calendar.MINUTE;
	/**
	 * jwt encryption method
	 */
	public static final String JWT_TOKEN_ALG = "HS256";
	/**
	 * create token method
	 */
	public static final String JWT_TOKEN_TYP = "JWT";
	/**
	 * create token service name
	 */
	public static final String JWT_TOKEN_ISS = "doudou";
	/**
	 * TokenVo object oriented
	 */
	public static final String JWT_TOKEN_AUD = "web";

	/**
	 * JWT生成Token
	 * @param conditions 待加密数据
	 * @return
	 * @throws Exception
	 */
	public static String encryptionToken(Map<String,Object> conditions){
		if(EmptyUtil.isEmpty(conditions)){
			return null;
		}
		//token 生效时间
		Date iatDate = new Date();
		//token 结束时间
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(JWT_TOKEN_TIME_UNIT, JWT_TOKEN_PRESCRIPTION);
		Date expiresDate = nowTime.getTime();
		//设置参数（jwt的头部承载信息）
		Map<String, Object> mapHeader = new HashMap<>();
		//加密方式
		mapHeader.put("alg", JWT_TOKEN_ALG);
		//声明类型
		mapHeader.put("typ", JWT_TOKEN_TYP);
		JWTCreator.Builder builder = JWT.create().withHeader(mapHeader) //设置请求头
				.withClaim("iss", JWT_TOKEN_ISS) //jwt签发者
				.withClaim("aud", JWT_TOKEN_AUD);

		Set<String> keys = conditions.keySet();
		for (String key : keys) {
			builder.withClaim(key,conditions.get(key).toString());
		}

		String token = null;// signature
		try {
			token = builder.withIssuedAt(iatDate) // sign time
					.withExpiresAt(expiresDate) // expire time
					.sign(Algorithm.HMAC256(JWT_TOKEN_SECRET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 解密Token
	 * @param token 加密串
	 * @return
	 */
	private static Map<String, Claim> decryptToken(String token) {
		if(EmptyUtil.isEmpty(token)){
			return null;
		}
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_TOKEN_SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			return null;
		}
		return jwt.getClaims();
	}

	/**
	 * 获取所有数据
	 * @param token 加密串
	 * @return
	 */
	public static Map<String, String> getCondition(String token) {
		if(EmptyUtil.isEmpty(token)){
			return null;
		}
		Map<String, Claim> claims = decryptToken(token);
		if(claims==null){
			return null;
		}
		Map<String, String> map = new HashMap<>();
		Set<String> keys = claims.keySet();
		for (String key:keys) {
			Claim userIdClaim = claims.get(key);
			map.put(key,userIdClaim.asString());
		}
		return map;
	}
}