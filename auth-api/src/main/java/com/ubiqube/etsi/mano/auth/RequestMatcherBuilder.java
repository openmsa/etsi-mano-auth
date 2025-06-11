/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Component
public class RequestMatcherBuilder {
	private final HandlerMappingIntrospector introspector;
	private final String servletPath;

	@Autowired
	public RequestMatcherBuilder(final HandlerMappingIntrospector introspector) {
		this(introspector, null);
	}

	private RequestMatcherBuilder(final HandlerMappingIntrospector introspector, final String servletPath) {
		this.introspector = introspector;
		this.servletPath = servletPath;
	}

	public PathPatternRequestMatcher[] matchers(final String... patterns) {
		final PathPatternRequestMatcher[] matchers = new PathPatternRequestMatcher[patterns.length];
		for (int index = 0; index < patterns.length; index++) {
			matchers[index] = PathPatternRequestMatcher.withDefaults().matcher(servletPath);
		}
		return matchers;
	}

	public RequestMatcherBuilder servletPath(final String path) {
		return new RequestMatcherBuilder(this.introspector, path);
	}
}
