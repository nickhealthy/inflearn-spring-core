package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

/**
 * CGLIB: 바이트코드 조작 라이브러리
 * - 스프링은 해당 라이브러리를 사용해서 Config 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한다.
 * - 이 덕분에 싱글톤이 보장되는 것
 * - Configuration을 붙이지 않으면 바이트코드를 조작하는 CGLIB 기술을 사용하지 않는다. 
 */
public class ConfigurationDeep {

	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		// AppConfig도 스프링 빈으로 등록됨
		AppConfig bean = ac.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass());
		// bean = class hello.core.AppConfig$$SpringCGLIB$$0

	}

}
