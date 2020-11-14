package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member) {
        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long id) {
        return memberRepository.findById(id);
    }

    private void validateDuplicatedMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }
}
