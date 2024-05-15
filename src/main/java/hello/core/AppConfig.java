package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 *	애플리케이션의 전체 동작 방식을 구성
 *  - 구현 객체를 생성하고, 연결하는 책임을 가짐 
 */
public class AppConfig {

	public MemberService memberSerivce() {
		return new MemberServiceImpl(memberRepository());
	}

	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());

	}

	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
	}
}
