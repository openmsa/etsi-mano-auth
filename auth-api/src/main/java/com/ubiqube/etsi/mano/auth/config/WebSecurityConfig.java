/**
 *     Copyright (C) 2019-2023 Ubiqube.
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
package com.ubiqube.etsi.mano.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import com.ubiqube.etsi.mano.auth.AuthException;
import com.ubiqube.etsi.mano.auth.RequestMatcherBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final SecutiryConfig secutiryConfig;
	private final TenantExtrator tenantExtrator;

	public WebSecurityConfig(final SecutiryConfig secutiryConfig, final TenantExtrator tenantExtrator) {
		this.secutiryConfig = secutiryConfig;
		this.tenantExtrator = tenantExtrator;
	}

	/**
	 * All request must be authenticated, No login page.
	 */
	@Bean
	SecurityFilterChain configure(final HttpSecurity http, final RequestMatcherBuilder mvc) {
		try {
			http.headers(headers -> headers.frameOptions(x -> x.sameOrigin()));
			http.csrf(csrf -> csrf.disable());
			http.authorizeHttpRequests(autorize -> {
				autorize.requestMatchers(mvc.matchers("/", "/api/**", "/ui/**", "/webjars/**", "/npm/**", "/error",
						"/h2-console/**", "/download/**", "/actuator/**", "/swagger-ui.html", "/swagger-ui/**",
						"/api-docs/**", "/v3/**")).permitAll();
				secutiryConfig.configure(autorize);
			});
			secutiryConfig.configure(http);
			http.addFilterAfter(new TenantExtractorFilter(tenantExtrator), SwitchUserFilter.class);
			return http.build();
		} catch (final Exception e) {
			throw new AuthException(e);
		}
	}
}
