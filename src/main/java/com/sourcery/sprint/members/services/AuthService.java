package com.sourcery.sprint.members.services;

import com.sourcery.sprint.exceptions.CustomExceptions;
import com.sourcery.sprint.members.model.LoginResponse;
import com.sourcery.sprint.members.model.Member;
import com.sourcery.sprint.members.model.MemberEntity;
import com.sourcery.sprint.members.repository.MemberConverter;
import com.sourcery.sprint.members.repository.MemberRepository;
import com.sourcery.sprint.security.JwtIssuer;
import com.sourcery.sprint.security.UserPrincipal;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String token = jwtIssuer.issue(userPrincipal.getId(), userPrincipal.getEmail(), roles);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

    public ResponseEntity<?> registerMember(Member member) {
        try {
            Optional<MemberEntity> existingUser = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
            if (existingUser.isPresent()) {
                throw new CustomExceptions.MemberExistsException();
            }
            MemberEntity newMember = MemberConverter.toMemberEntity(member);
            if (newMember.getUserType() == null) {
                newMember.setUserType("User");
            }
            newMember.setPassword(passwordEncoder.encode(member.getPassword()));
            memberRepository.addMemberEmailAndPassword(newMember);
            Optional<MemberEntity> registerMemberOptional = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
            if (registerMemberOptional.isEmpty()) {
                throw new CustomExceptions.MemberNotFoundException();
            }
            MemberEntity registerMember = registerMemberOptional.get();
            List<String> userTypes = SecurityContextHolder.getContext().getAuthentication()
                    .getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            String token = jwtIssuer.issue(registerMember.getId(), registerMember.getEmail(), userTypes);
            return ResponseEntity.ok(LoginResponse.builder()
                    .accessToken(token)
                    .build());
        } catch (CustomExceptions.MemberExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    public Optional<MemberEntity> findByEmail(String email) {
        MemberEntity user = memberRepository.findByEmail(email);
        return Optional.ofNullable(user);
    }
}
