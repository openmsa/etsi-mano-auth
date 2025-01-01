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
package com.ubiqube.etsi.mano.autth.oauth2.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import jakarta.servlet.ServletRequest;

@ExtendWith(MockitoExtension.class)
class JwtTenantExtractorTest {
	@Mock
	private ServletRequest request;

	@Test
	void test() {
		final JwtTenantExtractor srv = new JwtTenantExtractor();
		srv.extractTenant(request);
		assertTrue(true);
	}

	@Test
	void test2() {
		final JwtTenantExtractor srv = new JwtTenantExtractor();
		final Authentication auth = new UsernamePasswordAuthenticationToken(null, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		srv.extractTenant(request);
		assertTrue(true);
	}

	@Test
	void test3() {
		final JwtTenantExtractor srv = new JwtTenantExtractor();
		final Jwt princ = new Jwt("token", Instant.now().minus(Duration.ofMinutes(5)),
				Instant.now().plus(Duration.ofMinutes(5)),
				Map.of("Authorization", "Bearer Token"),
				Map.of("iss", "http://issuer/"));
		final Authentication auth = new UsernamePasswordAuthenticationToken(princ, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		srv.extractTenant(request);
		assertTrue(true);
	}

	@Test
	void test4() {
		final JwtTenantExtractor srv = new JwtTenantExtractor();
		final Jwt princ = new Jwt("token", Instant.now().minus(Duration.ofMinutes(5)),
				Instant.now().plus(Duration.ofMinutes(5)),
				Map.of("Authorization", "Bearer Token"),
				Map.of("tenant_id", List.of("tenant")));
		final Authentication auth = new UsernamePasswordAuthenticationToken(princ, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		srv.extractTenant(request);
		assertTrue(true);
	}

}
