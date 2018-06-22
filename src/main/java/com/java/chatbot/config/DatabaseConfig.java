package com.java.chatbot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class DatabaseConfig {
	
	@Value("${pcf.dbname}")
	private String dbname;
	
	@Bean
	public Cloud cloud() {
		return new CloudFactory().getCloud();
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = cloud().getServiceConnector(dbname, DataSource.class, null);
		return dataSource;
	}
}
