package Shopping.demo.service;

import Shopping.demo.member.Grade;
import Shopping.demo.member.Member;
import Shopping.demo.member.MemberService;
import Shopping.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        Member member = new Member(1L, "MemberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(findMember).isEqualTo(member);
    }


}