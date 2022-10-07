# Palindrome

Create a webservice in Spring Boot with REST API that calculates the longest
palindrome in the text content submitted to it. Only alphabetic characters counts, everything 
else is ignored, as from validity, as from length point of view.

Examples:
+ `abrakadabra` ->`ada`: 3
+ `abraka d abra` -> `ada`: 3
+ `abraka|$++d++$|abra` -> `ada` 3

## Requirements

### Running with Docker
+ Docker 

### Running without Docker

+ JDK 11
+ Postgres database

## Usage

Create a `.env` file in the root of the repository, then run
```shell
docker compose up
```
See [example](example.env) for the configuration possibilities.

## API documentation

The app has self-hosted OpenAPI documentation. [Browse](http://localhost:8080/swagger-ui/index.html) after starting the application.

## Running tests

The tests do not have a container, they can be run with Gradle (For requirements see: [Running without docker](#running-without-docker)). The tests require a database 
named `palindrome_test` by default. This database is also migrated by Flyway, but it has additional
[migrations](./src/main/resources/db/test_data) to create initial content. These migrations will clean the
database before running the scripts.

Running the tests:
```shell
gradle test 
```

## Known limitations

The app does not keep track of what timezone the request had. This means that if your payload
contains a different timezone than UTC, the responses will still show the timestamps in UTC instead
of your local time.