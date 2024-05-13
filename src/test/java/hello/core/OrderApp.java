package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 주문과 할인 정책 
 */
public class OrderApp {

	public static void main(String[] args) {
		
		// MemberService memberService = new MemberServiceImpl();
		// OrderService orderService = new OrderServiceImpl();
		
		AppConfig appConfig = new AppConfig();
		MemberService memberSerivce = appConfig.memberSerivce();
		OrderService orderService = appConfig.orderService();
		
		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberSerivce.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		
		System.out.println("order = " + order);
	}
	
}
