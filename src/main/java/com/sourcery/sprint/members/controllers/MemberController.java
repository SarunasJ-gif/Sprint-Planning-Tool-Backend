package com.sourcery.sprint.members.controllers;

import com.sourcery.sprint.members.model.Member;
import com.sourcery.sprint.members.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        memberService.update(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/role")
    public ResponseEntity<Member> updateMemberRole(@RequestBody Member member) {
        memberService.updateMemberRole(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("team/{id}")
    public Iterable<Member> getAllMembersByTeamId(@PathVariable Long id) {
        return memberService.getAllMembersByTeamId(id);
    }
}

