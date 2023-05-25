package com.sourcery.sprint.members.services;

import com.sourcery.sprint.exceptions.CustomExceptions;
import com.sourcery.sprint.members.model.Member;
import com.sourcery.sprint.members.model.MemberEntity;
import com.sourcery.sprint.members.repository.MemberConverter;
import com.sourcery.sprint.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void addMember(Member member) {
        Optional<MemberEntity> existingMember = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
        if (existingMember.isPresent()) {
            throw new CustomExceptions.MemberExistsException();
        }
        MemberEntity memberEntity = MemberConverter.toMemberEntity(member);
        memberRepository.addMember(memberEntity);
    }

    public void update(Member member) {
        Optional<MemberEntity> existingMember = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
        if (existingMember.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        MemberEntity memberEntity = MemberConverter.toMemberEntity(member);
        memberRepository.update(memberEntity);
    }

    public List<Member> getAllMembers() {
        List<MemberEntity> memberEntities = memberRepository.getAllMembers();
        return memberEntities.stream().map(MemberConverter::fromEntityToMember).collect(Collectors.toList());
    }

    public Member getMemberById(Long id) {
        Optional<MemberEntity> existingMember = memberRepository.findById(id);
        if (existingMember.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        return MemberConverter.fromEntityToMember(existingMember.get());
    }

    public void updateMemberRole(Member member) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(member.getId());
        if (optionalMemberEntity.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        memberRepository.updateMemberRole(member.getRole(), member.getId());
    }

    public Iterable<Member> getAllMembersByTeamId(Long id) {
        return memberRepository.getAllMembersByTeamId(id).stream().
                map(MemberConverter::fromEntityToMember).collect(Collectors.toList());
    }
}