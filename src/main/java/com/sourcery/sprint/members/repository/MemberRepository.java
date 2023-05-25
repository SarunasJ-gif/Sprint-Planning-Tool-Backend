package com.sourcery.sprint.members.repository;

import com.sourcery.sprint.members.model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @Autowired
    private MemberRepositoryMyBatis memberRepositoryMyBatis;


    public Optional<MemberEntity> findById(Long id) {
        return Optional.ofNullable(memberRepositoryMyBatis.findById(id));
    }

    public MemberEntity findByEmail(String email) {
        return memberRepositoryMyBatis.findByEmail(email);
    }

    public List<MemberEntity> getAllMembers() {
        return memberRepositoryMyBatis.getAllMembers();
    }

    public void addMember(MemberEntity member) {
        memberRepositoryMyBatis.save(member);
    }

    public void addMemberEmailAndPassword(MemberEntity member) {
        memberRepositoryMyBatis.saveMemberEmailAndPassword(member);
    }

    public void update(MemberEntity member) {
        memberRepositoryMyBatis.update(member);
    }

    public List<MemberEntity> findMembersBySprintId(Long id) {
        return memberRepositoryMyBatis.findBySprintId(id);
    }

    public void addSprintMemberIds(Long sprintId, Long memberId) {
        memberRepositoryMyBatis.saveSprintMemberIds(sprintId, memberId);
    }

    public List<MemberEntity> getAllMembersByTeamId(Long teamId) {
        return memberRepositoryMyBatis.findMembersByTeamId(teamId);
    }

    public void updateMemberRole(String role, Long memberId) {
        memberRepositoryMyBatis.updateMemberRole(role, memberId);
    }
}