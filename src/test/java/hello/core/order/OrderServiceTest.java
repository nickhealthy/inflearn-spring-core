package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class OrderServiceTest {
	
	// MemberService memberService = new MemberServiceImpl();
	// OrderService orderService = new OrderServiceImpl();
	ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	MemberService memberService = ac.getBean("memberService", MemberService.class);
	OrderService orderService = ac.getBean("orderService", OrderService.class);
	
	@Test
	@DisplayName("주문 생성")
	void createOrder() {
		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

}
