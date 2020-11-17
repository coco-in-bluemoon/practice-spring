package com.example.core.member;

import com.example.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceImplTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    @Test
    @DisplayName("회원 가입 테스트")
    public void join() {
        Member member = new Member(1L, "userA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        assertThat(findMember).isEqualTo(member);
    }
}
