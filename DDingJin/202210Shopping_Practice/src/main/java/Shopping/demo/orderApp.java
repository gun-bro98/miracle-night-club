package Shopping.demo;

import Shopping.demo.member.Grade;
import Shopping.demo.member.Member;
import Shopping.demo.member.MemberService;
import Shopping.demo.member.MemberServiceImpl;
import Shopping.demo.order.Order;
import Shopping.demo.order.OrderService;
import Shopping.demo.order.OrderServiceImpl;

//일반 자바 문법을 이용한 테스트 방법.
//그러나 Junit을 이용한 절차적인 단위테스트에 익숙해져야 한다.

public class orderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
