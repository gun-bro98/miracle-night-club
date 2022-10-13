package com.syu.SpringPractice.service;

import com.syu.SpringPractice.domain.Member;
import static org.assertj.core.api.Assertions.*;

import com.syu.SpringPractice.repository.MemberRepository;
import com.syu.SpringPractice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//테스트 클래스는 빌드될 때 포함되지 않으므로 막 써도 됨!
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test //회원가입,
    void join회원가입() {
        //given , 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        //when , 언제
        Long saveId = memberService.join(member);

        //then , 이런 결과가 나와야
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");  //이름 중복 회원

        //when
        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}