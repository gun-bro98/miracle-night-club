package Shopping.demo.service;

import Shopping.demo.member.Member;
import Shopping.demo.member.MemberRepository;
import Shopping.demo.member.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        //받아온 member를 save()을 통해 저장한다.
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        //받은 ID를 토대로 리런한다.
        return memberRepository.findById(memberId);
    }
}
