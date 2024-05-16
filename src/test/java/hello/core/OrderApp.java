package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

/**
 * 주문과 할인 정책 
 */
public class OrderApp {

	public static void main(String[] args) {
		
		// MemberService memberService = new MemberServiceImpl();
		// OrderService orderService = new OrderServiceImpl();
		
		// AppConfig appConfig = new AppConfig();
		// MemberService memberSerivce = appConfig.memberSerivce();
		// OrderService orderService = appConfig.orderService();
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
		
		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		
		System.out.println("order = " + order);
	}
	
}
