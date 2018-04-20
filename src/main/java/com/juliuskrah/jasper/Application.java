/*******************************************************************************
 * Copyright 2018, Julius Krah
 * by the @authors tag. See the LICENCE in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.juliuskrah.jasper;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.juliuskrah.jasper.storage.StorageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Julius Krah
 *
 */
@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Scheduled(cron = "${com.juliuskrah.cron}")
	void sendHTMLEmail() {
		log.info("Hello HTML mail!");
	}

	@Scheduled(cron = "${com.juliuskrah.inline-cron}")
	void sendInlineHTMLEmail() {
		log.info("Hello inline HTML mail!");
	}

	@Bean
	ApplicationRunner init(StorageService storageService) {
		return args -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
