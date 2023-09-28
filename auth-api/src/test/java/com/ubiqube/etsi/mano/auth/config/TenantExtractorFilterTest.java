package com.ubiqube.etsi.mano.auth.config;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@ExtendWith(MockitoExtension.class)
class TenantExtractorFilterTest {
	@Mock
	private TenantExtrator tenantExtractor;
	@Mock
	private ServletRequest request;
	@Mock
	private ServletResponse response;
	@Mock
	private FilterChain chain;

	@Test
	void test() throws IOException, ServletException {
		final TenantExtractorFilter srv = new TenantExtractorFilter(tenantExtractor);
		srv.doFilter(request, response, chain);
		verify(chain).doFilter(request, response);
	}

	@Test
	void testWithTenant() throws IOException, ServletException {
		final TenantExtractorFilter srv = new TenantExtractorFilter(tenantExtractor);
		when(tenantExtractor.extractTenant(request)).thenReturn("tenant");
		srv.doFilter(request, response, chain);
		verify(chain).doFilter(request, response);
	}
}
