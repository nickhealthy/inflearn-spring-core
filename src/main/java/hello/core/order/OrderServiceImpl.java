package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private MemberRepository memberRepository;
	private DiscountPolicy discountPolicy;

	/*
	 * [수정자 주입] - 선택, 변경 가능성이 있는 의존관계에 사용 - 생성자 주입 이후 Setter 주입이 발동하게 된다.
	 */
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		System.out.println("MemberRepository = " + memberRepository);
		this.memberRepository = memberRepository;
	}

	@Autowired
	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		System.out.println("DiscountPolicy = " + discountPolicy);
		this.discountPolicy = discountPolicy;
	}

	public OrderServiceImpl() {
		super();
	}

	/*
	 * [생성자 주입] - 생성자 호출 시점에 딱 1번만 호출되는 것을 보장 - 주로 불변, 필수 의존관계에 사용
	 */
	public OrderServiceImpl(MemberRepository memberRepository,
			DiscountPolicy discountPolicy) {
		super();
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {

		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);

	}

}
