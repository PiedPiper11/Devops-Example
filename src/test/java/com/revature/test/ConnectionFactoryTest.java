package com.revature.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.revature.util.ConnectionFactory;

public class ConnectionFactoryTest {

	@Test
	public void connectionFactory_ShouldProduceValidConnections() {
		assertNotNull(ConnectionFactory.getConnection());
	}
}
