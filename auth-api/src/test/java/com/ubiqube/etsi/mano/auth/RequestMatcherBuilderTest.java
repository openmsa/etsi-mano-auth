/**
 *     Copyright (C) 2019-2024 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see https://www.gnu.org/licenses/.
 */
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
