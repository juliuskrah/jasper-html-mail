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
package com.juliuskrah.jasper.mail;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.juliuskrah.jasper.ApplicationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Julius Krah
 *
 */
@Async
@Slf4j
@Component
@RequiredArgsConstructor
public class HtmlEmailService implements EmailService {
	private final JavaMailSender javaMail;
	private final ApplicationProperties properties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.mail.EmailService#sendHtmlEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendHtmlEmail(String recipient, String html) {
		final MimeMessage message = javaMail.createMimeMessage();
		try {
			final MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			helper.setFrom(properties.getMail().getSender(),
					properties.getMail().getPersonal());
			helper.setTo(recipient);
			helper.setSubject(properties.getMail().getMessageSubject());
			log.info("Sending HTML email to {}", recipient);
			// Set to true for HTML
			helper.setText(html, true);
			javaMail.send(message);
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			log.error("Error encountered preparing MimeMessage", e);
		}

		log.debug("Sending html email completed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.mail.EmailService#sendHtmlEmail(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public void sendHtmlEmail(String recipient, String html,
			Map<String, byte[]> imageSource) {
		final MimeMessage message = javaMail.createMimeMessage();
		try {
			final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(properties.getMail().getSender(),
					properties.getMail().getPersonal());
			helper.setTo(recipient);
			helper.setSubject(properties.getMail().getMessageSubject());
			log.info("Sending HTML email to {}", recipient);
			// Set to true for HTML
			helper.setText(html, true);
			for(Entry<String, byte[]> val : imageSource.entrySet()) {
				helper.addInline(val.getKey(), new ByteArrayResource(val.getValue()), "image/png");
			}
			javaMail.send(message);
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			log.error("Error encountered preparing MimeMessage", e);
		}

		log.debug("Sending html email completed");
	}

}
