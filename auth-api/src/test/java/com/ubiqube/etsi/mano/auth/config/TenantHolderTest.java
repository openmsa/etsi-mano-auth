package com.ubiqube.etsi.mano.auth.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TenantHolderTest {

	@Test
	void test() {
		assertNull(TenantHolder.getTenantId());
		TenantHolder.setTenantId("test");
		assertEquals("test", TenantHolder.getTenantId());
		TenantHolder.clear();
		assertNull(TenantHolder.getTenantId());
	}

}
