package com.example.react_query_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


// Spring security 5.4 이하
// WebSecurityConfigurerAdapter 상속받아서 override 형식으로 사용했지만
// 6.x 버전 부터는 삭제될 예정이여서 다른 방법으로 구현

//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    // SpringSecurity 5.5  이상
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 폼 기반 로그인 비활성화
        http.formLogin().disable();
//        http.formLogin((login) -> login.disable()); // 람다식

        // HTTP기본 인증 비활성화
        http.httpBasic().disable();
//        http.httpBasic((basic)->basic.disable()); // 람다식

        // CSRF 공격 방어 기능 비활성화
        http.csrf().disable();
//        http.csrf((csrf)->csrf.disable());    // 람다식

        // 세션 관리 정책 설정
        // 세션 인증을 사용하지 않고, JWT를 사용하여 인증하기 때문에, 세션 불필요
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}