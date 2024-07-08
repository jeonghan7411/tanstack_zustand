package com.example.jwtsecurity.service.impl;

import com.example.jwtsecurity.mapper.UserMapper;
import com.example.jwtsecurity.model.UserAuth;
import com.example.jwtsecurity.model.Users;
import com.example.jwtsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 회원 등록 (회원 가입)
     * 1. 비밀번호 암호화
     * 2. 회원 등록
     * 3. 권한 등록
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int insert(Users user) throws Exception {
        // 비밀번호 암호화
        String userPw = user.getUserPw();
        String encodedPw = passwordEncoder.encode(userPw);
        user.setUserPw(encodedPw);

        // 회원등록
        int result = userMapper.insert(user);

        // 권한 등록
        if( result > 0){
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(user.getUserId());
            userAuth.setAuth("ROLE_USER"); // 기본 권한
            result = userMapper.insertAuth(userAuth);
        }

        return result;
    }

    /**
     * 회원조회
     * @param userNo
     * @return
     * @throws Exception
     */
    @Override
    public Users select(int userNo) throws Exception {
        return userMapper.select(userNo);
    }

    /**
     *  로그인
     * @param user
     * @param requset
     * @throws Exception
     */
    @Override
    public void login(Users user, HttpServletRequest requset) throws Exception {
        String username = user.getUserId();
        String password = user.getUserPw();
        log.info("username :" + username );
        log.info("password :" + password );

        // AuthenticationManager
        // 아이디, 패스워드 인증 토큰 생성
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username,password);

        // 토큰에 요청정보 등록
        // HttpServletRequest는 클라이언트 -> 서버 로 직접 보내는 것이라
        // 실제 로그인요청이 온것인지 request로 확인 하고 넣어줌
        token.setDetails(new WebAuthenticationDetails(requset));

        // 토큰을 이용하여 인증 요청 - 로그인
        Authentication authentication =  authenticationManager.authenticate(token);

        log.info("인증 여부 : " + authentication.isAuthenticated());

        //스프링시큐리티에 있는 User로 정보를 확인할수있다.
        User authUser = (User) authentication.getPrincipal();
        log.info("인증된 사용자 아이디: " + authUser.getUsername());

        // 시큐리티 컨텍스트에 인증된 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    /**
     * 회원정보 수정
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int update(Users user) throws Exception {
        // 비밀번호 암호화
        String userPw = user.getUserPw();
        String encodedPw = passwordEncoder.encode(userPw);
        user.setUserPw(encodedPw);

        int result = userMapper.update(user);

        return result;
    }


    /***
     * 회원삭제 (회원탈퇴)
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public int delete(String userId) throws Exception {
        return userMapper.delete(userId);
    }
}