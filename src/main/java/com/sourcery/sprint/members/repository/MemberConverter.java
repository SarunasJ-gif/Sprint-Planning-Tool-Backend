package com.sourcery.sprint.members.repository;

import com.sourcery.sprint.members.model.Member;
import com.sourcery.sprint.members.model.MemberEntity;
import com.sourcery.sprint.members.model.MemberWithDays;

public class MemberConverter {
    public static Member fromEntityToMember(MemberEntity entity) {
        Member member = new Member();
        member.setId(entity.getId());
        member.setFirstName(entity.getFirstName());
        member.setLastName(entity.getLastName());
        member.setEmail(entity.getEmail());
        member.setRole(entity.getRole());
        member.setSoftDelete(entity.getSoftDelete());
        member.setUserType(entity.getUserType());
        return member;
    }

    public static MemberEntity toMemberEntity(Member member) {
        MemberEntity entity = new MemberEntity();
        entity.setId(member.getId());
        entity.setFirstName(member.getFirstName());
        entity.setLastName(member.getLastName());
        entity.setEmail(member.getEmail());
        entity.setPassword(member.getPassword());
        entity.setRole(member.getRole());
        entity.setSoftDelete(member.getSoftDelete());
        entity.setUserType(member.getUserType());
        return entity;
    }

    public static MemberWithDays fromEntityToMemberWithDays(MemberEntity memberEntity) {
        MemberWithDays member = new MemberWithDays();
        member.setId(memberEntity.getId());
        member.setFirstName(memberEntity.getFirstName());
        member.setLastName(memberEntity.getLastName());
        member.setEmail(memberEntity.getEmail());
        member.setRole(memberEntity.getRole());
        member.setSoftDelete(memberEntity.getSoftDelete());
        return member;
    }
}
