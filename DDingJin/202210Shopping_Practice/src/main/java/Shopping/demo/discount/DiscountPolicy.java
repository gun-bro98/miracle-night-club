package Shopping.demo.discount;

import Shopping.demo.member.Member;

public interface DiscountPolicy {

    //return 할인 금액
    int discount(Member member, int price);
}
