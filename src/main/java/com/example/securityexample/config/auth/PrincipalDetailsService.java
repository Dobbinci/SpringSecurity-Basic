package com.example.securityexample.config.auth;

import com.example.securityexample.domain.entity.Member;
import com.example.securityexample.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// loginProcessingUrl("/login")에서 가로챈 POST 요청을 이곳에서 처리
// "/login" 요청이 들어오면 자동으로 PrincipalDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 메서드 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // return 되면 authentication(UserDetails)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if (member != null) {
            return new PrincipalDetails(member);
        }
        return null;
    }
}
