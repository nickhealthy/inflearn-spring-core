package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

class OrderServiceImplTest {

	@Test
	void createUsingSpring() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
				TestConfig.class);
		OrderService orderService = ac.getBean(OrderService.class);
		MemberRepository memberRepository = ac.getBean(MemberRepository.class);

		memberRepository.save(new Member(1L, "sungwoo", Grade.VIP));
		Order order = orderService.createOrder(1L, "itemA", 10000);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

	/*
	 * 순수한 java로만 테스트할 땐 생성자 주입이 좋다. - 수정자(setter) 주입은 컴파일 타임 때 오류를 잡아내기 힘들다.
	 */
	@Test
	void createUsingJava() {
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.setDiscountPolicy(new RateDiscountPolicy());

		MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
		orderServiceImpl.setMemberRepository(memoryMemberRepository);
		memoryMemberRepository.save(new Member(1L, "itemA", Grade.VIP));

		Order order = orderServiceImpl.createOrder(1L, "itemA", 10000);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

	}

	@Configuration
	@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class), basePackages = "hello.core")
	static class TestConfig {

	}

}
