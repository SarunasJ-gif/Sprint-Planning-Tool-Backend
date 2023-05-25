package com.sourcery.sprint.members.repository;

import com.sourcery.sprint.members.model.MemberEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface MemberRepositoryMyBatis {

    @Select("SELECT id, first_name AS firstName, last_name AS lastName, email, role, soft_delete AS softDelete, " +
            "user_type AS userType FROM member WHERE id=#{id}")
    MemberEntity findById(@Param("id") Long id);
    
    @Select("SELECT id, first_name AS firstName, last_name AS lastName, email, role, soft_delete AS softDelete, user_type AS userType FROM member")
    List<MemberEntity> getAllMembers();


    @Insert("INSERT INTO member (first_name, last_name, email, password, role, soft_delete, user_type) " +
            "VALUES (#{firstName}, #{lastName}, #{email}, #{password}, #{role}, #{softDelete}, #{userType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(@RequestBody MemberEntity member);

    @Insert("INSERT INTO member (email, password) VALUES (#{email}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveMemberEmailAndPassword(@RequestBody MemberEntity member);

    @Update("UPDATE member SET first_name=#{firstName}, last_name=#{lastName} " +
        "WHERE email=#{email}")
    void update(@RequestBody MemberEntity member);

    @Select("SELECT m.id, m.first_name AS firstName, m.last_name AS lastName, m.email, m.role, m.soft_delete AS softDelete" +
            " FROM member m LEFT JOIN sprint_member sm ON m.id = sm.member_id WHERE sm.sprint_id=#{id}")
    List<MemberEntity> findBySprintId(@Param("id") Long id);

    @Insert("INSERT INTO sprint_member (sprint_id, member_id) SELECT #{sprintId}, #{memberId} " +
            "WHERE NOT EXISTS (SELECT 1 FROM sprint_member WHERE sprint_id = #{sprintId} AND member_id = #{memberId})")
    void saveSprintMemberIds(@Param("sprintId") Long sprintId, @Param("memberId") Long memberId);

    @Select("SELECT m.id, m.first_name AS firstName, m.last_name AS lastName, m.email, m.password, " +
            "m.role, m.soft_delete AS softDelete, m.user_type AS userType FROM member m WHERE m.email=#{email}")
    MemberEntity findByEmail(@Param("email") String email);

    @Select("SELECT m.id, m.first_name AS firstName, m.last_name AS lastName, m.email, m.role, m.soft_delete AS softDelete" +
            " FROM member m LEFT JOIN team_member tm ON m.id = tm.member_id WHERE tm.team_id = #{id}")
    List<MemberEntity> findMembersByTeamId(@Param("id") Long id);

    @Update("UPDATE member SET role=#{role} WHERE id = #{id}")
    void updateMemberRole(@Param("role") String role, @Param("id") Long id);
}

