package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService bean1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService bean2 = ac.getBean("statefulService", StatefulService.class);

		// ThreadA: A사용자 10000원 주문
		bean1.order("userA", 10000);

		// ThreadA: B사용자 20000원 주문
		bean2.order("userB", 20000);

		// ThreadA: 사용자A 주문 금액 조회
		int price = bean1.getPrice();
		// ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
		System.out.println("price = " + price);

		Assertions.assertThat(bean1.getPrice()).isEqualTo(20000);
	}

	@Configuration
	static class TestConfig {

		@Bean
		StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
