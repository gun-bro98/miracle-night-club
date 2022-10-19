package Shopping.demo.service;

import Shopping.demo.member.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
