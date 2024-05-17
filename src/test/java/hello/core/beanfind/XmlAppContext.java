package hello.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.member.MemberService;

/*
 * XML 설정 참고 - https://docs.spring.io/spring-framework/reference/core/appendix/xsd-schemas.html
 */
public class XmlAppContext {

	@Test
	void xmlAppContext() {
		ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

		MemberService bean = ac.getBean("memberService", MemberService.class);
		Assertions.assertThat(bean).isInstanceOf(MemberService.class);
	}

}
