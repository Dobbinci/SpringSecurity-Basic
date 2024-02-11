package com.example.securityexample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity //Spring security filter chain에 등록
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf((csrfConfig) -> csrfConfig.disable()) // CSRF 보호 비활성화

            // 요청에 대한 권한 설정
            .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                    .requestMatchers(PathRequest.toH2Console()).permitAll() // H2 콘솔 접근 허용
                    .requestMatchers("/user/**").authenticated() // "/user/**" 경로는 인증된 사용자만 접근 가능
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN") // "/admin/**" 경로는 "ADMIN" 권한을 가진 사용자만 접근 가능
                    .anyRequest().permitAll()) // 그 외 모든 요청은 접근 허용

             // 폼 로그인 설정
            .formLogin(login -> login
                    .loginPage("/login") // 인증이 필요한 페이지에 인증되지 않은 사용자가 접근 할 경우 해당 URL로 리디렉션
                    .loginProcessingUrl("/login") // 해당 URL로 들어오느 Login 폼에 대한 POST 요청을 가로채서 UserDetailsSerivce로 넘김
                    .usernameParameter("username") // 사용자 이름 파라미터 정의 (프론트 코드의 폼과 동일하게 해주어야 함)
                    .passwordParameter("password") // 비밀번호 파라미터 정의 (프론트 코드의 폼과 동일하게 해주어야 함)
                    .defaultSuccessUrl("/", true) // 로그인 성공 후 해당 주소로 리디렉션
                    .permitAll()) // 로그인 페이지와 로그인 처리 URL에 대한 접근을 모두에게 허용

            .logout(Customizer.withDefaults()); // 로그아웃

        return http.build();
    }

    // BCrypt 암호화 메서드
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
