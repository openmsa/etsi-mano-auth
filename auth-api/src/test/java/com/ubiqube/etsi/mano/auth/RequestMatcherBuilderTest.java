package com.ubiqube.etsi.mano.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@ExtendWith(MockitoExtension.class)
class RequestMatcherBuilderTest {
	@Mock
	private HandlerMappingIntrospector introspector;

	@Test
	void testServletPath() {
		final RequestMatcherBuilder srv = new RequestMatcherBuilder(introspector);
		final RequestMatcherBuilder res = srv.servletPath("/");
		assertNotNull(res);
	}

	@Test
	void testMatcher() {
		final RequestMatcherBuilder srv = new RequestMatcherBuilder(introspector);
		final MvcRequestMatcher[] res = srv.matchers("/", "/error");
		assertNotNull(res);
		final RequestMatcherBuilder res1 = srv.servletPath("/");
		final MvcRequestMatcher[] res2 = res1.matchers("/ok");
		assertNotNull(res2);
	}

}
