# API Routes

```
credit application-> "/credit-application/{id}"
all credit applications-> "GET /credit-application"
post a credit application-> "POST /credit-application"
```

# Notes

An application with REST API for car leasing. The application determines automatically result of the aplication as successfully approved when income is not lower than 600eur per family.

# Checklist

## Requirements

There should be an API for:
- [x] Retrieving credit application
- [x] Retrieving multiple credit applications
- [x] Adding an application
- [x] Automised applicationdecision making process

- [x] Reasonable amount of tests 
- [x] Solution should be uploaded to version control
- [x] Solution should be built using: Java 8, Spring boot, JPA, Hibernate, REST, JUnit, gitHub
- [x] Exception handling
- [x] Project README.md must be created with instructions 