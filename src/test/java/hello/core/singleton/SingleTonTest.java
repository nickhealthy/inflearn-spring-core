package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingleTonTest {

	@Test
	@DisplayName("스프링이 없는 순수한 DI 컨테이너")
	void pureContainer() {

		AppConfig appConfig = new AppConfig();
		// 1. 조회: 호출할 때마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();

		// 2. 조회: 호출할 때마다 객체를 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조값이 다른 것을 확인
		System.out.println(memberService1);
		System.out.println(memberService2);

		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	public void singletonServiceTest() {

		// private으로 생성자를 막아두었다. 컴파일 오류가 발생
		// new SingletonService();

		// 1. 조회: 호출할 때마다 같은 객체를 반환
		SingletonService instance1 = SingletonService.getInstance();

		// 2. 조회: 호출할 때마다 같은 객체를 반환
		SingletonService instance2 = SingletonService.getInstance();

		// 참조값이 같은 것을 확인
		System.out.println(instance1);
		System.out.println(instance2);

		Assertions.assertThat(instance1).isSameAs(instance2);

	}
}
