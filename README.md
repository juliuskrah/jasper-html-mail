# JasperReport PDF REST API with Spring

This branch demonstrates how to create a PDF endpoint using Spring REST:

## Prerequisite

1. Maven 3.5+
2. JDK 11+

## Quick Start

Clone:

```posh
C:\> git clone https://github.com/juliuskrah/jasper-html-mail.git
C:\> cd jasper-html-mail
C:\> git checkout jasper-rest-multi
C:\> mvnw spring-boot:run
```

Access the endpoint <http://localhost:8080/:username?format=:format>.

- format is one of `pdf` or `xlsx`

Using cURL

```posh
C:\> curl http://localhost:8080/juliuskrah?format=xlsx --output test.xlsx
```

## Implementation

The following code snippets illustrates how to work with the Jasper Reports

```java
public byte[] generatePDFReport(ExportFormat format, String inputFileName, Map<String, Object> params,
      JRDataSource dataSource) {
  byte[] bytes = null;
  JasperReport jasperReport = null;
  try {
    if (storageService.jasperFileExists(inputFileName)) {
      jasperReport = (JasperReport) JRLoader.loadObject(storageService.loadJasperFile(inputFileName));
    } else {
      String jrxml = storageService.loadJrxmlFile(inputFileName);
      jasperReport = JasperCompileManager.compileReport(jrxml);
      JRSaver.saveObject(jasperReport, storageService.loadJasperFile(inputFileName));
    }
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
    if (format == ExportFormat.PDF)
      bytes = generatePDF(jasperPrint);
    else if (format == ExportFormat.XLSX)
      bytes = generateExcel(jasperPrint);
  } catch (JRException e) {
    // handle error
  }
  return bytes;
}

private byte[] generatePDF(JasperPrint jasperPrint) throws JRException {
  return JasperExportManager.exportReportToPdf(jasperPrint);
}

private byte[] generateExcel(JasperPrint jasperPrint) throws JRException {
  byte[] bytes = null;
  var input = new SimpleExporterInput(jasperPrint);
  try (var byteArray = new ByteArrayOutputStream()) {
    var output = new SimpleOutputStreamExporterOutput(byteArray);
    var exporter = new JRXlsxExporter();
    exporter.setExporterInput(input);
    exporter.setExporterOutput(output);
    exporter.exportReport();
    bytes = byteArray.toByteArray();
    output.close();
  } catch (IOException e) {
    // handle IO error
  }
  return bytes;
}
```
