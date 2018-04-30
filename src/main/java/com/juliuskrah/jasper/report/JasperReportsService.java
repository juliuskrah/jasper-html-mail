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
package com.juliuskrah.jasper.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.juliuskrah.jasper.storage.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

/**
 * @author Julius Krah
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JasperReportsService implements ReportService {
	private final StorageService storageService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.mail.ReportService#generateHtmlReport(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public byte[] generatePDFReport(String inputFileName, Map<String, Object> params) {
		return generatePDFReport(inputFileName, params, new JREmptyDataSource());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.mail.ReportService#generateHtmlReport(java.lang.String,
	 * java.util.Map, net.sf.jasperreports.engine.JRDataSource)
	 */
	@Override
	public byte[] generatePDFReport(String inputFileName, Map<String, Object> params,
			JRDataSource dataSource) {
		byte[] bytes = null;
		JasperReport jasperReport = null;
		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
			// Check if a compiled report exists
			if (storageService.jasperFileExists(inputFileName)) {
				jasperReport = (JasperReport) JRLoader
						.loadObject(storageService.loadJasperFile(inputFileName));
			}
			// Compile report from source and save
			else {
				String jrxml = storageService.loadJrxmlFile(inputFileName);
				log.info("{} loaded. Compiling report", jrxml);
				jasperReport = JasperCompileManager.compileReport(jrxml);
				// Save compiled report. Compiled report is loaded next time
				JRSaver.saveObject(jasperReport,
						storageService.loadJasperFile(inputFileName));
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					dataSource);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		}
		catch (JRException | IOException e) {
			e.printStackTrace();
			log.error("Encountered error when loading jasper file", e);
		}

		return bytes;
	}
}
