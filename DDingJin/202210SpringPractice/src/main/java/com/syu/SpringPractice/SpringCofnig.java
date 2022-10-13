package com.syu.SpringPractice;

import com.syu.SpringPractice.repository.MemberRepository;
import com.syu.SpringPractice.repository.MemoryMemberRepository;
import com.syu.SpringPractice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCofnig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
