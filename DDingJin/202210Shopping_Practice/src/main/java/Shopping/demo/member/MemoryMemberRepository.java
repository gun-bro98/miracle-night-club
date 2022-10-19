package Shopping.demo.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        //hash MAP에 넣는다.
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        //hash MAP에서 꺼낸다.
        return store.get(memberId);
    }
}
