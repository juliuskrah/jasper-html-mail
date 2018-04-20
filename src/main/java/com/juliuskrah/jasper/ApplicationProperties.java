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

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * 
 * @author Julius Krah
 *
 */
@Data
@Validated
@ConfigurationProperties(prefix = "com.juliuskrah")
public class ApplicationProperties {

	private final @Valid Mail mail = new Mail();
	/**
	 * Cron expression to schedule HTML mail
	 */
	@NotBlank
	private String cron;
	/**
	 * Cron expression to schedule inline HTML mail
	 */
	@NotBlank
	private String inlineCron;
	/**
	 * The base path where reports will be stored after compilation
	 */
	@NotNull
	private Resource storageLocation;
	/**
	 * The location of JasperReports source files
	 */
	@NotNull
	private Resource reportLocation;

	@Data
	public static class Mail {
		@Email
		@NotNull
		private String sender;
		/**
		 * The display name for the Sender
		 */
		private String personal;
		private String messageSubject;
		@NotEmpty
		private Set<Recipient> recipients;

		@Data
		public static class Recipient {
			@NotBlank
			private String username;
			private String firstName;
			private String lastName;
			@Email
			@NotNull
			private String email;

		}

	}

}
