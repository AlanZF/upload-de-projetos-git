package com.ibm.alan.ponto.seguranca;

import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTCreator {
	
	public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObjeto jwtObjeto) {
    	
        String token = Jwts.builder().setSubject(jwtObjeto.getSubject())
        		.setIssuedAt(jwtObjeto.getIssuedAt())
        		.setExpiration(jwtObjeto.getExpiration())
        		.claim(ROLES_AUTHORITIES, checkRoles(jwtObjeto.getRoles()))
        		.signWith(SignatureAlgorithm.HS512, key).compact();
        return prefix + " " + token;
    }
    
    public static JWTObjeto create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
    	
    	JWTObjeto object = new JWTObjeto();
    	
        token = token.replace(prefix, "");
        
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        object.setSubject(claims.getSubject());
        object.setExpiration(claims.getExpiration());
        object.setIssuedAt(claims.getIssuedAt());
        object.setRoles((List<String>) claims.get(ROLES_AUTHORITIES));
        
        return object;
    }
    
    private static List<String> checkRoles(List<String> roles) {
        return roles.stream()
        		.map(s -> "ROLE_".concat(s.replaceAll("ROLE_","")))
        		.collect(Collectors.toList());
    }

}
