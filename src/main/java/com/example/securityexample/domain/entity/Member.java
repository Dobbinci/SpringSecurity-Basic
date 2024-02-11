package com.example.securityexample.domain.entity;

import com.example.securityexample.application.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String username;

    private String password;

    private int age;

    private String role;

    public static Member toMember(MemberDto dto) {
        return Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .age(dto.getAge())
                .build();
    }
}
