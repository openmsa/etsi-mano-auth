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
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.autth.oauth2.config;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import com.ubiqube.etsi.mano.auth.config.TenantExtrator;

import jakarta.servlet.ServletRequest;

@Configuration
public class JwtTenantExtractor implements TenantExtrator {

	@Override
	public String extractTenant(final ServletRequest request) {
		final Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			final Object principal = auth.get().getPrincipal();
			if (principal instanceof final Jwt jwt) {
				return handleJwt(jwt);
			}
			return auth.get().getName();
		}
		return null;
	}

	private static String handleJwt(final Jwt jwt) {
		return Optional.ofNullable((List<String>) jwt.getClaim("tenant_id")).map(x -> x.get(0)).orElse("BOOTSTRAP");
	}

}
