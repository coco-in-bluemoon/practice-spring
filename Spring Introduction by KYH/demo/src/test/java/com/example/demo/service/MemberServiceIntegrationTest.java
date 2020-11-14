package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void join() {
        Member member = new Member();
        member.setName("test");
        long savedId = memberService.join(member);

        Member result = memberService.findOne(savedId).get();

        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    public void duplicatedMember() {
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }
}
