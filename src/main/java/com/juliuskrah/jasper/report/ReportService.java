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

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author Julius Krah
 *
 */
public interface ReportService {
	/**
	 * Generates a HTML report with the given input file. Uses a JREmptyDataSource
	 * 
	 * @param inputFileName report source file without extension
	 * @param params        report parameters
	 * @return the byte[] containing the PDF
	 */
	byte[] generatePDFReport(String inputFileName, Map<String, Object> params);

	/**
	 * Generates a HTML report with the given input file. Uses a JREmptyDataSource
	 * 
	 * @param format        format can be either 'PDF' or 'XLSX'
	 * @param inputFileName report source file without extension
	 * @param params        report parameters
	 * @return the byte[] containing the PDF
	 */
	byte[] generatePDFReport(ExportFormat format, String inputFileName, Map<String, Object> params);

	/**
	 * Generates a HTML report with the given input file
	 * 
	 * @param format        format can be either 'PDF' or 'XLSX'
	 * @param inputFileName report source file without extension
	 * @param params        report parameters
	 * @param dataSource    the source of data
	 * @return the byte[] containing the PDF
	 */
	byte[] generatePDFReport(ExportFormat format, String inputFileName, Map<String, Object> params, JRDataSource dataSource);
}
