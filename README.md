# JasperReport HTML mail with Spring

A demonstration of how to send an HTML mail with Spring using JasperReport as the template
engine. In this demo, two types of HTML mails are demonstated:

1. HTML mail with no inline resources
2. HTML mail with inline resources

## Prerequisites

1. Maven 3.3+
2. JDK 1.8+

## Quick Start
Clone, set the values for the following environment variables and run the repository:

- MAIL_USERNAME
- MAIL_PASSWORD
- MAIL_HOST
- MAIL_PORT

```posh
C:\> git clone https://github.com/juliuskrah/jasper-html-mail.git
C:\> cd jasper-html-mail
C:\> REM set the environment variables listed above
C:\> mvnw spring-boot:run
```

# Jasper REST

The [Jasper REST](https://github.com/juliuskrah/jasper-html-mail/tree/jasper-rest) branch of this
repository provides a nice REST interface to generate your Jasper PDF files using Spring 5 and Spring-Boot 2.

The [Jasper REST Multi-Format](https://github.com/juliuskrah/jasper-html-mail/tree/jasper-rest-multi)
branch of this repository generates a RESTful representation of the Jasper Report either as PDF or
Excel based on user input