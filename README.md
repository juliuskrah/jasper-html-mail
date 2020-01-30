# JasperReport PDF REST API with Spring

This branch demonstrates how to create a PDF endpoint using Spring REST:

## Prerequisite

1. Maven 3.3+
2. JDK 1.8+

## Quick Start

Clone:

```posh
C:\> git clone https://github.com/juliuskrah/jasper-html-mail.git
C:\> cd jasper-html-mail
C:\> git checkout jasper-rest
C:\> mvnw spring-boot:run
```

Access the endpoint <http://localhost:8080/:username>.

Using cURL

```posh
C:\> curl http://localhost:8080/juliuskrah --output test.pdf
```

Find the full tutorial here <http://juliuskrah.com/blog/2018/04/30/sping-pdf-rest-api-with-jasperreports/>.

To be able to generate reports as either PDF or Excel checkout the [Jasper REST Multi-Format](https://github.com/juliuskrah/jasper-html-mail/tree/jasper-rest-multi)
of this repository.
