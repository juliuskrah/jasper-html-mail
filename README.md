# JasperReport HTML mail with Spring

A demonstration of how to send an HTML mail with Spring using JasperReport as the template
engine. In this demo, two types of HTML mails are demonstated:

1. HTML mail with no inline resources
2. HTML mail with inline resources

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