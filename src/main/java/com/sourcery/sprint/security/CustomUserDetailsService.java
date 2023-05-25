package com.sourcery.sprint.security;

import com.sourcery.sprint.exceptions.CustomExceptions;
import com.sourcery.sprint.members.model.MemberEntity;
import com.sourcery.sprint.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String email){
        MemberEntity memberEntity;
        try {
            memberEntity = memberRepository.findByEmail(email);
        } catch (CustomExceptions.MemberNotFoundException e) {
            throw new RuntimeException(e);
        }
        return UserPrincipal.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(memberEntity.getUserType())))
                .build();
    }
}
