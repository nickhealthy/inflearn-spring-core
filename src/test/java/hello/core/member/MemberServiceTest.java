package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		// AppConfig appConfig = new AppConfig();
		// memberService = appConfig.memberSerivce();
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		memberService = ac.getBean("memberService", MemberService.class);
	}

	@Test
	@DisplayName("회원 가입 및 조회")
	void join() {
		// given
		Member member = new Member(1L, "memberA", Grade.VIP);

		// when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);

		// then
		Assertions.assertThat(member).isEqualTo(findMember);
	}

}
