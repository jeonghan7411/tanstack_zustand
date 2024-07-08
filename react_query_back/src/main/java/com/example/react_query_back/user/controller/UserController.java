package com.example.react_query_back.user.controller;

import com.example.react_query_back.constants.SecurityConstants;
import com.example.react_query_back.prop.JwtProp;
import com.example.react_query_back.user.model.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private JwtProp jwtProp;

    /**
     *
     * JWT를 생성하는 Login요청
     *
     * body : {
     *     "id" : "jeonghan",
     *     "pw: : "1234"
     * }
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserVO userVO){
        String userId = userVO.getId();
        String userPw = userVO.getPw();

        log.info("id" + userId);
        log.info("pw" + userPw);

        //사용자 권한
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        // 시크릿키 -> 바이트 변환 해서 사용해야하기때문에 변환
        byte[] signingKey = jwtProp.getSecretKey().getBytes();

        //토큰 생성
        String jwt = Jwts.builder()
                        // .signWith(시크릿키,알고리즘)
                .signWith(Keys.hmacShaKeyFor(signingKey),Jwts.SIG.HS512) // 시그니처 사용할 비밀키, 알고리즘 설정
                .header()                                               // 헤더설정
                    .add("type", SecurityConstants.TOKEN_TYPE) // type : JWT
                .and()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5))  //토큰 만료시간 설정 (현재설정 5일)
                .claim("uid",userId)                            // PAYLOAD - uid : 사용자 아이디
                .claim("rol",roles)                             // PAYLOAD - rol : 권한정보
                .compact();                                           // 최종적으로 토큰 생성
        log.info("jwt : " + jwt);

        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }

    // 토큰 해석
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ResponseEntity<?> userInfo(@RequestHeader(name = "Authorization") String header){

        log.info("====header=====");
        log.info("Authorization : " + header);

        //Authorization : Bearer $(jwt)
        //토큰만 받아오기위해 상수로 지정한 Bearer를 header에서 제거
        String jwt = header.replace(SecurityConstants.TOKEN_PREFIX, "");

        // 시크릿키 -> 바이트 변환 해서 사용해야하기때문에 변환
        byte[] signingKey = jwtProp.getSecretKey().getBytes();

        // 토큰 해석
        Jws<Claims> parsedToken = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(signingKey))     //비밀키 지정
                .build()
                .parseSignedClaims(jwt);                        // 토큰 해석

        log.info("parsedToken : " + parsedToken);

        //등록된 정보들 가져오기
        //사용자
        String userId = parsedToken.getPayload().get("uid").toString();

        //권한
        Claims claims = parsedToken.getPayload();
        Object rolse = claims.get("rol");


        log.info("userId" + userId);
        log.info("rol" + rolse);

        return new ResponseEntity<String>(parsedToken.toString(),HttpStatus.OK);
    }


}
