package com.juliuskrah.jasper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.juliuskrah.jasper.report.ExportFormat;

@Component
public class FormatConverter implements Converter<String, ExportFormat> {

	@Override
	public ExportFormat convert(String source) {
		if (source == null)
			return ExportFormat.PDF;
		var upper = source.toUpperCase();
		try {
			return ExportFormat.valueOf(upper);
		} catch (Exception e) {
			return ExportFormat.PDF;
		}
	}

}
