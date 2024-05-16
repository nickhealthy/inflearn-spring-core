package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 *	스프링으로 변환
 *	애플리케이션의 전체 동작 방식을 구성
 *  - 구현 객체를 생성하고, 연결하는 책임을 가짐 
 */
@Configuration
public class AppConfig {

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());

	}

	@Bean
	public DiscountPolicy discountPolicy() {
		// return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
