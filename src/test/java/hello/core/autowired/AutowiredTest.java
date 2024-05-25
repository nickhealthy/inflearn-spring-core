package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;

/**
 * [실행결과 - 스프링 빈으로 등록이 되어있지 않을 때]
 * - noBean1 = 호출 안됨
 * - noBean2 = null
 * - noBean3 = Optional.empty
 * 
 * * 참고: Autowired는 스프링 컨테이너가 실행될 때 
 * 1. 스프링 빈을 등록하고,
 * 2. 의존관계 주입을 처리하기 때문에
 * 자동으로 아래와 같은 setter 주입이 일어나게 된다. 
 */
public class AutowiredTest {

	@Test
	void autowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}

	static class TestBean {

		@Autowired(required = false)
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 = " + noBean1);
		}

		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 = " + noBean2);
		}

		@Autowired
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 = " + noBean3);
		}

	}

}
