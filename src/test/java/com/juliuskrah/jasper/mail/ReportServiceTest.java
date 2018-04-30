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

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;

import com.juliuskrah.jasper.ApplicationTests;
import com.juliuskrah.jasper.report.ReportService;

/**
 * @author Julius Krah
 *
 */
public class ReportServiceTest extends ApplicationTests {
	@Inject
	private ReportService reportService;
	
	@Test
	public void whenGeneratePDFThenBytes() {
		Map<String, Object> params = new HashMap<>();
		params.put("username", "Julius");
		byte[] result = reportService.generatePDFReport("pdf_rest_resource", params);
		assertNotNull(result);
	}
}
