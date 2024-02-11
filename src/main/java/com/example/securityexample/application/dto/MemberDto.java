package com.example.securityexample.application.dto;

import com.example.securityexample.presentation.request.SignUpRequest;
import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long memberId;

    private String username;

    private String password;

    private int age;

    private String role;

    public static MemberDto from(SignUpRequest request) {
        return MemberDto.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .age(request.getAge())
                .build();
    }

}
