package Shopping.demo.order;

import Shopping.demo.discount.DiscountPolicy;
import Shopping.demo.discount.FixDiscountPolicy;
import Shopping.demo.member.Member;
import Shopping.demo.member.MemberRepository;
import Shopping.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    MemberRepository memberRepository = new MemoryMemberRepository();
    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
