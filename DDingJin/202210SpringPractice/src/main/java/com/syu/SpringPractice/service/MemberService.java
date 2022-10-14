package com.syu.SpringPractice.service;

import com.syu.SpringPractice.domain.Member;
import com.syu.SpringPractice.repository.MemberRepository;
import com.syu.SpringPractice.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member) {
        //비즈니스 로직1. 같은 이름이 있는 중복회원 가입 불가
        ValidateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    //중복회원 검증(회원이름을 통한)
    private void ValidateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(a -> {
                throw new IllegalStateException("이미 존재하는 이름입니다.");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
